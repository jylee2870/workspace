package com.jylee.crypto;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;

import javax.crypto.Cipher;

import com.jylee.util.Base64;
import com.jylee.util.HexString;

public class DefaultAsymmetricEncryption implements IAsymmetricEncryption {
	
	ICryptPublicKey cryptPublicKey;
	
	public DefaultAsymmetricEncryption(ICryptPublicKey cryptPublicKey) {
		this.cryptPublicKey = cryptPublicKey;
	}

	// @Override
	/**
	 * 비대칭 암호화를 처리한다.
	 * @param originalData byte 배열 
	 * @return 암호화된 바이트 배열 
	 */
	public byte[] encrypt(byte[] originalBytes) throws GeneralSecurityException {
		
		if ( originalBytes == null ) 
			throw new GeneralSecurityException("Invalid parameter");
			
		if ( cryptPublicKey == null ) return null;
		PublicKey publicKey = cryptPublicKey.getKey();
		
		if (publicKey == null) return null;
		Cipher cipher = null;
		if (cryptPublicKey.getProvider() != null) {
			cipher = Cipher.getInstance(publicKey.getAlgorithm(), cryptPublicKey.getProvider());	
		} else {
			cipher = Cipher.getInstance(publicKey.getAlgorithm());
		}
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	
		return cipher.doFinal(originalBytes);	
		
	}
	
	// @Override
	/**
	 * 비대칭 암호화 처리.
	 * @param orignalString 문자열  
	 * @return 암호하되어 base64로 인코딩된 문자열 반환 
	 */
	public String encryptToBase64(String orignalString) {
		
		if (orignalString != null) {
			
			byte[] encryptedBytes = null;
			try {

				encryptedBytes = encrypt(orignalString.getBytes("UTF-8"));
				
			} catch (UnsupportedEncodingException e1) {
				// loging 필요..[TODO]
				e1.printStackTrace();
				return null;
			} catch (GeneralSecurityException e) {
				// loging 필요..[TODO]
				e.printStackTrace();
				return null;
			}
			
			if (encryptedBytes != null)
				return Base64.encodeToString(encryptedBytes, Base64.NO_WRAP | Base64.URL_SAFE);
		}
		return null;
	}

	// @Override
	/**
	 * 비대칭 암호화 처리.
	 * @param orignalString hexString으로 인코딩된 문자열 
	 * @return 암호화 되어 hexstring으로 인코딩된 문자열 반환  
	 */
	public String encryptToHexString(String orignalString) {
		
		if (orignalString != null) {
			
			byte[] encryptedBytes = null;
			try {
				encryptedBytes = encrypt(orignalString.getBytes("UTF-8"));
				
			} catch (UnsupportedEncodingException e1) {
				// loging 필요..[TODO]
				e1.printStackTrace();
				return null;
			} catch (GeneralSecurityException e) {
				// loging 필요..[TODO]
				e.printStackTrace();
				return null;
			}
			
			return HexString.encode(encryptedBytes);
		}
		return null;
	}
}