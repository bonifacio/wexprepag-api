package com.bonifacio.wexprepag.api.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.domain.exception.MensagemErro;
import com.bonifacio.wexprepag.api.domain.security.SenhaUtil;

public class VendaNova {

	private static final int TAMANHO_NUMERO_CARTAO = 16;

	private static final int TAMANHO_CVV = 3;

	private static final int TAMANHO_SENHA = 4;

	private String numeroCartao;
	
	private LocalDate validade;
	
	private String cvv;
	
	private String estabelecimento;
	
	private BigDecimal valor;
	
	private String senha;
	
	private CartaoLeitura cartao;

	private BigDecimal saldoAnterior;
	
	private VendaNova(Builder builder) {
		
		this.numeroCartao = Objects.requireNonNull(builder.numeroCartao, "numeroCartao não pode ser nulo");
		this.validade = Objects.requireNonNull(builder.validade, "validade não pode ser nulo");
		this.cvv = Objects.requireNonNull(builder.cvv, "cvv não pode ser nulo");
		this.estabelecimento = Objects.requireNonNull(builder.estabelecimento, "estabelecimento não pode ser nulo");
		this.valor = Objects.requireNonNull(builder.valor, "valor não pode ser nulo");
		this.senha = Objects.requireNonNull(builder.senha, "senha não pode ser nulo");
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public String getCvv() {
		return cvv;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getSenha() {
		return senha;
	}
	
	public CartaoLeitura getCartao() {
		return cartao;
	}
	
	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}
	
	public void setCartao(CartaoLeitura cartaoLeitura) {
		
		validar(cartaoLeitura);
		this.cartao = cartaoLeitura;
		this.saldoAnterior = cartao.getSaldo();
	}

	private void validar(CartaoLeitura cartao) {
		if (cartao.getValidade().isBefore(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()))) {
			throw new BusinessException(MensagemErro.CARTAO_EXPIRADO);
		}
		if (!this.cvv.equals(cartao.getCvv())) {
			throw new BusinessException(MensagemErro.CVV_INVALIDO);
		}
		if (!SenhaUtil.encriptar(senha).equals(cartao.getSenha())) {
			throw new BusinessException(MensagemErro.SENHA_INVALIDA);
		}
		if (cartao.getSaldo().add(valor.negate()).compareTo(BigDecimal.ZERO) < 0) {
			throw new BusinessException(MensagemErro.SALDO_INSUFICIENTE);
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		
		private String numeroCartao;
		private LocalDate validade;
		private String cvv;
		private String estabelecimento;
		private BigDecimal valor;
		private String senha;

		private Builder() { }

		public Builder comCartao(String cartao) {
			
			Objects.requireNonNull(cartao, "cartao não pode ser nulo");
			if (!StringUtils.isNumeric(cartao)) {
				throw new IllegalArgumentException("cartao só deve possuir valores numéricos");
			}
			if (cartao.trim().length() != TAMANHO_NUMERO_CARTAO) {
				throw new IllegalArgumentException(String.format("cartao deve conter %s dígitos", TAMANHO_NUMERO_CARTAO));
			}
			
			this.numeroCartao = cartao;
			return this;
		}

		public Builder comValidade(LocalDate validade) {
			
			Objects.requireNonNull(validade, "validade não pode ser nulo");
			LocalDate periodoAtual = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
			if (validade.isBefore(periodoAtual)) {
				throw new IllegalArgumentException("vencimento informado já foi ultrapassado");
			}
			
			this.validade = validade;
			return this;
		}

		public Builder comCvv(String cvv) {
			
			Objects.requireNonNull(cvv, "cvv não pode ser nulo");
			if (!StringUtils.isNumeric(cvv)) {
				throw new IllegalArgumentException("cvv só deve possuir valores numéricos");
			}
			if (cvv.trim().length() != TAMANHO_CVV) {
				throw new IllegalArgumentException(String.format("cvv deve conter %s dígitos", TAMANHO_CVV));
			}
			
			this.cvv = cvv;
			return this;
		}

		public Builder comEstabelecimento(String estabelecimento) {
			
			Objects.requireNonNull(estabelecimento, "estabelecimento não pode ser nulo");
			if (StringUtils.isEmpty(estabelecimento.trim())) {
				throw new IllegalArgumentException("estabelecimento não pode ser vazio");
			}
			
			this.estabelecimento = estabelecimento;
			return this;
		}

		public Builder comValor(BigDecimal valor) {
			
			Objects.requireNonNull(valor, "valor não pode ser nulo");
			if (valor.compareTo(BigDecimal.ZERO) <= 0) {
				throw new IllegalArgumentException("valor deve ser maior ou igual a zero");
			}
			
			this.valor = valor;
			return this;
		}

		public Builder comSenha(String senha) {
			
			Objects.requireNonNull(senha, "senha não pode ser nulo");
			if (!StringUtils.isNumeric(senha)) {
				throw new IllegalArgumentException("senha só deve possuir valores numéricos");
			}
			if (senha.trim().length() != TAMANHO_SENHA) {
				throw new IllegalArgumentException(String.format("senha deve conter %s dígitos", TAMANHO_SENHA));
			}
			
			this.senha = senha;
			return this;
		}

		public VendaNova build() {
			return new VendaNova(this);
		}
	}
}
