package com.jylee.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DefaultKeyPairManager implements IKeyPairManager {

	private String encryptionAlgorithm;
	private int keyLength;
	
	private KeyPair keyPair;
	
	public DefaultKeyPairManager() {
		encryptionAlgorithm = AsymmetricAlgorithmEnum.RSA.toString();
		keyLength = 512;
	}
	
	public DefaultKeyPairManager(String encryptionAlgorithm, int keyLength) {
		
		String rsaAlgorithm = AsymmetricAlgorithmEnum.RSA.toString(); 
		
		this.encryptionAlgorithm = encryptionAlgorithm == null ? rsaAlgorithm: encryptionAlgorithm;
		
		if (this.encryptionAlgorithm.equals(rsaAlgorithm) && keyLength < 512)
			keyLength = 512;
		int length = keyLength / 8 + 1;
		
		this.keyLength = length * 8;
	}
	
	// @Override
	public void generateKeyPair() throws NoSuchAlgorithmException {
		
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(encryptionAlgorithm);
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextInt();
		keyPairGenerator.initialize(keyLength, secureRandom);
		setKeyPair( keyPairGenerator.generateKeyPair() );
		
	}

	// @Override
	public KeyPair getKeyPair() {
		
		return keyPair;
	}

	// @Override
	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;

	}
	
	// @Override 
	public ICryptPublicKey getCryptPublicKey() {
		
		return new DefaultCryptPublicKey(
				getKeyPair().getPublic(), encryptionAlgorithm
				);
	}
	
	// @Override
	public ICryptPrivateKey getCryptPrivateKey() {
		return new DefaultCryptPrivateKey(
				getKeyPair().getPrivate(), encryptionAlgorithm
				);
	}
	
	public void saveKeyPairToFile( String path ) {
		
		
	}
	
	public void loadKeyPairFromFile( String path) {
		
	}

	public void savePublicKeyToFile(String fileName, DefaultCryptPublicKey publicKey) {
		
		publicKey.saveKeyToFile(fileName);
	}

	public DefaultCryptPublicKey loadPublicKeyFromFile(String filePath) {
		
		DefaultCryptPublicKey publicKey = new DefaultCryptPublicKey();
		publicKey.loadKeyFromFile(filePath);
		
		return publicKey;
		
	}

	

	
}
