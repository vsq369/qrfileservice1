package com.cecurs.common;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author  wangjc
 * 工具类
 */
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

    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");//去重为空的情况
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断字符是否是中文
     *
     * @param c 字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


}
