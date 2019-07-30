package com.bonifacio.wexprepag.api.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VendaLeitura {

	private String estabelecimento;
	
	private LocalDateTime data;
	
	private BigDecimal valor;
	
	public VendaLeitura(String estabelecimento, LocalDateTime data, BigDecimal valor) {
		super();
		this.estabelecimento = estabelecimento;
		this.data = data;
		this.valor = valor;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public LocalDateTime getData() {
		return data;
	}

	public BigDecimal getValor() {
		return valor;
	}
}
