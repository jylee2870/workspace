package com.jylee.crypto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class HashUtil {
    public static Log logger = LogFactory.getLog(CipherAesUtil.class);
    
    final static String ALGORITHM_MD5 = "MD5";
    final static String ALGORITHM_SHA_1 = "SHA-1";
    final static String ALGORITHM_SHA_256 = "SHA-256";

    public static String MD5(String message)  {
        return hashString(message, ALGORITHM_MD5);
    }
 
    public static String SHA1(String message)  {
        return hashString(message, ALGORITHM_SHA_1);
    }
 
    public static String SHA256(String message) {
        return hashString(message, ALGORITHM_SHA_256);
    }
 
    private static String hashString(String message, String algorithm) {
        String hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
            hash = convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException ax) {
            logger.error("error ***** "+ax.getMessage(), ax);
        } catch (UnsupportedEncodingException ex) {
            logger.error("error ***** "+ex.getMessage(), ex);
        }
        return hash;
    }
 
    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }
    
    public static void main(String args[]) {
        try {
            // String orgStr = "890721-1234567";
            // String str = "#freesia4321!";
            // String str = "freesia1234";
            String str = "f1";
            
            System.out.println(MD5(str));
            System.out.println(SHA1(str));
            System.out.println(SHA256(str));
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
