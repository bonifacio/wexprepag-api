package com.bonifacio.wexprepag.api.gateway.http.model;

import java.math.BigDecimal;

public class AutorizacaoVendaResponse {

	private String codigo;
	
	private BigDecimal saldo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
}
