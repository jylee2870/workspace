package com.jylee.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	/**
	 * 파라미터로 들어온 스트링을 MD5로 인코딩 한 후 Hex 형식으로 변환하여 리턴한다.
	 * 
	 * Note: 자바스크립트에서는 jquery 플러그인을 사용하여 같은 변환을 할 수 있다.
	 * 예) $.md5("yamaiatest");
	 * 
	 * @param string
	 * @return md5 hex string
	 * @throws NoSuchAlgorithmException
	 */
	public static String toHexString(String string) throws NoSuchAlgorithmException {
		
		byte[] md5Bytes = MessageDigest.getInstance("MD5").digest(string.getBytes());

		return HexString.encode(md5Bytes);
	}
}
