package com.bonifacio.wexprepag.api.gateway.http.model;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

import com.bonifacio.wexprepag.api.domain.ResultadoOperacao;

public class AutorizacaoVendaResponseTest {

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoResultadoOperacaoForNulo() {
		AutorizacaoVendaResponse.of(null);
	}
	
	@Test
	public void deveInstanciar_quandoCartaoNaoForNulo() {
		AutorizacaoVendaResponse autorizacaoVendaResponse = AutorizacaoVendaResponse.of(new ResultadoOperacao(BigDecimal.ZERO));
		assertNotNull(autorizacaoVendaResponse);
		assertNotNull(autorizacaoVendaResponse.getCodigo());
		assertNotNull(autorizacaoVendaResponse.getSaldo());
	}
}
