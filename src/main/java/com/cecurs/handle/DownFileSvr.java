package com.cecurs.handle;

import com.cecurs.common.ReturnValue;
import com.cecurs.enums.MessageType;
import com.cecurs.service.FileInfoService;
import com.cecurs.util.TcpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Configuration
@Component
@Slf4j
public class DownFileSvr {

    @Value("file.downFilePath")
    private String downFilePath;

    @Resource
    private TcpClient tcpClient;

    @Resource
    private FileInfoService fileInfoService;

    @Resource
    private FileCmdCls  fileCmdCls;

    /**
     * 下载步骤 参考交通部文件传输规范
     * @return
     */
    public String downLoad(String inncode,String innName,String lsh){
        //请求文件下载
        ReturnValue cmd4002 =fileCmdCls.cmd4002Data(innName,inncode);
        ReturnValue head = fileCmdCls.cmdHeadV2(lsh, MessageType.CMD4002.getType(),MessageType.CMD4002.getLength());

        String sendCmd = head.getCmd().getDataBlock()+cmd4002.getCmd().getDataBlock()+fileCmdCls.MAC("","");
        log.info("发送4002请求下载报文"+sendCmd);
        tcpClient.sendText(sendCmd);
        byte[] recive = tcpClient.receiveMsg();
        String cmd4006 = new String(recive);
        log.info("接收4006报文"+cmd4006);
        //解析4006 报文 获取需要下载的文件个数
        ReturnValue ret4006 = fileCmdCls.cmd4006Decompose(cmd4006);
        int files = 0;
        if("00".equals(ret4006.getResult())){
            files = Integer.parseInt(ret4006.getCmd().getFiles());
        }
        log.info("解析4006；需要下载的文件个数"+files);
        //初始文件大小
        int downFileSize = 0;
        //下载文件
        for(int i=0;i<files;i++){
            head = fileCmdCls.cmdHeadV2(lsh,MessageType.CMD4008.getType(),MessageType.CMD4008.getLength());
            ReturnValue cmd4008 = fileCmdCls.cmd4008Data("00");
        }

        return "";

    }

}
