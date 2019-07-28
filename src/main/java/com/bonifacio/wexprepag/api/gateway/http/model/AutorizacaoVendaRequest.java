package com.bonifacio.wexprepag.api.gateway.http.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.gateway.http.deserialize.VencimentoDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class AutorizacaoVendaRequest {

	@Size(min = 16, max = 16, message = "deve ter 16 dígitos")
	@Pattern(regexp = "\\d+", message = "deve ser numérico")
	private String cartao;
	
	@JsonDeserialize(using = VencimentoDeserializer.class)
	private LocalDate validade;
	
	@Size(min = 3, max = 3, message = "deve ter 3 dígitos")
	@Pattern(regexp = "\\d+", message = "deve ser numérico")
	private String cvv;
	
	@NotBlank(message = "não pode ser vazio")
	private String estabelecimento;
	
	@NotNull(message = "não pode ser nulo")
	@DecimalMin(value = "0.01", message = "deve ser maior que zero")
	private BigDecimal valor;
	
	@Size(min = 4, max = 4, message = "deve ter 4 dígitos")
	@Pattern(regexp = "\\d+", message = "deve ser numérico")
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
