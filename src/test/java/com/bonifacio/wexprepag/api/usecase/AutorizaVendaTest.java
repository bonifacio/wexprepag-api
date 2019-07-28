package com.bonifacio.wexprepag.api.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bonifacio.wexprepag.api.CartaoFactory;
import com.bonifacio.wexprepag.api.VendaFactory;
import com.bonifacio.wexprepag.api.domain.CartaoLeitura;
import com.bonifacio.wexprepag.api.domain.ResultadoOperacao;
import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.gateway.BuscaCartaoGateway;

@RunWith(MockitoJUnitRunner.class)
public class AutorizaVendaTest {

	@Mock
	private BuscaCartaoGateway buscaCartaoGateway;

	@Mock
	private RegistraVenda registraVenda;

	@Mock
	private AtualizaSaldoCartao atualizaSaldoCartao;

	@InjectMocks
	private AutorizaVenda autorizaVenda;

	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoForPassadoUmaVendaNovaNulo() {
		autorizaVenda.autorizar(null);
	}

	@Test
	public void deveAutorizarUmaVenda_quandoForPassadaUmVendaNovaValida() {
		
		VendaNova vendaNova = VendaFactory.construir();
		CartaoLeitura cartaoLeitura = CartaoFactory.construir(vendaNova);
		BigDecimal saldoAtual = cartaoLeitura.getSaldo().subtract(vendaNova.getValor());
		
		when(buscaCartaoGateway.buscarPor(vendaNova.getNumeroCartao())).thenReturn(cartaoLeitura);
		doNothing().when(registraVenda).registrar(vendaNova);
		when(atualizaSaldoCartao.atualizar(vendaNova)).thenReturn(saldoAtual);
		
		
		ResultadoOperacao resultadoOperacao = autorizaVenda.autorizar(vendaNova);
		
		verify(buscaCartaoGateway, times(1)).buscarPor(vendaNova.getNumeroCartao());
		verify(registraVenda, times(1)).registrar(vendaNova);
		verify(atualizaSaldoCartao, times(1)).atualizar(vendaNova);
		assertNotNull(resultadoOperacao);
		assertNotNull(resultadoOperacao.getSaldo());
		assertEquals(saldoAtual, resultadoOperacao.getSaldo());
	}
}
