package com.app.MovieApp.security.common.config.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class AsymmetricCryptography {

	private Cipher cipher;

	public AsymmetricCryptography() throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipher = Cipher.getInstance("RSA");
	}

	public PrivateKey getPrivate() throws Exception {
		PrivateKey keyBytes = KeyGenerator.privateKey;
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes.getEncoded());
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public PublicKey getPublic() throws Exception {
		PublicKey keyBytes = KeyGenerator.publicKey;
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes.getEncoded());
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}

	public String getPrivateText(String msg) throws Exception {
		PrivateKey key = getPrivate();
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}

	public String getPublicText(String msg) throws Exception {
		PublicKey key = getPublic();
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
	}

	public static void main(String[] args) throws Exception {

		AsymmetricCryptography ac = new AsymmetricCryptography();
		String msg = "Cryptography is fun!";
		String encrypted_msg = ac.getPrivateText(msg);
		String decrypted_msg = ac.getPublicText(encrypted_msg);

		System.out.println("Original Message: " + msg + "\nEncrypted Message: " + encrypted_msg
				+ "\nDecrypted Message: " + decrypted_msg);
	}
}