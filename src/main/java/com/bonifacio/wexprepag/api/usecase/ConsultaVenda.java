package com.bonifacio.wexprepag.api.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.VendaLeitura;
import com.bonifacio.wexprepag.api.gateway.ConsultaVendaGateway;

@Service
public class ConsultaVenda {

	private ConsultaVendaGateway consultaVendaGateway;
	
	@Autowired
	public ConsultaVenda(ConsultaVendaGateway consultaVendaGateway) {
		this.consultaVendaGateway = consultaVendaGateway;
	}

	public List<VendaLeitura> consultar(String cartao) {
		return consultaVendaGateway.consultar(cartao);
	}
}
