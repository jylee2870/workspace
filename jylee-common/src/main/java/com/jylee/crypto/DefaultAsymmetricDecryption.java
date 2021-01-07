package com.jylee.crypto;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

import javax.crypto.Cipher;

import com.jylee.util.Base64;
import com.jylee.util.HexString;

public class DefaultAsymmetricDecryption implements IAsymmetricDecryption {

	private ICryptPrivateKey cryptPrivateKey;
	
	public DefaultAsymmetricDecryption(ICryptPrivateKey cryptPrivateKey) {
		
		this.cryptPrivateKey = cryptPrivateKey;
	}
	
	// @Override
	public byte[] decrypt(byte[] encrytedBytes) throws GeneralSecurityException {
		
		if ( encrytedBytes == null ) throw new GeneralSecurityException("invalid parameter!");
		
		if (cryptPrivateKey == null) return null;
		PrivateKey privateKey = cryptPrivateKey.getKey();
		
		if (privateKey == null) return null;
		Cipher cipher = null;
		if (cryptPrivateKey.getProvider() != null) {
			cipher = Cipher.getInstance(privateKey.getAlgorithm(), cryptPrivateKey.getProvider());	
		} else {
			cipher = Cipher.getInstance(privateKey.getAlgorithm());
		}
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		return cipher.doFinal(encrytedBytes);
	}

	// @Override
	public String decryptFromBase64(String encryptedString)  {
		
		if (encryptedString != null) {
			
			byte[] decryptedBytes = null;
			try {
				
				byte[] bytes = Base64.decode(encryptedString, Base64.NO_WRAP + Base64.URL_SAFE) ;
				if ( bytes != null ) {
					decryptedBytes = decrypt(bytes );
					if ( decryptedBytes != null)
						return new String(decryptedBytes, "UTF-8");
				}
				
			} catch (GeneralSecurityException e) {
				// TODO - logging
				e.printStackTrace();
				return null;
			} catch (UnsupportedEncodingException e1) {
				// TODO - logging
				e1.printStackTrace();
				return null;
			}
		}
		return null;
	}

	// @Override
	public String decryptFromHexString(String encryptedString) {
		
		if (encryptedString != null) {
			
			byte[] decryptedBytes = null;
			try {
				
				byte[] bytes = HexString.decode(encryptedString);
				if (bytes != null ) {
					decryptedBytes = decrypt( bytes );
					
					if (decryptedBytes != null)
						return new String(decryptedBytes, "UTF-8");
				}
				
			} catch (GeneralSecurityException e) {
				// TODO - logging
				e.printStackTrace();
				return null;
			} catch (UnsupportedEncodingException e1) {
				// TODO - logging
				e1.printStackTrace();
				return null;
			}
		}
		return null;
	}
}