package com.cecurs.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix="system.socket")
public class TcpClient {


    @Value("${ip}")
    private String ip;

    @Value("${port}")
    private int port;

    private Socket m_socket=null;
    public TcpClient(){
        try {
            m_socket = new Socket();
            m_socket.setSendBufferSize(2*1024);
            m_socket.setReceiveBufferSize(1107);
            m_socket.setSoTimeout(60000);
            m_socket.setTcpNoDelay(true);
            m_socket.connect(new InetSocketAddress(InetAddress
                    .getByName(ip), port), 60000);
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
        sb.append(new String(packLenByte) );
        int pLen = Integer.parseInt(new String(packLenByte));
        byte[] body= new byte[pLen];
        ReceiveMsgStr(body);
        sb.append(new String(body));
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
        sb.append(new String(packLenByte) );
        int pLen = Integer.parseInt(new String(packLenByte));
        byte[] body= new byte[pLen];
        ReceiveMsgStr(body);
        sb.append(new String(body));

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
                //接收4004
                packLenByte = new byte[4];
                ReceiveMsgStr(packLenByte);
                sb.append(new String(packLenByte) );
                pLen = Integer.parseInt(new String(packLenByte));
                body= new byte[pLen];
                ReceiveMsgStr(body);
                try {
                    sb.append(new String(body,"GB2312"));
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
