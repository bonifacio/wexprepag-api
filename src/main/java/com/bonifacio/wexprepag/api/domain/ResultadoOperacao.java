package com.bonifacio.wexprepag.api.domain;

import java.math.BigDecimal;

public class ResultadoOperacao {

	private BigDecimal saldo;

	public ResultadoOperacao(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
}
