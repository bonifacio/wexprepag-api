package com.bonifacio.wexprepag.api.usecase;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.gateway.AtualizaSaldoCartaoGateway;

@Service
public class AtualizaSaldoCartao {

	private AtualizaSaldoCartaoGateway atualizaSaldoCartaoGateway;

	@Autowired
	public AtualizaSaldoCartao(AtualizaSaldoCartaoGateway atualizaSaldoCartaoGateway) {
		this.atualizaSaldoCartaoGateway = atualizaSaldoCartaoGateway;
	}
	
	public BigDecimal atualizar(VendaNova vendaNova) {
		
		BigDecimal saldoAtual = vendaNova.getCartao().getSaldo().subtract(vendaNova.getValor());
		atualizaSaldoCartaoGateway.atualizar(vendaNova.getNumeroCartao(), saldoAtual);
		return saldoAtual;
	}
}
