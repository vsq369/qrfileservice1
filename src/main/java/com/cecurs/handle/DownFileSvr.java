package com.cecurs.handle;

import com.cecurs.common.ReturnValue;
import com.cecurs.entity.Cmd;
import com.cecurs.entity.FileInfo;
import com.cecurs.enums.MessageType;
import com.cecurs.service.FileInfoService;
import com.cecurs.util.TcpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.annotation.Resource;


/**
 * @author  wangjc
 * 下载工具类
 */
@Configuration
@Component
@Slf4j
public class DownFileSvr {

    @Value("file.downFilePath")
    private String downFilePath;

    @Resource
    private FileInfoService fileInfoService;

    @Resource
    private FileCmdCls  fileCmdCls;

    /**
     * 下载步骤 参考交通部文件传输规范
     * @return
     */
    public int downLoad(TcpClient tcpClient,String inncode,String innName,String lsh){
        int downFileSize = 0;
        //请求文件下载
        ReturnValue cmd4002 =fileCmdCls.cmd4002Data(innName,inncode);
        ReturnValue head = fileCmdCls.cmdHeadV2(lsh, MessageType.CMD4002.getType(),MessageType.CMD4002.getLength());

        String sendCmd = head.getCmd().getDataBlock()+cmd4002.getCmd().getDataBlock()+fileCmdCls.MAC("","");
        log.info("发送4002请求下载报文"+sendCmd);
        String cmd4006 = tcpClient.sendNormal(sendCmd);
        log.info("接收4002报文"+cmd4006);
        //解析4006 报文 获取需要下载的文件个数
        Cmd ret4006 = fileCmdCls.unPackNormal(cmd4006);
        int files = Integer.parseInt(ret4006.getBodyEntity().getCmdEntity().getFiles());

        log.info("解析4006；需要下载的文件个数"+files);
        //下载文件
        for(int i=0;i<files;i++){
            head = fileCmdCls.cmdHeadV2(lsh,MessageType.CMD4008.getType(),MessageType.CMD4008.getLength());
            ReturnValue cmd4008 = fileCmdCls.cmd4008Data("00");
            sendCmd = head.getCmd().getDataBlock()+cmd4008.getCmd().getDataBlock()+fileCmdCls.MAC("","");
            log.info("发送4008报文"+sendCmd);

            String cmd4003 = tcpClient.sendNormal(sendCmd);
            log.info("接收4008报文"+sendCmd);
            Cmd ret4003 =  fileCmdCls.unPackNormal(cmd4003);

            String fileName = ret4003.getBodyEntity().getCmdEntity().getFileName();
            String fileAbstract = ret4003.getBodyEntity().getCmdEntity().getFileAbstract();
            String fileSize =  ret4003.getBodyEntity().getCmdEntity().getFileSize();
            FileInfo fileinfo = new FileInfo();
            fileinfo.setFileName(fileName);
            fileinfo.setIsAnalysis(0);
            fileinfo.setFileSize(Integer.parseInt(fileSize));
            fileinfo.setHash(fileAbstract);
            fileinfo.setInnCode(inncode);
            fileinfo.setInnName(innName);
            fileinfo.setFlag(1);
            fileInfoService.addFileInfo(fileinfo);

            File file = new File(downFilePath+fileName.trim());
            FileWriter filewirte = null;
            try {
                filewirte = new FileWriter(file);
                PrintWriter print = new PrintWriter(filewirte);
                //已经接收的文件大小
                long resloveLen =0;
                head = fileCmdCls.cmdHeadV2(lsh,MessageType.CMD4005.getType(),MessageType.CMD4005.getLength());
                ReturnValue cmd4005 = fileCmdCls.cmd4005Data(String.valueOf(resloveLen),"00");
                sendCmd  = head.getCmd().getDataBlock()+cmd4005.getCmd().getDataBlock()+fileCmdCls.MAC("","");
                log.info("发送4005报文"+sendCmd);
               String txt4004And4008 = tcpClient.send4005(sendCmd);
               if("false".equals(txt4004And4008)){
                   continue;
               }

               Cmd cmd = fileCmdCls.unPack4008And4004(txt4004And4008);

               String lstflag = cmd.getBodyEntity().getCmdEntity().getLstflag();
                if("1".equals(lstflag)){
                    print.print(cmd.getBodyEntity().getCmdEntity().getDataBlock());
                    print.flush();
                    //如果文件已经传输完毕，则发送4008报文
                    head = fileCmdCls.cmdHeadV2(lsh,MessageType.CMD4008.getType(),MessageType.CMD4008.getLength());
                    cmd4008 = fileCmdCls.cmd4008Data("00");
                    sendCmd = head.getCmd().getDataBlock()+cmd4008.getCmd().getDataBlock()+fileCmdCls.MAC("","");
                    log.info("发送4008报文"+sendCmd);
                    String ret = tcpClient.sendNormal(sendCmd);
                    log.info("4008返回报文"+sendCmd);
                    cmd =  fileCmdCls.unPackNormal(ret);

                    if(cmd.getBodyEntity().getMsgType().equals(MessageType.CMD4007.getType())){
                        if(cmd.getBodyEntity().getCmdEntity().getResp().equals("**TEOF**")){
                            System.out.println("下载完成"+fileName);
                            //更改状态
                            fileinfo.setStatus(1);
                            fileInfoService.updateFileInfo(fileinfo);
                        }
                    }
                    continue;
                }else if("0".equals(lstflag)){
                    print.print(cmd.getBodyEntity().getCmdEntity().getDataBlock());
                    print.flush();
                }

                //如果不为1 则有后续报文，发送4008 获取4004 报文
                while(true){
                    head = fileCmdCls.cmdHeadV2(lsh,MessageType.CMD4008.getType(),MessageType.CMD4008.getLength());
                    cmd4008 = fileCmdCls.cmd4008Data("00");
                    sendCmd = head.getCmd().getDataBlock()+cmd4008.getCmd().getDataBlock()+fileCmdCls.MAC("","");
                    log.info("发送4008报文"+sendCmd);
                    String ret = tcpClient.sendNormal(sendCmd);
                    cmd = fileCmdCls.unPackNormal(ret);

                    lstflag = cmd.getBodyEntity().getCmdEntity().getLstflag();
                    if("1".equals(lstflag)){
                        print.print(cmd.getBodyEntity().getCmdEntity().getDataBlock());
                        print.flush();
                        head = fileCmdCls.cmdHeadV2(lsh,MessageType.CMD4008.getType(),MessageType.CMD4008.getLength());
                        cmd4008 = fileCmdCls.cmd4008Data("00");
                        sendCmd = head.getCmd().getDataBlock()+cmd4008.getCmd().getDataBlock()+fileCmdCls.MAC("","");
                        log.info("发送4008报文"+sendCmd);
                        ret = tcpClient.sendNormal(sendCmd);
                        log.info("4008返回报文"+sendCmd);
                        cmd =  fileCmdCls.unPackNormal(ret);

                        if(cmd.getBodyEntity().getMsgType().equals(MessageType.CMD4007.getType())){
                            if(cmd.getBodyEntity().getCmdEntity().getResp().equals("**TEOF**")){
                                System.out.println("下载完成"+fileName);
                                //更改状态
                                fileinfo.setStatus(1);
                                fileInfoService.updateFileInfo(fileinfo);

                            }
                        }
                        break;
                    }else if("0".equals(lstflag)){
                        print.print(cmd.getBodyEntity().getCmdEntity().getDataBlock());
                        print.flush();
                    }

                }

                print.close();
                filewirte.close();

            }catch(Exception e){
                log.error(e.getMessage());
            }

            if(i+1==files){
                downFileSize = 0;
            }else{
                downFileSize = i+1;
            }

        }

        tcpClient.CloseLink();
        return downFileSize;
    }

}
