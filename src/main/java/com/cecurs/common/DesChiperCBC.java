package com.cecurs.common;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class DesChiperCBC {
	 private final static String DES = "DES";
	
	 //加密模式
	 private final static String DESPadding = "DES/CBC/NoPadding";
	 
	 /**
	  * 加密
	  * @param src
	  * @param key
	  * @param iv
	  * @return
	  * @throws Exception
	  */
	   public static byte[] encrypt(byte[] src, byte[] key,byte[] iv) throws Exception {   
		   //SecureRandom r=new SecureRandom(iv);
	        DESKeySpec dks = new DESKeySpec(key);   
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);   
	        SecretKey securekey = keyFactory.generateSecret(dks);   //key
	        Cipher cipher = Cipher.getInstance(DESPadding);  
	        
	       IvParameterSpec ivSpec = new IvParameterSpec(iv);//iv
	        cipher.init(Cipher.ENCRYPT_MODE, securekey,ivSpec);   
	        return cipher.doFinal(src);   
	    }   
	   /**
	    * 解密
	    * @param src
	    * @param key
	    * @param iv
	    * @return
	    * @throws Exception
	    */
	   public static byte[] decrypt(byte[] src, byte[] key,byte[] iv) throws Exception {   
		 //  SecureRandom r=new SecureRandom(iv);
	        DESKeySpec dks = new DESKeySpec(key);   
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);   
	        SecretKey securekey = keyFactory.generateSecret(dks);   
	        Cipher cipher = Cipher.getInstance(DESPadding);   
	        IvParameterSpec ivSpec = new IvParameterSpec(iv);//iv
	        cipher.init(Cipher.DECRYPT_MODE, securekey, ivSpec);   
	        return cipher.doFinal(src);   
	    }   
	   
	   //加密
	   public static String encrypt(String key,String iv,String src){
		   byte[] keyByte = StringCls.HexString2Bytes(key, false);
		   byte[] ivByte = StringCls.HexString2Bytes(iv, false);
		   byte[] srcByte = StringCls.HexString2Bytes(src, false);
		   byte[] result = null;
			try {
				result = encrypt(srcByte, keyByte, ivByte);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		  return StringCls.Bytes2HexString(result, false);
	   }
	   
	   //解密
	   public static String decrypt(String key,String iv,String src){
		   byte[] keyByte = StringCls.HexString2Bytes(key, false);
		   byte[] ivByte = StringCls.HexString2Bytes(iv, false);
		   byte[] srcByte = StringCls.HexString2Bytes(src, false);
		   byte[] result = null;
			try {
				result = decrypt(srcByte, keyByte, ivByte);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		  return StringCls.Bytes2HexString(result, false);
	   }
	   
	   
	   /**
	    * 
	    * @param hexsrc
	    * @param hexkey
	    * @param iv
	    * @return
	    * CBC加密算法流程
	    * 1/k1=left(key,8) k2=right(key,8)
	    * ek1(left(data,8),k1,iv)
	    * dk2(left(data,8),k2,iv)
	    * l =ek3(left(data,8),k1,iv)
	    * iv = left(ek3,8)
	     *ek1(right(data,8),k1,iv)
	    * dk2(right(data,8),k2,iv)
	    * r = ek3(right(data,8),k1,iv)
	    * l+r
	    */
	   //加密
	    public static String Encrypt3(String hexsrc,String hexkey,String iv){
	    	StringBuffer sb = new StringBuffer();
	    	int speed = hexsrc.length()/16;
	    	
	    	byte[] ivbresult= StringCls.HexString2Bytes(iv, false);
	    	byte[] bresult = null;
	    	try{
	    		for(int i=0;i<speed;i++){
	    			 bresult= encrypt( StringCls.HexString2Bytes(hexsrc.substring(i*16,i*16+16), false) ,
			    			    StringCls.HexString2Bytes(hexkey.substring(0,16), false),
			    			    ivbresult);
	    			 bresult=decrypt(bresult,
	 		    			StringCls.HexString2Bytes(hexkey.substring(16,32), false),
	 		    			ivbresult );
	    				bresult=encrypt(bresult,
	    		    			StringCls.HexString2Bytes(hexkey.substring(0,16), false),
	    		    			ivbresult);
	    				ivbresult = bresult;
	    				sb.append(StringCls.Bytes2HexString(bresult, false));
	    		}
		    	return sb.toString();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		return null;
	    	}
	    }
	    //解密
	    /**
	     * 
	     * @param hexsrc
	     * @param hexkey
	     * @param iv
	     * @return
	     * CBC解密算法流程
	    * 1/k1=left(key,8) k2=right(key,8)
	    * dk1(left(data,8),k1,iv)
	    * ek2(left(data,8),k2,iv)
	    * l =dk3(left(data,8),k1,iv)
	    * iv = left(data,8)
	     *dk1(right(data,8),k1,iv)
	    * ek2(right(data,8),k2,iv)
	    * r = dk3(right(data,8),k1,iv)
	    * l+r
	     */
	    public static String Decrypt3(String hexsrc,String hexkey,String iv){
	    	StringBuffer sb = new StringBuffer();
	    	
	    	int speed = hexsrc.length()/16;
	    		byte[] ivbresult= StringCls.HexString2Bytes(iv, false);
		    	byte[] bresult = null;
		    	try{
		    		for(int i=0;i<speed;i++){
		    			 bresult= decrypt( StringCls.HexString2Bytes(hexsrc.substring(i*16,i*16+16), false) ,
				    			    StringCls.HexString2Bytes(hexkey.substring(0,16), false),
				    			    ivbresult);
		    			 bresult=encrypt(bresult,
		 		    			StringCls.HexString2Bytes(hexkey.substring(16,32), false),
		 		    			ivbresult );
		    				bresult=decrypt(bresult,
		    		    			StringCls.HexString2Bytes(hexkey.substring(0,16), false),
		    		    			ivbresult);
		    				ivbresult = StringCls.HexString2Bytes(hexsrc.substring(i*16,i*16+16), false);
		    				sb.append(StringCls.Bytes2HexString(bresult, false));
		    		}
			    	return sb.toString();
	    		
		    /*	byte[] bresult= decrypt( StringCls.HexString2Bytes(hexsrc.substring(0,16), false) ,StringCls.HexString2Bytes(hexkey.substring(0,16), false),StringCls.HexString2Bytes(iv, false)  );
		    	bresult=encrypt(bresult,StringCls.HexString2Bytes(hexkey.substring(16,32), false),StringCls.HexString2Bytes(iv, false) );
		    	bresult=decrypt(bresult,StringCls.HexString2Bytes(hexkey.substring(0,16), false),StringCls.HexString2Bytes(iv, false) );
		    	sb.append(StringCls.Bytes2HexString(bresult, false));
		    	byte[] reslutIv = StringCls.HexString2Bytes(hexsrc.substring(0,16), false);
		    	
		    	   bresult= decrypt( StringCls.HexString2Bytes(hexsrc.substring(16), false),
		    			 StringCls.HexString2Bytes(hexkey.substring(0,16), false),reslutIv );
			    	bresult=encrypt(bresult,StringCls.HexString2Bytes(hexkey.substring(16,32), false),reslutIv);
			    	bresult=decrypt(bresult,StringCls.HexString2Bytes(hexkey.substring(0,16), false),reslutIv);
			    	sb.append(StringCls.Bytes2HexString(bresult, false));
		    	return sb.toString();*/
	    	}catch(Exception e){
	    		return null;
	    	}
	    } 
	   
	   public static void main(String[] args) {
		  
		  /* byte[] key = new byte[]{(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF};
		   byte[] iv = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
		   byte[] data = new byte[]{0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,};
		   
		   try {
			byte[] mm =  encrypt(data, key, iv);
			System.out.println(StringCls.Bytes2HexString(mm, false));
			mm = decrypt(mm, key, iv);
			System.out.println(StringCls.Bytes2HexString(mm, false));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		   
		  // String key = "b9d3b5d7abc3c0cdb3eef2accfccb6d0";
		   String key = "404142434445464748494A4B4C4D4E4F";
		   String iv = "0000000000000000";
		   String src = "0200921AFDB0C46501020304050606088000000000000000";
		  /* String mm = encrypt(key, iv, src);
		   System.out.println("-->"+mm);
		   String mw = decrypt(key, iv, mm);
		   System.out.println(mw);*/
		   
		   String des3 = Encrypt3(src, key, iv);
		   System.out.println("des3:"+des3);
		   String mw3 = Decrypt3(des3, key, iv);
		   System.out.println("3des:"+mw3);
		   
		  // 38ECBEF46B6345A 2AEA639A484FF749E
		   //38ECBEF46B6345A 236439DF27A6ECE2D
		  /* String key = "b9d3b5d7abc3c0cdb3eef2accfccb6d0";
			String s = "4430021010000000";
			System.out.println(Decrypt3(Encrypt3("4430021010000000", key),key));
			System.out.println(Encrypt3("4430021010000000", key));*/
	   }
	   
}
