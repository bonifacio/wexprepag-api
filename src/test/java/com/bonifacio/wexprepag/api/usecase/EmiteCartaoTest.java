package com.bonifacio.wexprepag.api.usecase;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bonifacio.wexprepag.api.domain.CartaoNovo;
import com.bonifacio.wexprepag.api.gateway.PersisteCartaoGateway;

@RunWith(MockitoJUnitRunner.class)
public class EmiteCartaoTest {
	
	@Mock
	private PersisteCartaoGateway persisteCartaoGateway;
	
	@InjectMocks
	private EmiteCartao emiteCartao;

	@Test
	public void deveEmitirCartao_quandoTodosOsParametrosForemPassadosCorretamente() {
		
		doNothing().when(persisteCartaoGateway).persistir(any(CartaoNovo.class));
		CartaoNovo cartaoRetornado = emiteCartao.emitir("João", BigDecimal.ZERO);
		verify(persisteCartaoGateway, times(1)).persistir(any(CartaoNovo.class));
		assertNotNull(cartaoRetornado);
	}
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoNomeForNulo() {
		emiteCartao.emitir(null, BigDecimal.ZERO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoNomeForVazio() {
		emiteCartao.emitir(" ", BigDecimal.ZERO);
	}
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoSaldoForNulo() {
		emiteCartao.emitir("João", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveLancarExcecao_quandoSaldoForMenorQueZero() {
		emiteCartao.emitir(" ", BigDecimal.valueOf(-1));
	}
}
