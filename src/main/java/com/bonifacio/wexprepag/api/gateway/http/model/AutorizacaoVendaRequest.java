package com.bonifacio.wexprepag.api.gateway.http.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bonifacio.wexprepag.api.domain.VendaNova;
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

	public VendaNova getVenda() {
		
		return VendaNova.builder()
				.comCartao(cartao)
				.comCvv(cvv)
				.comEstabelecimento(estabelecimento)
				.comSenha(senha)
				.comValidade(validade)
				.comValor(valor)
				.build();
	}
}
