package com.cecurs.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

@Component
@Slf4j
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
            m_socket.setSoTimeout(60000);
            m_socket.setReceiveBufferSize(2*1024);
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
        try{
            return sendMsg(txt.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
       return false;
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

    public void  ReceiveMsgStr(byte[] buffer){
        DataInputStream in = null;
        try {
            in = new DataInputStream(m_socket.getInputStream());
            in.read(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 常规发包函数
     * @param sendData
     * @return
     */
    public String sendNormal(String sendData){
        StringBuffer sb = new StringBuffer();
        sendText(sendData);
        byte[] packLenByte = new byte[4];
        ReceiveMsgStr(packLenByte);
        try {
            sb.append(new String(packLenByte,"ISO8859-1") );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int pLen = 0;
        try {
            pLen = Integer.parseInt(new String(packLenByte,"ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] body= new byte[pLen];
        ReceiveMsgStr(body);
        try {
            sb.append(new String(body,"ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 发送特殊
     * @param sendData
     * @return
     */
    public String send4005(String sendData){
        StringBuffer sb = new StringBuffer();
        sendText(sendData);
        //接收4008
        byte[] packLenByte = new byte[4];
        ReceiveMsgStr(packLenByte);
        try {
            sb.append(new String(packLenByte,"ISO8859-1") );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int pLen = Integer.parseInt(new String(packLenByte));
        byte[] body= new byte[pLen];
        ReceiveMsgStr(body);
        try {
            sb.append(new String(body,"ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        MsgSplit msg = new MsgSplit(sb.toString());
        msg.split(MsgSplit.FieldType.FIX,4);
        msg.split(MsgSplit.FieldType.FIX,12);
        msg.split(MsgSplit.FieldType.FIX,1);
        msg.split(MsgSplit.FieldType.FIX,1);

        msg.split(MsgSplit.FieldType.FIX,2);//版本
        String msgType = msg.split(MsgSplit.FieldType.FIX,4);//type
        String resp =  msg.split(MsgSplit.FieldType.FIX,2);//type
        if(msgType.equals("4008")){
            if("00".equals(resp)){

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //接收4004
                packLenByte = new byte[4];
                ReceiveMsgStr(packLenByte);
                try {
                    sb.append(new String(packLenByte,"ISO8859-1") );
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                pLen = Integer.parseInt(new String(packLenByte));
                body= new byte[pLen];
                ReceiveMsgStr(body);
                try {
                    sb.append(new String(body,"ISO8859-1"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return sb.toString();
            }else {
                return "false";
            }
        }
        return "false";
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
