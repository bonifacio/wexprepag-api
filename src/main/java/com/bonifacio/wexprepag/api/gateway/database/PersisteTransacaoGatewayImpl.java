package com.bonifacio.wexprepag.api.gateway.database;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.Transacao;
import com.bonifacio.wexprepag.api.gateway.PersisteTransacaoGateway;
import com.bonifacio.wexprepag.api.gateway.database.entity.TransacaoData;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;
import com.bonifacio.wexprepag.api.gateway.database.repository.TransacaoRepository;

@Service
public class PersisteTransacaoGatewayImpl implements PersisteTransacaoGateway {
	
	private CartaoRepository cartaoRepository;
	
	private TransacaoRepository transacaoRepository;
	
	private ModelMapper mapper;

	@Autowired
	public PersisteTransacaoGatewayImpl(
			CartaoRepository cartaoRepository,
			TransacaoRepository transacaoRepository,
			ModelMapper mapper) {
		this.cartaoRepository = cartaoRepository;
		this.transacaoRepository = transacaoRepository;
		this.mapper = mapper;	
	}

	@Override
	public void persistir(Transacao transacao) {
		TransacaoData transacaoData = mapper.map(transacao, TransacaoData.class);
		transacaoRepository.save(transacaoData);
		cartaoRepository.save(transacaoData.getCartao());
	}
}
