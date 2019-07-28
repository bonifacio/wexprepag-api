package com.bonifacio.wexprepag.api.domain.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.bonifacio.wexprepag.api.domain.exception.BusinessException;

public class SenhaUtil {
	
	private SenhaUtil() { }

	public static String encriptar(String senha) {
		
		try {
			MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
			byte[] bytesCriptografados = algoritmo.digest(senha.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(bytesCriptografados);
		} catch (NoSuchAlgorithmException e) {
			throw new BusinessException("Um erro ocorreu ao criptografar a senha");
		}
	}
}
