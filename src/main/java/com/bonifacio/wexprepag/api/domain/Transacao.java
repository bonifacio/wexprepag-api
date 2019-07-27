package com.bonifacio.wexprepag.api.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {

	private String estabelecimento;
	
	private LocalDateTime data;
	
	private BigDecimal saldoAnterior;
	
	private BigDecimal valor;
	
	private Cartao cartao;

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public LocalDateTime getData() {
		return data;
	}

	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Cartao getCartao() {
		return cartao;
	}

	private Transacao(Builder builder) {
		this.estabelecimento = builder.estabelecimento;
		this.data = builder.data;
		this.saldoAnterior = builder.saldoAnterior;
		this.valor = builder.valor;
		this.cartao = builder.cartao;
		this.cartao.computarTransacao(this);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String estabelecimento;
		private LocalDateTime data;
		private BigDecimal saldoAnterior;
		private BigDecimal valor;
		private Cartao cartao;

		private Builder() { }

		public Builder comEstabelecimento(String estabelecimento) {
			this.estabelecimento = estabelecimento;
			return this;
		}

		public Builder comData(LocalDateTime data) {
			this.data = data;
			return this;
		}

		public Builder comSaldoAnterior(BigDecimal saldoAnterior) {
			this.saldoAnterior = saldoAnterior;
			return this;
		}

		public Builder comValor(BigDecimal valor) {
			this.valor = valor;
			return this;
		}
		
		public Builder comCartao(Cartao cartao) {
			this.cartao = cartao;
			return this;
		}

		public Transacao build() {
			return new Transacao(this);
		}
	}
}
