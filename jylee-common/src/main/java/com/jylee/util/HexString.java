package com.jylee.util;

public class HexString {

	public static String encode(byte[] bytes) {
		
		String result = null;
		if (bytes == null) {
			return result;
		}
		
		StringBuffer sbf = new StringBuffer();
		for (byte b : bytes) {
			sbf.append(Integer.toString((b & 0xF0) >> 4, 16));
			sbf.append(Integer.toString(b & 0x0F, 16));

		}
		return sbf.toString();

	}
	
	public static byte[] decode(String hexString) {
		
		byte[] result = null;
		if (hexString != null) {
			// remove all non alphanumeric chars like colons, whitespace, slashes
			hexString = hexString.replaceAll("[^a-zA-Z0-9]", "");
			// from http://forums.sun.com/thread.jspa?threadID=546486
			// (using BigInteger to convert to byte array messes up by adding extra 0 if first byte > 7F and this method
			//  will not rid of leading zeroes like the flawed method byte[] bts = new BigInteger(hex, 16).toByteArray();)
			result = new byte[hexString.length() / 2];
			for (int i = 0; i < result.length; i++) {
				result[i] = (byte) Integer.parseInt(hexString.substring(2 * i, 2 * i + 2), 16);
			}
		}
		// System.out.println("lenientHexToBytes(" + hexString + ") returned '" + new String(result) + "'");

		return result;

	}
}
