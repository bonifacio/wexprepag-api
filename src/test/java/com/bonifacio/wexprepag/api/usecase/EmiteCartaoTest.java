package com.bonifacio.wexprepag.api.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bonifacio.wexprepag.api.CartaoFactory;
import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.gateway.PersisteCartaoGateway;

@RunWith(MockitoJUnitRunner.class)
public class EmiteCartaoTest {
	
	@Mock
	private PersisteCartaoGateway persisteCartaoGateway;
	
	@InjectMocks
	private EmiteCartao emiteCartao;

	@Test
	public void deveEmitirCartao() {
		
		Cartao cartao = CartaoFactory.umCartao();
		
		when(persisteCartaoGateway.persistir(any())).thenReturn(cartao);
		Cartao cartaoRetornado = emiteCartao.executar("João", BigDecimal.valueOf(1000));
		cartaoRetornado.getCvv();
	}
}
