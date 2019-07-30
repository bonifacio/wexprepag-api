package com.bonifacio.wexprepag.api.gateway.http.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bonifacio.wexprepag.api.domain.VendaLeitura;
import com.fasterxml.jackson.annotation.JsonFormat;

public class VendaResponse {

	private String estabelecimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime data;
	
	private BigDecimal valor;
	
	public String getEstabelecimento() {
		return estabelecimento;
	}

	public LocalDateTime getData() {
		return data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	private VendaResponse(VendaLeitura vendaLeitura) {
		this.estabelecimento = vendaLeitura.getEstabelecimento();
		this.data = vendaLeitura.getData();
		this.valor = vendaLeitura.getValor();
	}

	public static VendaResponse of(VendaLeitura vendaLeitura) {
		return new VendaResponse(vendaLeitura);
	}
}
