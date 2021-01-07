package com.jylee.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jylee.util.Base64;

// AES128 방식임. 
public class CipherAesUtil {
    public static Log logger = LogFactory.getLog(CipherAesUtil.class);
	
	private static String AES_ALGORITHM = "AES";
	final static String AES_SEC_KEY = "_FREESIA_AES_128";
	private static String CHARSET_UTF8 = "UTF-8";
	
	public static Cipher encCipher() {
        Cipher c = null;
        try {
            byte[] keyData = AES_SEC_KEY.getBytes();
            
            SecretKey secureKey = new SecretKeySpec(keyData, AES_ALGORITHM);
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(AES_SEC_KEY.getBytes()));
        } catch(Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
        }
        return c;
    }
    
    public static Cipher decCipher() {
        Cipher c = null;
        try {
            byte[] keyData = AES_SEC_KEY.getBytes();
            
            SecretKey secureKey = new SecretKeySpec(keyData, AES_ALGORITHM);
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(AES_SEC_KEY.getBytes(CHARSET_UTF8)));
        } catch(Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
        }
        return c;
    }
	
	public static String aesEncrypt(String orgStr) throws Exception {
		byte[] keyData = AES_SEC_KEY.getBytes();
		
		SecretKey secureKey = new SecretKeySpec(keyData, AES_ALGORITHM);
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(AES_SEC_KEY.getBytes()));
		
		byte[] encrypted = c.doFinal(orgStr.getBytes(CHARSET_UTF8));
		
		return new String(Base64.encode(encrypted, Base64.NO_WRAP), "UTF8");
	}
	
	public static String aesDecrypt(String encStr) throws Exception {
		byte[] keyData = AES_SEC_KEY.getBytes();
		
		SecretKey secureKey = new SecretKeySpec(keyData, AES_ALGORITHM);
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(AES_SEC_KEY.getBytes(CHARSET_UTF8)));
		
		byte[] byteStr = Base64.decode(encStr.getBytes(), Base64.NO_WRAP); 
		 
		return new String(c.doFinal(byteStr),"UTF-8");
		
	}
	
	public static String aesEncrypt(String orgStr, Cipher c) throws Exception {
        
        byte[] encrypted = c.doFinal(orgStr.getBytes(CHARSET_UTF8));
        
        return new String(Base64.encode(encrypted, Base64.NO_WRAP), "UTF8");
    }
    
    public static String aesDecrypt(String encStr, Cipher c) {
        try {
            byte[] byteStr = Base64.decode(encStr.getBytes(), Base64.NO_WRAP); 
             
            return new String(c.doFinal(byteStr),"UTF-8");
        } catch(Exception e) {
            // e.printStackTrace();
            logger.error("error ***** "+e.getMessage(), e);
            return encStr;
        }
    }
	
	public static void main(String args[]) {
		
		try {
		    // String orgStr = "890721-1234567";
		    // String orgStr = "#freesia4321!";
		    // String orgStr = "freesia1234";
		    String orgStr = "bom1";
		    
			String aesEncStr = CipherAesUtil.aesEncrypt(orgStr);
			System.out.println("aesEncStr:"+aesEncStr);
			String aesDecStr = CipherAesUtil.aesDecrypt(aesEncStr);
			System.out.println("aesDecStr:"+aesDecStr);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}





