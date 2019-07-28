package com.bonifacio.wexprepag.api.gateway.http.model;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

import com.bonifacio.wexprepag.api.domain.CartaoNovo;

public class EmissaoCartaoResponseTest {

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoCartaoForNulo() {
		EmissaoCartaoResponse.of(null);
	}
	
	@Test
	public void deveInstanciar_quandoCartaoNaoForNulo() {
		
		EmissaoCartaoResponse emissaoCartaoResponse = EmissaoCartaoResponse.of(new CartaoNovo("João", BigDecimal.ZERO));
		assertNotNull(emissaoCartaoResponse);
		assertNotNull(emissaoCartaoResponse.getCvv());
		assertNotNull(emissaoCartaoResponse.getNome());
		assertNotNull(emissaoCartaoResponse.getNumero());
		assertNotNull(emissaoCartaoResponse.getSaldo());
		assertNotNull(emissaoCartaoResponse.getSenha());
		assertNotNull(emissaoCartaoResponse.getValidade());
	}
}
