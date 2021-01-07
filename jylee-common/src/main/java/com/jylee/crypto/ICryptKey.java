package com.jylee.crypto;


public interface ICryptKey {

	
	public String getBase64Key();
	
	public String getHexStringKey();
	
	
	public void setBase64Key(String base64String);
	
	public void setHexStringKey(String hexString);
}
