package com.bonifacio.wexprepag.api.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import com.bonifacio.wexprepag.api.domain.security.Cvv;
import com.bonifacio.wexprepag.api.domain.security.SenhaUtil;

public class CartaoNovo {
	
	private static final long VALIDADE_EM_ANOS = 2;
	
	private static final String BIN = "123456";

	private String numero;
	
	private String nome;
	
	private LocalDate validade;
	
	private String senha;
	
	private BigDecimal saldo;

	private String senhaCriptografada;
	
	public CartaoNovo(String nome, BigDecimal saldo) {
		
		this.nome = validarNome(nome);
		this.saldo = validarSaldo(saldo);
		this.senha = gerarSenha();
		this.senhaCriptografada = SenhaUtil.encriptar(this.senha);
		this.numero = gerarNumeroCartao();
		this.validade = gerarValidade();
	}

	public String getNome() {
		return nome;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public LocalDate getValidade() {
		return validade;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}
	
	public String getCvv() {
		return new Cvv(numero, validade).get();
	}
	
	private String validarNome(String nome) {
		
		Objects.requireNonNull(nome, "nome não pode ser nulo");
		if (StringUtils.isEmpty(nome.trim())) {
			throw new IllegalArgumentException("nome não pode ser vazio");
		}
		return nome;
	}
	
	private BigDecimal validarSaldo(BigDecimal saldo) {
		
		Objects.requireNonNull(saldo, "saldo não pode ser nulo");
		if (saldo.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("saldo não pode ser negativo");
		}
		return saldo;
	}
	
	private LocalDate gerarValidade() {
		return LocalDate.now().plusYears(VALIDADE_EM_ANOS).with(TemporalAdjusters.lastDayOfMonth());
	}
	
	private String gerarNumeroCartao() {
		return new StringBuilder(BIN).append(RandomStringUtils.random(10, false, true)).toString();
	}
	
	private String gerarSenha() {
		return RandomStringUtils.random(4, false, true);
	}
}
