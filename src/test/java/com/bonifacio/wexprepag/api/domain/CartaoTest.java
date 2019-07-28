package com.bonifacio.wexprepag.api.domain;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

public class CartaoTest {

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoNomeForNulo() {
		new Cartao(null, BigDecimal.ZERO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoNomeForVazio() {
		new Cartao(" ", BigDecimal.ZERO);
	}
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoValorForNulo() {
		new Cartao("João", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoSaldoForMenorQueZero() {
		new Cartao("João", BigDecimal.valueOf(-1));
	}
	
	@Test
	public void deveInstanciarUmCartao_quandoForemPassadosTodosOsCamposValidos() {
		
		Cartao cartao = new Cartao("João", BigDecimal.ZERO);
		assertNotNull(cartao);
		assertNotNull(cartao.getNome());
		assertNotNull(cartao.getNumero());
		assertNotNull(cartao.getSaldo());
		assertNotNull(cartao.getValidade());
		assertNotNull(cartao.getCvv());
		assertNotNull(cartao.getSenha());
	}
}
