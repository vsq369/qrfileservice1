package com.cecurs.util;


import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

@Component
//@ConfigurationProperties(prefix="system.socket")
public class TcpClient {


    //@Value("${ip}")
    private String ip = "114.242.105.153";

    //@Value("${port}")
    private int port = 10011;

    private Socket m_socket=null;
    public TcpClient(){
        try {
            m_socket = new Socket();
            m_socket.setSendBufferSize(2*1024);
            m_socket.setReceiveBufferSize(1107);
            m_socket.setSoTimeout(60000);

            m_socket.setTcpNoDelay(true);
            m_socket.connect(new InetSocketAddress(InetAddress.getByName(ip), port), 60000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public boolean sendMsg(byte[] buf){
        try {
            DataOutputStream doc = new DataOutputStream(m_socket.getOutputStream());
            doc.write(buf);
            doc.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean sendText(String txt){
        return sendMsg(txt.getBytes());
    }

    public byte[] receiveMsg(){
        try{
            DataInputStream in = new DataInputStream(m_socket.getInputStream());
            int itime=0;
            byte[] byteMac=null;
            do{
                Thread.sleep(100);
                itime++;
                int count = in.available();
                byteMac=new byte[count];
            }while(byteMac.length==0 && itime<600);
            in.read(byteMac, 0, byteMac.length);
            return byteMac;
        }catch (Exception e) {
            return null;
        }
    }

    public boolean CloseLink(){
        if (m_socket != null) {
            try {

                m_socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }




}
