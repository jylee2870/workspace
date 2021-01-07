package com.jylee.crypto;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import com.jylee.util.Base64;
import com.jylee.util.HexString;

public class DefaultCryptPrivateKey implements ICryptPrivateKey {
	
	private PrivateKey key;
	private String encryptAlgorithm;
	private String provider;
	
	public DefaultCryptPrivateKey() {
		this.key = null;
		this.encryptAlgorithm = "RSA";
		this.provider = null;
	}
	
	public DefaultCryptPrivateKey(String encryptAlgorithm) {
		this.key = null;
		this.encryptAlgorithm = encryptAlgorithm;
		this.provider = null;
	}
	
	public DefaultCryptPrivateKey(PrivateKey key, String encryptAlgorithm) {
		this.key = key;
		this.encryptAlgorithm = encryptAlgorithm;
		this.provider = null;
	}
	
	public DefaultCryptPrivateKey(PrivateKey key, String encryptAlgorithm, String provider) {
		this.key = key;
		this.encryptAlgorithm = encryptAlgorithm;
		this.provider = provider;
	}
	
	// @Override
	public PrivateKey getKey() {
		
		return key;
	}

	// @Override
	public void setKey(PrivateKey key) {
		
		this.key = key;
	}

	// @Override
	public String getBase64Key() {
		if (key == null) return null;
		return Base64.encodeToString( PrivateKeyHelper.serialize(key), Base64.NO_WRAP + Base64.URL_SAFE);
	}

	// @Override
	public String getHexStringKey() {
		if (key == null) return null;
		return HexString.encode(PrivateKeyHelper.serialize(key));
	}

	// @Override
	public void setBase64Key(String base64String) {
		
		if (base64String == null )
			key = null;
		else
			key = PrivateKeyHelper.deserialize(Base64.decode(base64String, Base64.NO_WRAP + Base64.URL_SAFE), encryptAlgorithm, provider);
	}

	// @Override
	public void setHexStringKey(String hexString) {
		if (hexString == null )
			key = null;
		else
			key = PrivateKeyHelper.deserialize( HexString.decode(hexString), encryptAlgorithm, provider);

	}
	
	// @Override
	public String getProvider() {
		return provider;
	}
	
	public boolean saveKeyToFile(String filePath) {
		if ( filePath == null ) return false;

		return KeyPairFileLoader.saveKeyDataToFile(PrivateKeyHelper.serialize(key), filePath);
	}
	
	public boolean loadKeyFromFile(String filePath) {
		if ( filePath == null ) return false;
		
		byte[] keyData = KeyPairFileLoader.loadKeyFromFile(filePath);
		key = PrivateKeyHelper.deserialize(keyData, encryptAlgorithm, provider);
		return key == null ? false : true;
	}
	
	private static class PrivateKeyHelper {
		
		private static byte[] serialize(PrivateKey key) {
			
			if (key == null) return null;
			return (new PKCS8EncodedKeySpec(key.getEncoded())).getEncoded(); 
		}
		
		private static PrivateKey deserialize( byte[] keyData, String encryptAlgorithm, String provider ) {
			
			if (keyData == null || encryptAlgorithm == null) return null;
			
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyData);
			
			try {
				if (provider != null) {
					return KeyFactory.getInstance(encryptAlgorithm, provider).generatePrivate(keySpec);
				} else if (provider == null) {
					return KeyFactory.getInstance(encryptAlgorithm).generatePrivate(keySpec);
				}
			} catch (InvalidKeySpecException e) {
				// TODO logging supplied
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO logging supplied
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
}