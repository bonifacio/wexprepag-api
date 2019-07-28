package com.bonifacio.wexprepag.api.gateway.http.model;

import java.math.BigDecimal;

public class EmissaoCartaoRequest {

	private String nome;

	private BigDecimal saldo;

	public String getNome() {
		return nome;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
}
