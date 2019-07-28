package com.bonifacio.wexprepag.api.gateway.database;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.gateway.database.entity.CartaoData;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersisteCartaoGatewayImplTest {

	@Mock
	private CartaoRepository cartaoRepository;
	
	@InjectMocks
	private PersisteCartaoGatewayImpl persisteCartaoGatewayImpl;
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcessao_quandoCartaoForNulo() {
		persisteCartaoGatewayImpl.persistir(null);
	}
	
	@Test
	public void devePersistirCartao_quandoForPassadaUmaInstanciaValida() {
		
		Cartao cartao = new Cartao("João", BigDecimal.ZERO);
		when(cartaoRepository.save(any(CartaoData.class))).thenReturn(any(CartaoData.class));
		persisteCartaoGatewayImpl.persistir(cartao);
		verify(cartaoRepository, times(1)).save(any(CartaoData.class));
	}
}
