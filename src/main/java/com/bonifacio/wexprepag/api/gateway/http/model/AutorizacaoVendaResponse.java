package com.bonifacio.wexprepag.api.gateway.http.model;

import java.math.BigDecimal;

import com.bonifacio.wexprepag.api.domain.ResultadoOperacao;

public class AutorizacaoVendaResponse {

	private String codigo;
	
	private BigDecimal saldo;

	@Deprecated
	public AutorizacaoVendaResponse() { }
	
	public AutorizacaoVendaResponse(ResultadoOperacao resultadoOperacao) {
		this.codigo = resultadoOperacao.getCodigo();
		this.saldo = resultadoOperacao.getSaldo();
	}

	public String getCodigo() {
		return codigo;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public static AutorizacaoVendaResponse of(ResultadoOperacao resultadoOperacao) {
		return new AutorizacaoVendaResponse(resultadoOperacao);
	}
}
