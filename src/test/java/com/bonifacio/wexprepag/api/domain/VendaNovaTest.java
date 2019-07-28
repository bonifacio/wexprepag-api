package com.bonifacio.wexprepag.api.domain;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.domain.security.Cvv;
import com.bonifacio.wexprepag.api.domain.security.SenhaUtil;

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

		VendaNova venda = construirVendaValida();

		assertNotNull(venda);
		assertNotNull(venda.getNumeroCartao());
		assertNotNull(venda.getCvv());
		assertNotNull(venda.getEstabelecimento());
		assertNotNull(venda.getSenha());
		assertNotNull(venda.getValidade());
		assertNotNull(venda.getValor());
	}

	@Test(expected = BusinessException.class)
	public void deveLancarExcecao_quandoForPassadoUmCartaoComCvvInvalido() {

		VendaNova venda = construirVendaValida();
		
		LocalDate validadeDiferente = venda.getValidade().plusDays(1);
		
		CartaoLeitura cartao = CartaoLeitura.builder()
				.comNumero(venda.getNumeroCartao())
				.comValidade(validadeDiferente)
				.build();
		
		venda.setCartao(cartao);
	}
	
	@Test(expected = BusinessException.class)
	public void deveLancarExcecao_quandoForPassadoUmCartaoComSenhaInvalido() {

		VendaNova venda = construirVendaValida();
		
		CartaoLeitura cartao = CartaoLeitura.builder()
				.comNumero(venda.getNumeroCartao())
				.comValidade(venda.getValidade())
				.comSenha("")
				.build();
		
		venda.setCartao(cartao);
	}
	
	@Test(expected = BusinessException.class)
	public void deveLancarExcecao_quandoForPassadoUmCartaoComValidadeUltrapassada() {

		VendaNova venda = construirVendaValida();
		
		CartaoLeitura cartao = CartaoLeitura.builder()
				.comNumero(venda.getNumeroCartao())
				.comValidade(venda.getValidade().minusMonths(1))
				.comSenha(SenhaUtil.encriptar(venda.getSenha()))
				.build();
		
		venda.setCartao(cartao);
	}
	
	@Test(expected = BusinessException.class)
	public void deveLancarExcecao_quandoForPassadoUmCartaoComSaldoInsuficiente() {

		VendaNova venda = construirVendaValida();
		
		CartaoLeitura cartao = CartaoLeitura.builder()
				.comNumero(venda.getNumeroCartao())
				.comValidade(venda.getValidade())
				.comSenha(SenhaUtil.encriptar(venda.getSenha()))
				.comSaldo(BigDecimal.ZERO)
				.build();
		
		venda.setCartao(cartao);
	}
	
	@Test
	public void deveAtribuirCartaoESaldoAtual_quandoOCartaoEACompraForemValidos() {
		
		VendaNova venda = construirVendaValida();
		
		CartaoLeitura cartao = CartaoLeitura.builder()
				.comNumero(venda.getNumeroCartao())
				.comValidade(venda.getValidade())
				.comSaldo(BigDecimal.TEN)
				.comSenha(SenhaUtil.encriptar(venda.getSenha()))
				.build();
		
		venda.setCartao(cartao);
		assertNotNull(venda.getCartao());
		assertNotNull(venda.getSaldoAnterior());
	}

	private VendaNova construirVendaValida() {
		
		String numero = "1234567890987654";
		LocalDate validade = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		
		return VendaNova.builder()
				.comCartao(numero)
				.comCvv(new Cvv(numero, validade).get())
				.comEstabelecimento("Loja")
				.comSenha("1234")
				.comValidade(validade)
				.comValor(BigDecimal.valueOf(1))
				.build();
	}
}
