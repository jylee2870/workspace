package com.jylee.crypto;

public class CipherDesMake {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String val = "123";
		
		try {
			//scirpt shell 실행으로 화면에 출력함!! System.out.println 지우면안됨
			System.out.println(CipherDesUtil.desEncrypt(val));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
