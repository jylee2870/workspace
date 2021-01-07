package com.jylee.crypto.klib;


public class CryptoLib {
	
	static {
		System.load("/home/freesia/jni/CryptoLib.so");
	}
	public native static int start();
    public native static int end();
    
    public native static byte[] encrypt(byte key[], byte value[]);
    public native static byte[] decrypt(byte key[], byte value[]);
    public native static byte[] getKey();
    public native static byte[] getLandom(int length);
    public native static byte[] createHash(byte value[]);
	
}



