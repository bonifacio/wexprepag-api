package com.bonifacio.wexprepag.api.domain.security;

import java.time.LocalDate;

public class Cvv {

	private String numero;
	
	private LocalDate validade;

	public Cvv(String numero, LocalDate validade) {
		this.numero = numero;
		this.validade = validade;
	}

	public String get() {
		
		String ultimoDigitoCartao = numero.substring(numero.length()-1);
		int diaDaSemanaVencimento = validade.getDayOfWeek().getValue();
		String nonoDigitoCartao = numero.substring(8, 8 + 1);
		
		StringBuilder cvv = new StringBuilder()
				.append(ultimoDigitoCartao)
				.append(diaDaSemanaVencimento)
				.append(nonoDigitoCartao);
		
		return cvv.toString();
	}
	
	
}
