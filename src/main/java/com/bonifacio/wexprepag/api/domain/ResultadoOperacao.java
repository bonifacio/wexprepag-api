package com.bonifacio.wexprepag.api.domain;

import java.math.BigDecimal;

public class ResultadoOperacao {
	
	private final String codigo = "00";

	private BigDecimal saldo;

	public ResultadoOperacao(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public String getCodigo() {
		return codigo;
	}
}
