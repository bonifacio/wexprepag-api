package com.bonifacio.wexprepag.api.gateway.http.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bonifacio.wexprepag.api.gateway.http.deserialize.VencimentoDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class AutorizacaoVendaRequest {

	private String cartao;
	
	@JsonDeserialize(using = VencimentoDeserializer.class)
	private LocalDate validade;
	
	private String cvv;
	
	private String estabelecimento;
	
	private BigDecimal valor;
	
	private String senha;

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
