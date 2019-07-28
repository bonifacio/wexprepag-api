package com.bonifacio.wexprepag.api.gateway.http.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EmissaoCartaoRequest {

	@NotBlank(message = "não pode ser vazio")
	private String nome;

	@NotNull(message = "não pode ser nulo")
	@Min(0)
	private BigDecimal saldo;

	public String getNome() {
		return nome;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
}
