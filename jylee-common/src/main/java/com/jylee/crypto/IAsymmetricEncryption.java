package com.jylee.crypto;

import java.security.GeneralSecurityException;

/**
 * 비대칭 암화를 처리하는 객체
 * RSA 등의 비대칭 암호화를 처리한다. 
 * @author y20090064
 *
 */
public interface IAsymmetricEncryption {

	/**
	 * 비대칭 암호화를 처리한다.
	 * @param originalData byte 배열 
	 * @return 암호화된 바이트 배열 
	 */
	public byte[] encrypt(byte[] originalBytes) throws GeneralSecurityException;
	
	/**
	 * 비대칭 암호화 처리.
	 * @param orignalString base64로 인코딩된 문자열 
	 * @return 암호하되어 base64로 인코딩된 문자열 반환 
	 */
	public String encryptToBase64(String orignalString);
	
	/**
	 * 비대칭 암호화 처리.
	 * @param orignalString hexString으로 인코딩된 문자열 
	 * @return 암호화 되어 hexstring으로 인코딩된 문자열 반환  
	 */
	public String encryptToHexString(String orignalString);
}
