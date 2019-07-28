package com.bonifacio.wexprepag.api.usecase;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.CartaoNovo;
import com.bonifacio.wexprepag.api.gateway.PersisteCartaoGateway;

@Service
public class EmiteCartao {
	
	private PersisteCartaoGateway persisteCartaoGateway;
	
	@Autowired
	public EmiteCartao(PersisteCartaoGateway criaCartaoGateway) {
		this.persisteCartaoGateway = criaCartaoGateway;
	}

	public CartaoNovo emitir(String nome, BigDecimal saldo) {
		
		CartaoNovo cartao = new CartaoNovo(nome, saldo);
		persisteCartaoGateway.persistir(cartao);
		return cartao;
	}
}
