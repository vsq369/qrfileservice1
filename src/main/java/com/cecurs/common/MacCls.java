package com.cecurs.common;


import com.cecurs.util.SessionKey;
/**
 * @author  wangjc
 * 工具类
 */
public class MacCls {

	public static void main(String[] args) throws Exception {
		String HexData = "fbfdac89699a3af277c5d0cd4d5a04eccbb1ae5a";
		String hexKey = "FBC85599A45EA47BB7B3C28865293D21";
		String vector= null;
	    System.out.println( StringCls.Bytes2HexString(HexData.toUpperCase().getBytes(), false));
		
		String mac = SessionKey.PBOC_3DES_MAC(hexKey, "0000000000000000", StringCls.Bytes2HexString(HexData.toUpperCase().getBytes(), false), 0);
		System.out.println(mac);
	}
	

	/**
	 * CBC模式下的mac计算
	 * @throws Exception 
	 */
	public static String mac_CBC(String HexData,String hexKey,String vector) throws Exception{
		int len = HexData.length();
		int arrLen = len / 16 + 1;
		String[] D = new String[arrLen];
		if (vector == null)
			vector = "0000000000000000";
		if (len % 16 == 0) {
			HexData += "8000000000000000";
		} else {
			HexData += "80";
			for (int i = 0; i < 15 - len % 16; i++) {
				HexData += "00";
			}
		}
		for (int i = 0; i < arrLen; i++) {
			D[i] = HexData.substring(i * 16, i * 16 + 16);
		}
		
		String I = xOr(D[0], vector);
		String kl = hexKey.substring(0, 16);
		String O = null;
		for (int i = 1; i < arrLen; i++) {
			//O = DES_1(I, kl, 0);
			//O = DESCipher.Encrypt(I, kl);//(I, kl, 0);
			O = DesChiperCBC.encrypt(kl, "0000000000000000", I);
			I = xOr(D[i], O);
		}
		//I = DESCipher.Encrypt3(I, hexKey);//(I, key, 0);
		I = DesChiperCBC.Encrypt3(I, hexKey, "0000000000000000");
		return I;
		
	}
	/**
	 * 将s1和s2做异或，然后返回
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 * @throws Exception 
	 */
	public static String xOr(String s1, String s2) throws Exception {
		int[] iArr = diffOr(string2Binary(s1), string2Binary(s2));
		return binary2ASC(intArr2Str(iArr));
	}
	
	
	/**
	 * 两个等长的数组做异或
	 * 
	 * @param source1
	 * @param source2
	 * @return
	 */
	public static int[] diffOr(int[] source1, int[] source2) {
		int len = source1.length;
		int[] dest = new int[len];
		for (int i = 0; i < len; i++) {
			dest[i] = source1[i] ^ source2[i];
		}
		return dest;
	}
	
	/**
	 * 将字符串转换成二进制数组
	 * 
	 * @param source :
	 *            16字节
	 * @return
	 * @throws Exception 
	 */
	public static int[] string2Binary(String source) throws Exception {
		int len = source.length();
		int[] dest = new int[len * 4];
		char[] arr = source.toCharArray();
		for (int i = 0; i < len; i++) {
			int t = 0;
			
			t = getIntByChar(arr[i]);
			
			String[] str = Integer.toBinaryString(t).split("");
			int k = i * 4 + 3;
			for (int j = str.length - 1; j > 0; j--) {
				dest[k] = Integer.parseInt(str[j]);
				k--;
			}
		}
		return dest;
	}
	
	/**
	 * 将十六进制A--F转换成对应数
	 * 
	 * @param ch
	 * @return
	 * @throws Exception
	 */
	public static int getIntByChar(char ch) throws Exception {
		char t = Character.toUpperCase(ch);
		int i = 0;
		switch (t) {
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			i = Integer.parseInt(Character.toString(t));
			break;
		case 'A':
			i = 10;
			break;
		case 'B':
			i = 11;
			break;
		case 'C':
			i = 12;
			break;
		case 'D':
			i = 13;
			break;
		case 'E':
			i = 14;
			break;
		case 'F':
			i = 15;
			break;
		default:
			throw new Exception("getIntByChar was wrong");
		}
		return i;
	}
	
	
	/**
	 * 将int类型数组拼接成字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String intArr2Str(int[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	/**
	 * 将二进制字符串转换成十六进制字符
	 * 
	 * @param s
	 * @return
	 */
	public static String binary2ASC(String s) {
		String str = "";
		int ii = 0;
		int len = s.length();
		// 不够4bit左补0
		if (len % 4 != 0) {
			while (ii < 4 - len % 4) {
				s = "0" + s;
			}
		}
		for (int i = 0; i < len / 4; i++) {
			str += binary2Hex(s.substring(i * 4, i * 4 + 4));
		}
		return str;
	}
	
	
	/**
	 * s位长度的二进制字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String binary2Hex(String s) {
		int len = s.length();
		int result = 0;
		int k = 0;
		if (len > 4)
			return null;
		for (int i = len; i > 0; i--) {
			result += Integer.parseInt(s.substring(i - 1, i)) * getXY(2, k);
			k++;
		}
		switch (result) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return "" + result;
		case 10:
			return "A";
		case 11:
			return "B";
		case 12:
			return "C";
		case 13:
			return "D";
		case 14:
			return "E";
		case 15:
			return "F";
		default:
			return null;
		}
	}

	/**
	 * 返回x的y次方
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static int getXY(int x, int y) {
		int temp = x;
		if (y == 0)
			x = 1;
		for (int i = 2; i <= y; i++) {
			x *= temp;
		}
		return x;
	}

}
