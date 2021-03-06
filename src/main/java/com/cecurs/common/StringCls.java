package com.cecurs.common;

import java.io.UnsupportedEncodingException;
/**
 * @author  wangjc
 * 工具类
 */
public class StringCls {
	
	public static String StrToHex(String str,boolean mSpace){
		return StrToHex(str,mSpace,"gb2312");
	}
	
	public static String StrToHex(String str,boolean mSpace,String charsetname) 
	{ 
		if(str == null) return null; 
		try {
			return Bytes2HexString(str.getBytes(charsetname),mSpace);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
//		String content = str; 
//		String Digital="0123456789ABCDEF"; 
//		StringBuffer sb=new StringBuffer(""); 
//		byte[] bs=content.getBytes(); 		
//		int bit; 
//		for(int i=0;i<bs.length;i++){ 
//			bit=(bs[i]&0x0f0)>>4; 
//			sb.append(Digital.substring(bit,bit+1)); 
//			bit=bs[i]&0x0f; 
//			sb.append(Digital.substring(bit,bit+1)); 
//			if(mSpace)sb.append(" ");
//		} 
//		return sb.toString(); 
		
	} 
	
	public static String HexToStr(String str){
		return HexToStr(str,"gb2312");
	}

	public static String HexToStr(String str,String charsetname) 
	{ 
		if(str == null) return null; 
		try {
			return (new String(HexString2Bytes(str,false),charsetname));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
//		String b= str; 
//		String Digital="0123456789ABCDEF"; 
//		byte[] bytes=new byte[b.length()/2]; 
//		int temp; 
//		for(int i=0;i<bytes.length;i++){ 
//			temp=Digital.indexOf(b.substring(2*i,2*i+1))*16; 
//			temp+=Digital.indexOf(b.substring(2*i+1,2*i+2)); 
//			bytes[i]=(byte)(temp&0xff); 
//		} 
//		return (new String(bytes)); 
	} 
	
	public static String Bytes2HexString(byte[] b,boolean mSpace) { 
		String ret = ""; 
		for (int i = 0; i < b.length; i++) { 
			String hex = Integer.toHexString(b[i] & 0xFF); 
			if (hex.length() == 1) { 
			hex = '0' + hex; 
			} 
			ret += hex.toUpperCase(); 
			if(mSpace)ret+=" ";
		} 
		return ret; 
	}
	

	public static byte[] HexString2Bytes(String src,boolean mSpace){ 
		if(mSpace)src=src.replace(" ", "");
		byte[] ret = new byte[src.length()/2]; 
		byte[] tmp = src.getBytes(); 
		for(int i=0; i<ret.length; i++){ 
		ret[i] = uniteBytes(tmp[i*2], tmp[i*2+1]); 
		} 
		return ret; 
	} 

	public static byte uniteBytes(byte src0, byte src1)
    {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
        _b0 = (byte)(_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
        byte ret = (byte)(_b0 | _b1);
        return ret;
    }
	
	public static int ByteToUint(byte b){
		int i=(int)b;
		if(i<0)i=256+i;
		return i;		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s=StringCls.StrToHex("FFEGH", false);
		s=StringCls.StrToHex("FFH", false, "gb2312");
		System.out.println("========"+s);
	}

}
