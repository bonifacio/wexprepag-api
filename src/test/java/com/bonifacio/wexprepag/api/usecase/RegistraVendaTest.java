package com.bonifacio.wexprepag.api.usecase;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bonifacio.wexprepag.api.VendaFactory;
import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.gateway.PersisteVendaGateway;

@RunWith(MockitoJUnitRunner.class)
public class RegistraVendaTest {
	
	@Mock
	private PersisteVendaGateway persisteVendaGateway;

	@InjectMocks
	private RegistraVenda registraVenda;
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoUmaVendaNovaNuloForPassado() {
		registraVenda.registrar(null);
	}

	@Test
	public void deveRegistrarUmaVenda_quandoUmaVendaValidaForPassado() {
		VendaNova vendaNova = VendaFactory.construir();
		doNothing().when(persisteVendaGateway).persistir(vendaNova);
		registraVenda.registrar(vendaNova);
		verify(persisteVendaGateway, times(1)).persistir(vendaNova);
	}
}
