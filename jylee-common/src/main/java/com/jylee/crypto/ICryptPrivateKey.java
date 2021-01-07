package com.jylee.crypto;

import java.security.PrivateKey;

public interface ICryptPrivateKey extends ICryptKey {

	public PrivateKey getKey();
	
	public void setKey(PrivateKey key);
	
	public String getProvider();
}