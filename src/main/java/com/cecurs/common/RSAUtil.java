package com.cecurs.common;

import java.io.ByteArrayOutputStream;
import java.util.Locale;
/**
 * @author  wangjc
 * 工具类
 */
public class RSAUtil {

	private static String hexits = "0123456789ABCDEF";
	/**
     * Convert a byte array to a hex encoded string
     *
     * @param block
     * byte array to convert to hexString
     * @return String representation of byte array
     */
     public static String toHex(byte[] block) 
     {
             StringBuffer buf = new StringBuffer();

             for (int i = 0; i < block.length; ++i) 
             {
                     buf.append(hexits.charAt((block[i] >>> 4) & 0xf)); /* hell: high 4 bits */
                     buf.append(hexits.charAt(block[i] & 0xf)); /* hell: low 4 bits */
             }

             return buf + "";
     }

     /**
     * Convert a String hex notation to a byte array
     *
     * @param s
     * string to convert
     * @return byte array
     */
 public static byte[] fromHex(String s) {
             s = s.toLowerCase(Locale.ENGLISH);
             byte[] b = new byte[(s.length() + 1) / 2];
             int j = 0;
             int h;
             int nibble = -1;

             for (int i = 0; i < s.length(); ++i) 
             {
                     h = hexits.indexOf(s.charAt(i));
                     if (h >= 0) 
                     {
                             if (nibble < 0) /* hell: nibble store high 4 bits */
                             {
                                     nibble = h;
                             } 
                             else 
                             {
                                     b[j++] = (byte) ((nibble << 4) + h);
                                     nibble = -1;
                             }
                     }
             }

             if (nibble >= 0) {
                     b[j++] = (byte) (nibble << 4);
             }

             if (j < b.length) {
                     byte[] b2 = new byte[j];
                     System.arraycopy(b, 0, b2, 0, j);
                     b = b2;
             }

             return b;
     }
 
 
 
 
 
 public static String toHexString(String s) {  
     String str = "";  
     for (int i = 0; i < s.length(); i++) {  
         int ch = (int) s.charAt(i);  
         String s4 = Integer.toHexString(ch);  
         str = str + s4;  
     }  
     return "0x" + str;//0x��ʾʮ������  
 }  

 //ת��ʮ�����Ʊ���Ϊ�ַ���  
 public static String toStringHex(String s) {  
     if ("0x".equals(s.substring(0, 2))) {  
         s = s.substring(2);  
     }  
     byte[] baKeyword = new byte[s.length() / 2];  
     for (int i = 0; i < baKeyword.length; i++) {  
         try {  
             baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(  
                     i * 2, i * 2 + 2), 16));  
         } catch (Exception e) {  
             e.printStackTrace();  
         }  
     }  

     try {  
         s = new String(baKeyword, "GB2312");//UTF-16le:Not  
     } catch (Exception e1) {  
         e1.printStackTrace();  
     }  
     return s;  
 }  

 public static void main(String[] args) throws Exception {  
     String str = "test";  
     printHexString(str.getBytes());  
 }  

 public static void printHexString(byte[] b) {  
     for (int i = 0; i < b.length; i++) {  
         String hex = Integer.toHexString(b[i] & 0xFF);  
         if (hex.length() == 1) {  
             hex = '0' + hex;  
         }  
         System.out.print(hex.toUpperCase());  
     }  
 }  

 private static String hexString="0123456789ABCDEF"; 
 /* 
 * ���ַ��������16��������,�����������ַ����������ģ� 
 */ 
 public static String encode(String str) 
 { 
 //����Ĭ�ϱ����ȡ�ֽ����� 
 byte[] bytes=str.getBytes(); 
 StringBuilder sb=new StringBuilder(bytes.length*2); 
 //���ֽ�������ÿ���ֽڲ���2λ16�������� 
 for(int i=0;i<bytes.length;i++) 
 { 
 sb.append(hexString.charAt((bytes[i]&0xf0)>>4)); 
 sb.append(hexString.charAt((bytes[i]&0x0f)>>0)); 
 } 
 return sb.toString(); 
 } 
 /* 
 * ��16�������ֽ�����ַ���,�����������ַ����������ģ� 
 */ 
 public static String decode(String bytes) 
 { 
 ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
 //��ÿ2λ16����������װ��һ���ֽ� 
 for(int i=0;i<bytes.length();i+=2) 
 baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1)))); 
 return new String(baos.toByteArray()); 
 } 

}
