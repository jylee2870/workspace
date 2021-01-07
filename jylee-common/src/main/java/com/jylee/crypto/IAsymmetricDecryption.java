package com.jylee.crypto;

import java.security.GeneralSecurityException;

/**
 * 비대칭 암호화를 처리하는 객체 
 * @author y20090064
 *
 */
public interface IAsymmetricDecryption {


	
	/**
	 * 비대칭 암호화된키를 사설키로 복호화 한다.
	 * @param encrytedBytes
	 * @return
	 * @throws GeneralSecurityException
	 */
	public byte[] decrypt(byte[] encrytedBytes) throws GeneralSecurityException;
	
	/**
	 * 비대칭 암호화된키를 사설키로 복호화 한다.
	 * @param base64String base64 encodeing된 데이터.
	 * @return
	 */
	public String decryptFromBase64(String base64String);
	
	/**
	 * 비대칭 암호화된 키를 사설키로 복호화 한다. 
	 * @param hexString
	 * @return
	 */
	public String decryptFromHexString(String hexString);
}
