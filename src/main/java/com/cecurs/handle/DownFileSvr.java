package com.cecurs.handle;

import com.cecurs.service.FileInfoService;
import com.cecurs.util.TcpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DownFileSvr {

    @Value("file.down.downFilePath")
    private String downFilePath;

    @Autowired
    private TcpClient tcpClient;

    @Autowired
    private FileInfoService fileInfoService;


    public String downLoad(){

        return "";

    }

}
