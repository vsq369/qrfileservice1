package com.cecurs.handle;

import com.cecurs.common.ReturnValue;
import com.cecurs.entity.Cmd;
import com.cecurs.enums.CmdResp;
import com.cecurs.enums.MessageType;
import com.cecurs.service.FileInfoService;
import com.cecurs.util.TcpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 2019年4月11日15:21:57
 * @author  wangjc
 * 上传工具类
 */

@Component
@Slf4j
public class UploadFileSvr {


    @Value("system.upload")
    private String upladPath;

    @Resource
    private TcpClient tcpClient;

    @Resource
    private FileCmdCls  fileCmdCls;

    public String uploadFile(String inName,String fName,String incode,String lsh){

        String filename = upladPath+fName;
        File file = new File(filename);
        long maxLen = file.length();
        ReturnValue head = fileCmdCls.cmdHeadV2(lsh, MessageType.CMD4001.getType(),MessageType.CMD4001.getLength());
        ReturnValue cmd4001 = fileCmdCls.cmd4001Data(inName,incode);
        String sendCmd = head.getCmd().getDataBlock()+cmd4001.getCmd().getDataBlock()+fileCmdCls.MAC("","");
        String ret4008 = tcpClient.sendNormal(sendCmd);
       Cmd cmd =  fileCmdCls.unPackNormal(ret4008);
       if(!cmd.getBodyEntity().getCmdEntity().getResp().equals("00")){
           //上传失败
           return CmdResp.FAIL.getCode();
       }

        head = fileCmdCls.cmdHeadV2(lsh, MessageType.CMD4006.getType(),MessageType.CMD4006.getLength());
        ReturnValue cmd4006 = fileCmdCls.cmd4006Data("1","00");
        sendCmd = head.getCmd().getDataBlock()+cmd4006.getCmd().getDataBlock()+fileCmdCls.MAC("","");
        ret4008 =   tcpClient.sendNormal(sendCmd);
         cmd =  fileCmdCls.unPackNormal(ret4008);
        if(!cmd.getBodyEntity().getCmdEntity().getResp().equals("00")){
            //上传失败
            return CmdResp.FAIL.getCode();
        }
        //发送4003 通知报文
        head = fileCmdCls.cmdHeadV2(lsh, MessageType.CMD4003.getType(),MessageType.CMD4003.getLength());
        ReturnValue cmd4003 = fileCmdCls.cmd4003Data(upladPath,fName);
        sendCmd = head.getCmd().getDataBlock()+cmd4003.getCmd().getDataBlock()+fileCmdCls.MAC("","");
        String cmd4005 = tcpClient.sendNormal(sendCmd);
        cmd =  fileCmdCls.unPackNormal(cmd4005);

        if(!"00".equals(cmd.getBodyEntity().getCmdEntity().getResp())){
            return CmdResp.FAIL.getCode();
        }

        //得到已上传文件大小

        int serverLength = cmd.getBodyEntity().getCmdEntity().getRecvlen();

        if(serverLength>maxLen){
            head = fileCmdCls.cmdHeadV2(lsh,MessageType.CMD4008.getType(),MessageType.CMD4008.getLength());
            ReturnValue cmd4008 = fileCmdCls.cmd4008Data("09");
            sendCmd = head.getCmd().getDataBlock()+cmd4008.getCmd().getDataBlock()+fileCmdCls.MAC("","");
            log.info("发送4008报文"+sendCmd);
            tcpClient.sendNormal(sendCmd);
            return CmdResp.FAIL.getCode();
        }
        InputStream fis = null;
        try{
             fis = new FileInputStream(file);
            //设置游标位置
            fis.skip(serverLength);
            byte[] bytes = new byte[1024];
            int zlen = 0;
            int length = 0 ;

            while (((length = fis.read(bytes, 0, bytes.length)) != -1) ){
                String lstflag = "0";
                zlen +=length;
                if(maxLen==zlen){
                    lstflag="1";
                }

                ReturnValue cmd4004 =  fileCmdCls.cmd4004uploadData(lstflag,new String(bytes,0,length,"GB2312"));
                head = fileCmdCls.cmdHeadV2(lsh,MessageType.CMD4004.getType(),length+7);
                sendCmd = head.getCmd().getDataBlock()+cmd4004.getCmd().getDataBlock()+fileCmdCls.MAC("","");

                ret4008 = tcpClient.sendNormal(sendCmd);
                cmd =  fileCmdCls.unPackNormal(ret4008);

                if(!cmd.getBodyEntity().getCmdEntity().getResp().equals("00")){
                    return CmdResp.FAIL.getCode();
                }
            }
            head = fileCmdCls.cmdHeadV2(lsh,MessageType.CMD4007.getType(),MessageType.CMD4007.getLength());
            ReturnValue cmd4007 = fileCmdCls.cmd4007Data();
            sendCmd = head.getCmd().getDataBlock()+cmd4007.getCmd().getDataBlock()+fileCmdCls.MAC("","");
            ret4008 =  tcpClient.sendNormal(sendCmd);
            cmd =  fileCmdCls.unPackNormal(ret4008);
            if(!cmd.getBodyEntity().getCmdEntity().getResp().equals("00")){
                //上传失败
                return CmdResp.FAIL.getCode();
            }
        }catch (Exception e){
          log.error(e.getMessage());
        }finally {
            try {
                fis.close();
                tcpClient.CloseLink();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return CmdResp.SUCCESS.getCode();
    }
}

