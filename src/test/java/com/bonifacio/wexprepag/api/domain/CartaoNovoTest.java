package com.bonifacio.wexprepag.api.domain;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

public class CartaoNovoTest {

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoNomeForNulo() {
		new CartaoNovo(null, BigDecimal.ZERO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoNomeForVazio() {
		new CartaoNovo(" ", BigDecimal.ZERO);
	}
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoValorForNulo() {
		new CartaoNovo("João", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoSaldoForMenorQueZero() {
		new CartaoNovo("João", BigDecimal.valueOf(-1));
	}
	
	@Test
	public void deveInstanciarUmCartao_quandoForemPassadosTodosOsCamposValidos() {
		
		CartaoNovo cartao = new CartaoNovo("João", BigDecimal.ZERO);
		assertNotNull(cartao);
		assertNotNull(cartao.getNome());
		assertNotNull(cartao.getNumero());
		assertNotNull(cartao.getSaldo());
		assertNotNull(cartao.getValidade());
		assertNotNull(cartao.getCvv());
		assertNotNull(cartao.getSenha());
	}
}
