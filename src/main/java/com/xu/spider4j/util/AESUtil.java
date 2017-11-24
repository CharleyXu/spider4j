package com.xu.spider4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);
	private static int Length = 128;
	private static final String DEFAULT_KEY = "glgjsy1";
	private static byte[] password = generateKey(DEFAULT_KEY);

	/**
	 * 即便key相同 但是每次生成的byte[]却是不同的
	 * @param	key
	 * @return	16Byte的加密password
	 */
	public static byte[] generateKey(String key){
		try {
			KeyGenerator generator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			generator.init(Length,secureRandom);
			return generator.generateKey().getEncoded();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.toString());
		}
		return null;
	}

	public static String encrypt(String content){
		return encrypt(content, password);
	}

	public static String encrypt(String content, byte[] keyByte){
		try {
			//初始化密钥 SecretKeySpec(byte[] key, String algorithm)
			SecretKeySpec key = new SecretKeySpec(keyByte, "AES");
			//创建密码器
			Cipher cipher = Cipher.getInstance("AES");
			//初始化 key要求是16位 16个字节=16*8=128bit 128位
			cipher.init(Cipher.ENCRYPT_MODE,key);

			byte[] byteContent = content.getBytes("utf-8");
			//获取加密后字节数组
			byte[] result = cipher.doFinal(byteContent);

			//获取加密后的字符串
			return parseByte2HexStr(result);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return null;
	}

	public static String decrypt(String content){
		return decrypt(content, password);
	}

	public static String decrypt(String content, byte[] keyByte) {
		try {
			SecretKeySpec key = new SecretKeySpec(keyByte, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(parseHexStr2Byte(content));
			return new String(result); // 明文
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return null;
	}

	/**
	 * 将16进制转换为二进制
	 *
	 * @param hexStr    字符串
	 * @return          字节数组
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf       字节数组
	 * @return          字符串
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}


	public static void main(String[] args) {
		byte[] password = generateKey("changliang");
		System.out.println(password.length);

		String content = "test";

		//加密
		System.out.println("加密前：" + content);
		String encryptResult = encrypt(content, password);
		System.out.println("加密后：" + encryptResult);
		//解密
		String decryptResult = decrypt(encryptResult,password);
		System.out.println("解密后：" + decryptResult);


	}
}
