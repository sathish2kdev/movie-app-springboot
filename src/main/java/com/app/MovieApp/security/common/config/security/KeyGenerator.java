package com.app.MovieApp.security.common.config.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyGenerator {

	protected static final Logger logger = LoggerFactory.getLogger(KeyGenerator.class);

	private static final KeyPairGenerator keyGen = generateKeyPairGenerator();

	private static final KeyPair pair = getnerateKeyPair();

	public static final PrivateKey privateKey = getPrivateKey();

	public static final PublicKey publicKey = getPublicKey();

	private static KeyPair getnerateKeyPair() {
		keyGen.initialize(2048);
		return keyGen.generateKeyPair();
	}

	private static KeyPairGenerator generateKeyPairGenerator() {
		try {
			return KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			logger.error("Exception @ KeyGenerator --- KeyPairGenerator --", e);
		}
		return null;
	}

	private static PrivateKey getPrivateKey() {
		return pair.getPrivate();
	}

	private static PublicKey getPublicKey() {
		return pair.getPublic();
	}

}