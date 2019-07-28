package com.bonifacio.wexprepag.api.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bonifacio.wexprepag.api.domain.security.Cvv;

public class CartaoLeitura {

	private String numero;

	private String nome;

	private LocalDate validade;

	private String senha;

	private BigDecimal saldo;
	
	private CartaoLeitura(Builder builder) {
		
		this.numero = builder.numero;
		this.nome = builder.nome;
		this.validade = builder.validade;
		this.senha = builder.senha;
		this.saldo = builder.saldo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public LocalDate getValidade() {
		return validade;
	}
	
	public String getCvv() {
		return new Cvv(numero, validade).get();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String numero;
		private String nome;
		private LocalDate validade;
		private String senha;
		private BigDecimal saldo;

		private Builder() {
		}

		public Builder comNumero(String numero) {
			this.numero = numero;
			return this;
		}

		public Builder comNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder comValidade(LocalDate validade) {
			this.validade = validade;
			return this;
		}

		public Builder comSenha(String senha) {
			this.senha = senha;
			return this;
		}

		public Builder comSaldo(BigDecimal saldo) {
			this.saldo = saldo;
			return this;
		}

		public CartaoLeitura build() {
			return new CartaoLeitura(this);
		}
	}
}
