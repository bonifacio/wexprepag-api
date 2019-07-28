package com.bonifacio.wexprepag.api.gateway.http.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.bonifacio.wexprepag.api.domain.CartaoNovo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EmissaoCartaoResponse {

	private String numero;
	
	private String cvv;

	private String nome;

	@JsonFormat(pattern = "MM/yy")
	private LocalDate validade;

	private String senha;

	private BigDecimal saldo;
	
	@Deprecated
	public EmissaoCartaoResponse() { }

	private EmissaoCartaoResponse(CartaoNovo cartao) {
		this.cvv = cartao.getCvv();
		this.nome = cartao.getNome();
		this.numero = cartao.getNumero();
		this.saldo = cartao.getSaldo();
		this.senha = cartao.getSenha();
		this.validade = cartao.getValidade();
	}

	public String getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
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

	public String getCvv() {
		return cvv;
	}

	public static EmissaoCartaoResponse of(CartaoNovo cartao) {
		Objects.requireNonNull(cartao, "o objeto cartao não pode ser nulo");
		return new EmissaoCartaoResponse(cartao);
	}
}
