package com.bonifacio.wexprepag.api.gateway.database;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bonifacio.wexprepag.api.VendaFactory;
import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.gateway.database.entity.Cartao;
import com.bonifacio.wexprepag.api.gateway.database.entity.Venda;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;
import com.bonifacio.wexprepag.api.gateway.database.repository.VendaRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersisteVendaGatewayImplTest {
	
	@Mock
	private CartaoRepository cartaoRepository;
	
	@Mock
	private VendaRepository vendaRepository;
	
	@Mock
	private Cartao cartao;

	@InjectMocks
	private PersisteVendaGatewayImpl persisteVendaGatewayImpl;
	
	@Test(expected = BusinessException.class)
	public void deveLancarExcecao_quandoNaoExistirCartao() {
		
		VendaNova vendaNova = VendaFactory.construir();
		
		when(cartaoRepository.findById(vendaNova.getNumeroCartao())).thenReturn(Optional.empty());
		
		persisteVendaGatewayImpl.persistir(vendaNova);
	}
	
	@Test
	public void deveRegistrarVenda_quandoExistirCartao() {
		
		VendaNova vendaNova = VendaFactory.construir();
		
		when(cartaoRepository.findById(vendaNova.getNumeroCartao())).thenReturn(Optional.of(cartao));
		
		persisteVendaGatewayImpl.persistir(vendaNova);
		
		verify(cartaoRepository, times(1)).findById(vendaNova.getNumeroCartao());
		verify(vendaRepository, times(1)).save(any(Venda.class));
	}
}
