package com.cecurs.qrfileservice.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.net.Socket;

@Component
@ConfigurationProperties(prefix="system.socket")
public class TcpClient {


    @Value("${ip}")
    private String ip;

    @Value("${port}")
    private String port;

    private Socket m_socket=null;
    public TcpClient(){
        m_socket  = new Socket();
    }


}
