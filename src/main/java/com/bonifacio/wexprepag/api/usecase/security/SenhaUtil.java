package com.bonifacio.wexprepag.api.usecase.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SenhaUtil {

	public static String encriptar(String senha) {
		
		try {
			MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
			byte[] bytesCriptografados = algoritmo.digest(senha.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(bytesCriptografados);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeException("Um erro ocorreu ao criptografar a senha");
		}
	}
}
