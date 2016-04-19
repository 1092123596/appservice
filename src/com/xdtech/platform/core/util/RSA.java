package com.xdtech.platform.core.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSA {
	private static final String KEY_ALGORITHM = "RSA";
	private static final String PUBLIC_KEY = "publicKey";
	private static final String PRIVATE_KEY = "privateKey";

	private static final char[] bcdLookup = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static void main(String[] args) throws Exception {
		Map<String, String> keyMap = genKey();
		RSAPublicKey publicKey = getPublicKey(keyMap.get(PUBLIC_KEY));
		RSAPrivateKey privateKey = getPrivateKey(keyMap.get(PRIVATE_KEY));
		String info = "明文123456==^@11133333222222222";
		// 加密
		byte[] bytes = encrypt(info.getBytes("utf-8"), publicKey);
		System.out.println(bytesToHexStr(bytes));
		// 解密
		bytes = decrypt(bytes, privateKey);
		System.out.println(new String(bytes, "utf-8"));

	}
	
	public static Map<String, String> genKey() throws Exception {
		Map<String, String> keyMap = new HashMap<String, String>();
		KeyPairGenerator keygen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		SecureRandom random = new SecureRandom();
		// random.setSeed(keyInfo.getBytes());
		// 初始加密，512位已被破解，用1024位,最好用2048位
		keygen.initialize(1024, random);
		// 取得密钥对
		KeyPair kp = keygen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
		String privateKeyString = bytesToHexStr(privateKey.getEncoded());

		RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
		String publicKeyString = bytesToHexStr(publicKey.getEncoded());
		keyMap.put(PUBLIC_KEY, publicKeyString);
		keyMap.put(PRIVATE_KEY, privateKeyString);
		System.out.println("PUBLIC_KEY:" + keyMap.get(PUBLIC_KEY));
		System.out.println("PRIVATE_KEY:" + keyMap.get(PRIVATE_KEY));
		return keyMap;
	}

	public static RSAPublicKey getPublicKey(String publicKey) throws Exception {
		byte[] keyBytes = hexStrToBytes(publicKey);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		return (RSAPublicKey) keyFactory.generatePublic(spec);
	}

	public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception {
		byte[] keyBytes = hexStrToBytes(privateKey);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		return (RSAPrivateKey) keyFactory.generatePrivate(spec);
	}

	public static byte[] encrypt(byte[] text, RSAPublicKey pubRSA) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubRSA);
		return cipher.doFinal(text);
	}

	public static byte[] decrypt(byte[] src, RSAPrivateKey prK) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, prK);
		return cipher.doFinal(src);
	}

	/**
	 * Transform the specified byte into a Hex String form.
	 */
	public static final String bytesToHexStr(byte[] bcd) {
		StringBuffer s = new StringBuffer(bcd.length * 2);
		for (int i = 0; i < bcd.length; i++) {
			s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
			s.append(bcdLookup[bcd[i] & 0x0f]);
		}
		return s.toString();
	}

	/**
	 * Transform the specified Hex String into a byte array.
	 */
	public static final byte[] hexStrToBytes(String s) {
		byte[] bytes;
		bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

}