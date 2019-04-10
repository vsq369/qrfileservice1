package com.cecurs.common;

import java.util.Random;

public class Tools {

    /**
     * data  源数据
     * dlen 该域实际长度
     * rex  需要补的字符 空格 或0
     */
    public static String rightopdata(String data,int dLen,String rex){
        byte[] b = data.getBytes();
        StringBuffer sb = new StringBuffer(data);
        int len = dLen-b.length;
        for(int i=0;i<len;i++){
            sb.append(rex);
        }
        return sb.toString();
    }
    public static String leftopdata(String data,int dLen,String rex){
        byte[] b = data.getBytes();
        StringBuffer sb = new StringBuffer();
        int len = dLen-b.length;
        for(int i=0;i<len;i++){
            sb.append(rex);
        }
        sb.append(data);
        return sb.toString();
    }
    public static byte[] andorData(byte[] rawdata,byte[] comdata){
        if(rawdata.length!=256 && comdata.length!=256){
            return null;
        }
        byte[] temp = new byte[256];
        for(int i=0;i<256;i++){
            temp[i]=(byte)(rawdata[i]^comdata[i]);
        }
        return temp;
    }

    public  static int SYSNUM=100;
    public static int SYSSER=100;
    public static int RECODE=100;
    public static int ZDNUM=100;
    public static String intTohex(int num,String type){
        return leftopdata(Integer.toHexString(num).toUpperCase()+type,8,"0");
    }

    public static String toHex(String data){
        double ye = Double.parseDouble(data);
        int temp = (int)(ye*100);
        return Integer.toString(temp, 16);
    }
    static String[] data = new String[]{"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    public static String getRandom(int len){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for(int i=0;i<len;i++){
            int num = random.nextInt(15);
            sb.append(data[num]);
        }

        return sb.toString();
    }



}
