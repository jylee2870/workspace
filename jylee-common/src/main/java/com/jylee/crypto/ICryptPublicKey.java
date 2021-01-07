package com.jylee.crypto;

import java.security.PublicKey;

public interface ICryptPublicKey extends ICryptKey {

	public PublicKey getKey();
	
	public void setKey(PublicKey key);
	
	public String getProvider();
}