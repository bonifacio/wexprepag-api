package com.bonifacio.wexprepag.api.usecase;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.CartaoLeitura;
import com.bonifacio.wexprepag.api.domain.ResultadoOperacao;
import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.gateway.BuscaCartaoGateway;

@Service
public class AutorizaVenda {

	private BuscaCartaoGateway buscaCartaoGateway;
	
	private RegistraVenda registraVenda;

	private AtualizaSaldoCartao atualizaSaldoCartao;
	
	@Autowired
	public AutorizaVenda(
			BuscaCartaoGateway buscaCartaoGateway,
			RegistraVenda registraVenda,
			AtualizaSaldoCartao atualizaSaldoCartao) {
		
		this.buscaCartaoGateway = buscaCartaoGateway;
		this.registraVenda = registraVenda;
		this.atualizaSaldoCartao = atualizaSaldoCartao;
	}

	public ResultadoOperacao autorizar(VendaNova vendaNova) {

		CartaoLeitura cartaoLeitura = buscaCartaoGateway.buscarPor(vendaNova.getNumeroCartao());
		vendaNova.setCartao(cartaoLeitura);
		registraVenda.registrar(vendaNova);
		BigDecimal saldo = atualizaSaldoCartao.atualizar(vendaNova);
		return new ResultadoOperacao(saldo);
	}
}
