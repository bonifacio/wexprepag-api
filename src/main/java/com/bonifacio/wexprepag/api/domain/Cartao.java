package com.bonifacio.wexprepag.api.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.util.StringUtils;

public class Cartao {

	private String numero;
	
	private String nome;
	
	private LocalDate validade;
	
	private String senha;
	
	private BigDecimal saldo;
	
	@Deprecated
	public Cartao() { }
	
	private Cartao(Builder builder) {
		
		this.nome = Objects.requireNonNull(builder.nome);
		this.numero = Objects.requireNonNull(builder.numero);
		this.senha = Objects.requireNonNull(builder.senha);
		this.saldo = Objects.requireNonNull(builder.saldo);
		this.validade = Objects.requireNonNull(builder.validade);
	}
	
	public String getNome() {
		return nome;
	}
	
	@Deprecated
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}
	
	@Deprecated
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getValidade() {
		return validade;
	}
	
	@Deprecated
	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public String getSenha() {
		return senha;
	}
	
	@Deprecated
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	
	@Deprecated
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public void computarTransacao(Transacao transacao) {
		this.saldo = this.saldo.add(transacao.getValor());
	}
	
	public String getCvv() {
		
		String ultimoDigitoCartao = numero.substring(numero.length()-1);
		int diaDaSemanaVencimento = validade.getDayOfWeek().getValue();
		String nonoDigitoCartao = numero.substring(8, 8 + 1);
		
		StringBuilder cvv = new StringBuilder()
				.append(ultimoDigitoCartao)
				.append(diaDaSemanaVencimento)
				.append(nonoDigitoCartao);
		
		return cvv.toString();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private String nome;
		
		private String numero;
		
		private LocalDate validade;
		
		private String senha;
		
		private BigDecimal saldo;

		private Builder() {
		}

		public Builder comNome(String nome) {
			this.nome = Objects.requireNonNull(nome);
			if (StringUtils.isEmpty(nome.trim())) {
				throw new IllegalArgumentException("nome não pode ser vazio");
			}
			return this;
		}

		public Builder comNumero(String numero) {
			this.numero = Objects.requireNonNull(numero);
			return this;
		}

		public Builder comSenha(String senha) {
			this.senha = Objects.requireNonNull(senha);
			return this;
		}

		public Builder comSaldo(BigDecimal saldo) {
			this.saldo = Objects.requireNonNull(saldo);
			if (this.saldo.compareTo(BigDecimal.ZERO) < 0) {
				throw new IllegalArgumentException("saldo não pode ser negativo");
			}
			return this;
		}
		
		public Builder comValidade(LocalDate validade) {
			this.validade = Objects.requireNonNull(validade);
			if (this.validade.isBefore(LocalDate.now())) {
				throw new IllegalArgumentException("validade deve ser maior ou igual a data atual");
			}
			return this;
		}

		public Cartao build() {
			return new Cartao(this);
		}
	}
}
