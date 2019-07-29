package com.bonifacio.wexprepag.api.gateway.database;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bonifacio.wexprepag.api.domain.CartaoLeitura;
import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.gateway.database.entity.Cartao;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;

@RunWith(MockitoJUnitRunner.class)
public class BuscaCartaoGatewayImplTest {

	@Mock
	private CartaoRepository cartaoRepository;
	
	@Mock
	private Cartao cartao;
	
	@InjectMocks
	private BuscaCartaoGatewayImpl buscaCartaoGatewayImpl;
	
	@Test(expected = BusinessException.class)
	public void deveLancarExcecao_quandoCartaoNaoExistir() {
		
		String numero = "1234567890987654";
		
		when(cartaoRepository.findById(numero)).thenReturn(Optional.empty());
		
		buscaCartaoGatewayImpl.buscarPor(numero);
	}
	
	@Test
	public void deveRetornarOCartao_quandoCartaoExistir() {
		
		String numero = "1234567890987654";
		
		when(cartaoRepository.findById(numero)).thenReturn(Optional.of(cartao));
		when(cartao.getCartaoLeitura()).thenReturn(CartaoLeitura.builder().build());
		
		CartaoLeitura cartaoLeitura = buscaCartaoGatewayImpl.buscarPor(numero);
		
		verify(cartaoRepository, times(1)).findById(numero);
		assertNotNull(cartaoLeitura);
	}
}
