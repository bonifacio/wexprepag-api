package com.bonifacio.wexprepag.api.usecase;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bonifacio.wexprepag.api.VendaFactory;
import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.gateway.AtualizaSaldoCartaoGateway;


@RunWith(MockitoJUnitRunner.class)
public class AtualizaSaldoCartaoTest {

	@Mock
	private AtualizaSaldoCartaoGateway atualizaSaldoCartaoGateway;
	
	@InjectMocks
	private AtualizaSaldoCartao atualizaSaldoCartao;
	
	@Test(expected = NullPointerException.class)
	public void deveLancarExcecao_quandoUmaVendaNovaNuloForPassado() {
		atualizaSaldoCartao.atualizar(null);
	}
	
	@Test
	public void deveAtualizarSaldo_quandoUmaVendaValidaForPassado() {
		
		VendaNova vendaNova = VendaFactory.construir();
		BigDecimal saldoAtual = vendaNova.getSaldoAnterior().subtract(vendaNova.getValor());
		
		doNothing().when(atualizaSaldoCartaoGateway).atualizar(vendaNova.getNumeroCartao(), saldoAtual);
		
		atualizaSaldoCartao.atualizar(vendaNova);
		
		verify(atualizaSaldoCartaoGateway, times(1)).atualizar(vendaNova.getNumeroCartao(), saldoAtual);
	}
}
