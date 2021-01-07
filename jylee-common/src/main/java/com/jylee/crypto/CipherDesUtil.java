package com.jylee.crypto;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.jylee.util.Base64;

/** 
* <ul>
*  <li>업무 그룹명 : BLAS </li>
*  <li>서브 업무명 : BLAS 공통유틸 - DES 암호화 관련 유틸리티  </li>
*  <li>설  명 : DES 암호화 관련 유틸리티  </li>
*  <li>작성자 : KIM IL YUN</li>
*  <li>작성일 : 2012. 11. 6 </li>
*  <li>Copyright ⓒ DSNTECH All Right Reserved </li>
*  <li>========================================</li>
*  <li>변경자/변경일 : </li>
*  <li>변경사유/내역 : </li>
*  <li>========================================</li>
* </ul>
*/
public class CipherDesUtil {

	private static String DES_ALGORITHM = "DES";
	// private static String DES_KEY = String.valueOf(CipherDesUtil.class.toString().hashCode());
	private static byte[] DES_KEY_RAW = {24, 23, 25, 93, 24, -14, -110, -97, -27, -73, -1, 77, 64, 58, 15, -23};
	private static String ENCODING = "UTF-8";
	
	/**
	 * DES 알고리즘을 사용하여 해당 평문을 암호화한다. 암호화된 결과는 BASE64 인코딩하여 반환한다.
	 * @param plain 암호화할 평문
	 * @return BASE64로 인코딩한 암호화 문자열
	 * @throws Exception
	 */
	public static String desEncrypt(String plain) throws Exception {
//		if (StringUtils.isEmpty(plain)) {
//			return plain;
//		}
		// 키 생성
		// KeySpec keySpec = new DESKeySpec(DES_KEY.getBytes());
		KeySpec keySpec = new DESKeySpec(DES_KEY_RAW);
		SecretKey sKey = SecretKeyFactory.getInstance(DES_ALGORITHM).generateSecret(keySpec);
		
		// 암호화
		Cipher cipher = Cipher.getInstance(sKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, sKey);
		byte[] enc = cipher.doFinal(plain.getBytes(ENCODING));
		
		// BASE64 encoding
		String result = new String(Base64.encode(enc, Base64.NO_WRAP), "UTF8");
		
		return result;
	}
	
	/**
	 * DES 알고리즘을 사용하여 해당 암호화 문자열을 복호화한다. 입력되는 암호화 문자열은 BASE64로 인코딩 되어 있어야 한다.
	 * @param encrypt BASE64로 인코딩 되어 있는 암호화 문자열
	 * @return 복호화된 평문
	 * @throws Exception
	 */
	public static String desDecrypt(String encrypt) throws Exception {
//		if (StringUtils.isEmpty(encrypt)) {
//			return encrypt;
//		}
		// 키 생성
		// KeySpec keySpec = new DESKeySpec(DES_KEY.getBytes());
		KeySpec keySpec = new DESKeySpec(DES_KEY_RAW);
		SecretKey sKey = SecretKeyFactory.getInstance(DES_ALGORITHM).generateSecret(keySpec);
		
		// BASE64 Decoding
		
		byte[] enc = Base64.decode(encrypt, Base64.NO_WRAP); 
		//decoder.decodeBuffer(encrypt);
		
		// 복호화
		Cipher cipher = Cipher.getInstance(sKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, sKey);
		byte[] plain = cipher.doFinal(enc);
		
		return new String(plain, ENCODING);
	}
}
