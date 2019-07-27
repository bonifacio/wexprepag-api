package com.bonifacio.wexprepag.api.domain;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import com.bonifacio.wexprepag.api.CartaoFactory;

public class CartaoTest {

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoNenhumAtributoForInformado() {
		Cartao.builder().build();
	}
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoNomeForNulo() {
		Cartao.builder().comNome(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoNumeroForNulo() {
		Cartao.builder().comNumero(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoSaldoForNulo() {
		Cartao.builder().comSaldo(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoSenhaForNulo() {
		Cartao.builder().comSenha(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoValidadeForNulo() {
		Cartao.builder().comValidade(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoNomeForVazio() {
		Cartao.builder().comNome(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoSaldoForMenorQueZero() {
		Cartao.builder().comSaldo(BigDecimal.valueOf(-1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoValidadeForAntesDaDataAtual() {
		Cartao.builder().comValidade(LocalDate.now().minusDays(1));
	}
	
	@Test
	public void deveInstanciarUmCartao_quandoForemPassadosTodosOsCamposValidos() {
		
		Cartao cartao = CartaoFactory.umCartao();
		assertNotNull(cartao);
		assertNotNull(cartao.getNome());
		assertNotNull(cartao.getNumero());
		assertNotNull(cartao.getSaldo());
		assertNotNull(cartao.getValidade());
		assertNotNull(cartao.getCvv());
		assertNotNull(cartao.getSenha());
	}
}
