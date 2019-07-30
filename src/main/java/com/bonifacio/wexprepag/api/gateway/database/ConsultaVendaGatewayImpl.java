package com.bonifacio.wexprepag.api.gateway.database;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.VendaLeitura;
import com.bonifacio.wexprepag.api.gateway.ConsultaVendaGateway;
import com.bonifacio.wexprepag.api.gateway.database.entity.Venda;
import com.bonifacio.wexprepag.api.gateway.database.repository.VendaRepository;

@Service
public class ConsultaVendaGatewayImpl implements ConsultaVendaGateway {
	
	private VendaRepository vendaRepository;

	@Autowired
	public ConsultaVendaGatewayImpl(VendaRepository vendaRepository) {
		this.vendaRepository = vendaRepository;
	}

	@Override
	public List<VendaLeitura> consultar(String cartao) {
		List<Venda> vendas = vendaRepository.findByCartao(cartao);
		return vendas.stream()
			.map(venda -> new VendaLeitura(venda.getEstabelecimento(), venda.getData(), venda.getValor()))
			.collect(Collectors.toList());
	}
}
