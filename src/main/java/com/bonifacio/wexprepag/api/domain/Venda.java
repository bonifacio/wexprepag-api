package com.bonifacio.wexprepag.api.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class Venda {

	private static final int TAMANHO_NUMERO_CARTAO = 16;

	private static final int TAMANHO_CVV = 3;

	private static final int TAMANHO_SENHA = 4;

	private String cartao;
	
	private LocalDate validade;
	
	private String cvv;
	
	private String estabelecimento;
	
	private BigDecimal valor;
	
	private String senha;

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		
		this.cartao = Objects.requireNonNull(cartao);
		if (!StringUtils.isNumeric(this.cartao)) {
			throw new IllegalArgumentException("cartao só deve possuir valores numéricos");
		}
		if (this.cartao.trim().length() != TAMANHO_NUMERO_CARTAO) {
			throw new IllegalArgumentException(String.format("cartao deve conter %s dígitos", TAMANHO_NUMERO_CARTAO));
		}
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		
		this.validade = Objects.requireNonNull(validade);
		if (this.validade.isBefore(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()))) {
			throw new IllegalArgumentException("vencimento informado já foi ultrapassado");
		}
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		
		this.cvv = Objects.requireNonNull(cvv);
		if (!StringUtils.isNumeric(this.cvv)) {
			throw new IllegalArgumentException("cvv só deve possuir valores numéricos");
		}
		if (this.cvv.trim().length() != TAMANHO_CVV) {
			throw new IllegalArgumentException(String.format("cvv deve conter %s dígitos", TAMANHO_CVV));
		}
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = Objects.requireNonNull(estabelecimento);
		if (StringUtils.isEmpty(this.estabelecimento)) {
			throw new IllegalArgumentException("estabelecimento não pode ser vazio");
		}
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = Objects.requireNonNull(valor);
		if (this.valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("valor deve ser maior ou igual a zero");
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = Objects.requireNonNull(senha);
		if (!StringUtils.isNumeric(this.senha)) {
			throw new IllegalArgumentException("senha só deve possuir valores numéricos");
		}
		if (this.senha.trim().length() != TAMANHO_SENHA) {
			throw new IllegalArgumentException(String.format("senha deve conter %s dígitos", TAMANHO_SENHA));
		}
	}
}
