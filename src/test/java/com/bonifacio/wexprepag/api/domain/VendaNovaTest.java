package com.bonifacio.wexprepag.api.domain;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class VendaNovaTest {

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoNenhumAtributoForPassado() {
		VendaNova.builder().build();
	}

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoCartaoForNulo() {
		VendaNova.builder().comCartao(null).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoCartaoNaoForNumerico() {
		VendaNova.builder().comCartao("a").build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoCartaoForInvalido() {
		VendaNova.builder().comCartao("1234567").build();
	}

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoCvvForNulo() {
		VendaNova.builder().comCvv(null).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoCvvNaoForNumerico() {
		VendaNova.builder().comCvv("a").build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoCvvForInvalido() {
		VendaNova.builder().comCvv("1").build();
	}

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoEstabelecimentoForNulo() {
		VendaNova.builder().comEstabelecimento(null).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoEstabelecimentoForVazio() {
		VendaNova.builder().comEstabelecimento(" ").build();
	}

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoSenhaForNulo() {
		VendaNova.builder().comSenha(null).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoSenhaNaoForNumerico() {
		VendaNova.builder().comSenha("a").build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoSenhaNaoForInvalido() {
		VendaNova.builder().comSenha("12").build();
	}

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoValidadeForNulo() {
		VendaNova.builder().comValidade(null).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoValidadeUltrapassada() {
		LocalDate periodoAnterior = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).minusMonths(1);
		VendaNova.builder().comValidade(periodoAnterior).build();
	}

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoValorForNulo() {
		VendaNova.builder().comValor(null).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoValorForMenorOuIgualAZero() {
		VendaNova.builder().comValor(BigDecimal.valueOf(-1)).build();
	}

	@Test
	public void deveInstanciarVenda_quandoTodosOsCamposForemValidos() {

		VendaNova venda = VendaNova.builder()
				.comCartao("1234567890987654")
				.comCvv("123")
				.comEstabelecimento("Loja")
				.comSenha("1234")
				.comValidade(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()))
				.comValor(BigDecimal.valueOf(1))
				.build();
		
		assertNotNull(venda);
		assertNotNull(venda.getNumeroCartao());
		assertNotNull(venda.getCvv());
		assertNotNull(venda.getEstabelecimento());
		assertNotNull(venda.getSenha());
		assertNotNull(venda.getValidade());
		assertNotNull(venda.getValor());
	}
}
