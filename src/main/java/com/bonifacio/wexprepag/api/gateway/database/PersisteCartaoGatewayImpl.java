package com.bonifacio.wexprepag.api.gateway.database;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.gateway.PersisteCartaoGateway;
import com.bonifacio.wexprepag.api.gateway.database.entity.CartaoData;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;

@Service
public class PersisteCartaoGatewayImpl implements PersisteCartaoGateway {
	
	private CartaoRepository cartaoRepository;
	private ModelMapper mapper;

	@Autowired
	public PersisteCartaoGatewayImpl(
			CartaoRepository cartaoRepository,
			ModelMapper mapper) {
		this.cartaoRepository = cartaoRepository;
		this.mapper = mapper;
	}

	@Override
	public Cartao persistir(Cartao cartao) {
		cartaoRepository.save(mapper.map(cartao, CartaoData.class));
		return cartao;
	}
}
