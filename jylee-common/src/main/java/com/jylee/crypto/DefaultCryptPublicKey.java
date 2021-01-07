package com.jylee.crypto;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import com.jylee.util.Base64;
import com.jylee.util.HexString;

public class DefaultCryptPublicKey implements ICryptPublicKey {

	private PublicKey key;
	private String encryptAlgorithm;
	private String provider;
	
	public DefaultCryptPublicKey() {
		this.key = null;
		this.encryptAlgorithm = AsymmetricAlgorithmEnum.RSA.toString();
		this.provider = null;
	}
	
	public DefaultCryptPublicKey(String encryptAlgorithm) {
		this.key = null;
		this.encryptAlgorithm = encryptAlgorithm;
		this.provider = null;
	}
	
	public DefaultCryptPublicKey(PublicKey key, String encryptAlgorithm) {
		this.key = key;
		this.encryptAlgorithm = encryptAlgorithm;
		this.provider = null;
	}
	
	public DefaultCryptPublicKey(PublicKey key, String encryptAlgorithm, String provider) {
		this.key = key;
		this.encryptAlgorithm = encryptAlgorithm;
		this.provider = provider;
	}
	
	// @Override
	public PublicKey getKey() {
		return key;
	}

	// @Override
	public void setKey(PublicKey key) {
		this.key = key;
	}

	// @Override
	public String getBase64Key() {
		
		if (key == null) return null;
		return Base64.encodeToString(PublicKeyHelper.serialize(key), Base64.NO_WRAP + Base64.URL_SAFE);
	}

	// @Override
	public String getHexStringKey() {
		if (key == null) return null;
		return HexString.encode(PublicKeyHelper.serialize(key));
	}

	// @Override
	public void setBase64Key(String base64String) {
		if ( base64String == null) {
			key = null;
		}
		else {
			key = PublicKeyHelper.deserialize(Base64.decode(base64String, Base64.NO_WRAP + Base64.URL_SAFE), encryptAlgorithm, provider);
		}
	}

	// @Override
	public void setHexStringKey(String hexString) {
		
		if ( hexString == null) {
			key = null;
		}
		else {
			key = PublicKeyHelper.deserialize( HexString.decode(hexString), encryptAlgorithm, provider);
		}
	}
	
	// @Override
	public String getProvider() {
		return provider;
	}
	
	public boolean loadKeyFromFile(String filePath) {
		
		if ( filePath == null ) return false;
		
		byte[] bytes = KeyPairFileLoader.loadKeyFromFile(filePath);
		key = PublicKeyHelper.deserialize(bytes, encryptAlgorithm, provider);
		return key == null ? false : true;
	}
	
	public boolean saveKeyToFile(String filePath) {
		
		if ( filePath == null ) return false;
		byte[] bytes = PublicKeyHelper.serialize(key);
		return KeyPairFileLoader.saveKeyDataToFile(bytes, filePath);
	}
	
	private static class PublicKeyHelper {
		
		private static byte[] serialize(PublicKey key) {
			if (key == null) return null;
			
			return (new X509EncodedKeySpec( key.getEncoded() )).getEncoded();
		}
	
		private static PublicKey deserialize(byte[] keyData, String encryptAlgorithm, String provider) {
			
			if (keyData == null || encryptAlgorithm == null) return null;
			
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyData);
			
			try {
				if (provider != null) {
					return KeyFactory.getInstance(encryptAlgorithm, provider).generatePublic(keySpec);
				} else if (provider == null) {
					return KeyFactory.getInstance(encryptAlgorithm).generatePublic(keySpec);
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