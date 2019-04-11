package com.cecurs.common;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @author  wangjc
 * 加密
 */
public class DESCipher {

	/**  
     * 鐎靛棝鎸�  
     */  
    public static final String KEY = "12345678";   
  
    private final static String DES = "DES";   
  
    //DES/CBC/NoPadding
    //DES/ECB/PKCS5Padding
    //DES
    //DES/ECB/NoPadding
    private final static String DESPadding = "DES/ECB/NoPadding";
    /**  
     * 閸旂姴鐦�  
     *   
     * @param src  
     *            閺勫孩鏋�(鐎涙濡�)  
     * @param key  
     *            鐎靛棝鎸滈敍宀勬毐鎼达箑绻�妞ょ粯妲�8閻ㄥ嫬锟介弫锟� 
     * @return 鐎靛棙鏋�(鐎涙濡�)  
     * @throws Exception  
     */  
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {   
        // DES缁犳纭剁憰浣圭湴閺堝绔存稉顏勫讲娣団�叉崲閻ㄥ嫰娈㈤張鐑樻殶濠э拷  
        SecureRandom sr = new SecureRandom();   
        // 娴犲骸甯慨瀣槕閸栨瑦鏆熼幑顔煎灡瀵ょ瘚ESKeySpec鐎电钖�   
        DESKeySpec dks = new DESKeySpec(key);   
        // 閸掓稑缂撴稉锟介嚋鐎靛棗瀵靛銉ュ范閿涘瞼鍔ч崥搴ｆ暏鐎瑰啯濡窪ESKeySpec鏉烆剚宕查幋锟�  
        // 娑擄拷閲淪ecretKey鐎电钖�   
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);   
        SecretKey securekey = keyFactory.generateSecret(dks);   
        // Cipher鐎电钖勭�圭偤妾�瑰本鍨氶崝鐘茬槕閹垮秳缍�   
        Cipher cipher = Cipher.getInstance(DESPadding);   
        // 閻€劌鐦戦崠娆忓灥婵瀵睠ipher鐎电钖�   
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);   
        // 閻滄澘婀敍宀冨箯閸欐牗鏆熼幑顔艰嫙閸旂姴鐦�   
        // 濮濓絽绱￠幍褑顢戦崝鐘茬槕閹垮秳缍�   
        return cipher.doFinal(src);   
    }   
  
    /**  
     * 鐟欙絽鐦�  
     *   
     * @param src  
     *            鐎靛棙鏋�(鐎涙濡�)  
     * @param key  
     *            鐎靛棝鎸滈敍宀勬毐鎼达箑绻�妞ょ粯妲�8閻ㄥ嫬锟介弫锟� 
     * @return 閺勫孩鏋�(鐎涙濡�)  
     * @throws Exception  
     */  
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {   
        // DES缁犳纭剁憰浣圭湴閺堝绔存稉顏勫讲娣団�叉崲閻ㄥ嫰娈㈤張鐑樻殶濠э拷  
        SecureRandom sr = new SecureRandom();   
        // 娴犲骸甯慨瀣槕閸栨瑦鏆熼幑顔煎灡瀵よ桨绔存稉鐙SKeySpec鐎电钖�   
        DESKeySpec dks = new DESKeySpec(key);   
        // 閸掓稑缂撴稉锟介嚋鐎靛棗瀵靛銉ュ范閿涘瞼鍔ч崥搴ｆ暏鐎瑰啯濡窪ESKeySpec鐎电钖勬潪顒佸床閹达拷  
        // 娑擄拷閲淪ecretKey鐎电钖�   
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);   
        SecretKey securekey = keyFactory.generateSecret(dks);   
        // Cipher鐎电钖勭�圭偤妾�瑰本鍨氱憴锝呯槕閹垮秳缍�   
        Cipher cipher = Cipher.getInstance(DESPadding);   
        // 閻€劌鐦戦崠娆忓灥婵瀵睠ipher鐎电钖�   
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);   
        
        // 閻滄澘婀敍宀冨箯閸欐牗鏆熼幑顔艰嫙鐟欙絽鐦�   
        // 濮濓絽绱￠幍褑顢戠憴锝呯槕閹垮秳缍�   
        return cipher.doFinal(src);   
    }   
  
    /**  
     * 閸旂姴鐦�  
     *   
     * @param src  
     *            閺勫孩鏋�(鐎涙濡�)  
     * @return 鐎靛棙鏋�(鐎涙濡�)  
     * @throws Exception  
     */  
    public static byte[] encrypt(byte[] src) throws Exception {   
        return encrypt(src, KEY.getBytes());   
    }   
  
    /**  
     * 鐟欙絽鐦�  
     *   
     * @param src  
     *            鐎靛棙鏋�(鐎涙濡�)  
     * @return 閺勫孩鏋�(鐎涙濡�)  
     * @throws Exception  
     */  
    public static byte[] decrypt(byte[] src) throws Exception {   
        return decrypt(src, KEY.getBytes());   
    }   
  
    /**  
     * 閸旂姴鐦�  
     *   
     * @param src  
     *            閺勫孩鏋�(鐎涙顑佹稉锟�  
     * @return 鐎靛棙鏋�(16鏉╂稑鍩楃�涙顑佹稉锟�  
     * @throws Exception  
     */  
    public final static String encrypt(String src) {   
        try {   
            return byte2hex(encrypt(src.getBytes(), KEY.getBytes()));   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return null;   
    }   
  
    /**  
     * 鐟欙絽鐦�  
     *   
     * @param src  
     *            鐎靛棙鏋�(鐎涙顑佹稉锟�  
     * @return 閺勫孩鏋�(鐎涙顑佹稉锟�  
     * @throws Exception  
     */  
    public final static String decrypt(String src) {   
        try {   
            return new String(decrypt(hex2byte(src.getBytes()), KEY.getBytes()));   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return null;   
    }   
  
    /**  
     * 閸旂姴鐦�  
     *   
     * @param src  
     *            閺勫孩鏋�(鐎涙濡�)  
     * @return 鐎靛棙鏋�(16鏉╂稑鍩楃�涙顑佹稉锟�  
     * @throws Exception  
     */  
    public static String encryptToString(byte[] src) throws Exception {   
        return encrypt(new String(src));   
    }   
  
    /**  
     * 鐟欙絽鐦�  
     *   
     * @param src  
     *            鐎靛棙鏋�(鐎涙濡�)  
     * @return 閺勫孩鏋�(鐎涙顑佹稉锟�  
     * @throws Exception  
     */  
    public static String decryptToString(byte[] src) throws Exception {   
        return decrypt(new String(src));   
    }   
  
    public static String byte2hex(byte[] b) {   
        String hs = "";   
        String stmp = "";   
        for (int n = 0; n < b.length; n++) {   
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)   
                hs = hs + "0" + stmp;   
            else  
                hs = hs + stmp;   
        }   
        return hs.toUpperCase();   
    }   
  
    public static byte[] hex2byte(byte[] b) {   
        if ((b.length % 2) != 0)   
            throw new IllegalArgumentException("");   
        byte[] b2 = new byte[b.length / 2];   
        for (int n = 0; n < b.length; n += 2) {   
            String item = new String(b, n, 2);   
            b2[n / 2] = (byte) Integer.parseInt(item, 16);   
        }   
        return b2;   
    }   
    
    public static String Encrypt(String hexsrc,String hexkey){
    	try{
	    	byte[] bresult= encrypt( StringCls.HexString2Bytes(hexsrc, false) ,StringCls.HexString2Bytes(hexkey, false) );
	    	return StringCls.Bytes2HexString(bresult, false);
    	}catch(Exception e){
    		return null;
    	}
    }
    
    public static String Decrypt(String hexsrc,String hexkey){
    	try{
	    	byte[] bresult= decrypt( StringCls.HexString2Bytes(hexsrc, false) ,StringCls.HexString2Bytes(hexkey, false) );
	    	return StringCls.Bytes2HexString(bresult, false);
    	}catch(Exception e){
    		return null;
    	}
    }
    //加密
    public static String Encrypt3(String hexsrc,String hexkey){
    	try{
	    	byte[] bresult= encrypt( StringCls.HexString2Bytes(hexsrc, false) ,StringCls.HexString2Bytes(hexkey.substring(0,16), false) );
	    	bresult=decrypt(bresult,StringCls.HexString2Bytes(hexkey.substring(16,32), false));
	    	bresult=encrypt(bresult,StringCls.HexString2Bytes(hexkey.substring(0,16), false));
	    	return StringCls.Bytes2HexString(bresult, false);
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    //解密
    public static String Decrypt3(String hexsrc,String hexkey){
    	try{
	    	byte[] bresult= decrypt( StringCls.HexString2Bytes(hexsrc, false) ,StringCls.HexString2Bytes(hexkey.substring(0,16), false) );
	    	bresult=encrypt(bresult,StringCls.HexString2Bytes(hexkey.substring(16,32), false));
	    	bresult=decrypt(bresult,StringCls.HexString2Bytes(hexkey.substring(0,16), false));
	    	return StringCls.Bytes2HexString(bresult, false);
    	}catch(Exception e){
    		return null;
    	}
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*byte[] key={ (byte) 0xB9,
				(byte) 0xD3, (byte) 0xB5, (byte) 0xD7, (byte) 0xAB, (byte) 0xC3,
				(byte) 0xC0, (byte) 0xCD, (byte) 0xB3, (byte) 0xEE, (byte) 0xF2,
				(byte) 0xAC, (byte) 0xCF, (byte) 0xCC, (byte) 0xB6, (byte) 0xD0 };*/
		String key = "b9d3b5d7abc3c0cdb3eef2accfccb6d0";
		String s = "4430021010000000";
		System.out.println(Decrypt3(s,key));
		System.out.println(Encrypt3("4430021010000000", key));
//		System.out.println("閸旓拷+Encrypt3("67C6B70FC894439D67C6B70FC894439D","56A0DFCC8DB885AB88FB640A4084305F"));
//		System.out.println("鐟欙拷+Decrypt3("67C6B70FC894439D67C6B70FC894439D","56A0DFCC8DB885AB88FB640A4084305F"));
// TODO Auto-generated method stub
//		try {
//			String Str="906824";
//			String Str2="F7B90C857FD93EE6";
//			String key="20110501";
//			key+="0000000000000000";
//			key=key.substring(0,16);
//			
//			while(Str.length()%16!=0){
//				Str+="00";
//			}
//			
////			while(Str2.length()%16!=0){
////				Str2+="00";
////			}
//			
//			
//			System.out.println(byte2hex(encrypt(MessageCenter.HexString2Bytes(Str, false),
//					MessageCenter.HexString2Bytes(key, false))));
//			System.out.println(byte2hex(decrypt(
//					new String(MessageCenter.HexString2Bytes(Str2,false),"utf-8").getBytes(),
//					//MessageCenter.HexString2Bytes(Str2, false),
//					//Base64.decode(MessageCenter.HexString2Bytes(Str2, false)),
//					MessageCenter.HexString2Bytes(key, false))));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
