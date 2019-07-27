package com.bonifacio.wexprepag.api.usecase;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.gateway.PersisteCartaoGateway;

@Service
public class EmiteCartao {
	
	private static final long VALIDADE_EM_ANOS = 2;

	private static final String BIN = "123456";
	
	private PersisteCartaoGateway persisteCartaoGateway;
	
	@Autowired
	public EmiteCartao(PersisteCartaoGateway criaCartaoGateway) {
		this.persisteCartaoGateway = criaCartaoGateway;
	}

	public Cartao executar(String nome, BigDecimal saldo) {
		
		Cartao cartao = Cartao.builder()
			.comNome(nome)
			.comSaldo(saldo)
			.comNumero(gerarNumeroCartao())
			.comSenha(gerarSenha())
			.comValidade(obterValidade())
			.build();
		
		return persisteCartaoGateway.persistir(cartao);
	}

	private LocalDate obterValidade() {
		return LocalDate.now().plusYears(VALIDADE_EM_ANOS);
	}

	private String gerarSenha() {
		return RandomStringUtils.random(4, false, true);
	}

	private String gerarNumeroCartao() {
		return new StringBuilder(BIN).append(RandomStringUtils.random(10, false, true)).toString();
	}
}
