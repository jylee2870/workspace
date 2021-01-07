package com.jylee.crypto;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

/**
 * 비대칭 암호화에 사용되는 KeyPair를 관리한다.
 * @author y20090064
 *
 */
public interface IKeyPairManager {


	public void generateKeyPair() throws NoSuchAlgorithmException;
	
	public KeyPair getKeyPair();
	
	public void setKeyPair(KeyPair keyPair);
	
	public ICryptPublicKey getCryptPublicKey();
	
	public ICryptPrivateKey getCryptPrivateKey();

}
