package com.bonifacio.wexprepag.api.gateway.database;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.CartaoNovo;
import com.bonifacio.wexprepag.api.gateway.PersisteCartaoGateway;
import com.bonifacio.wexprepag.api.gateway.database.entity.Cartao;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;

@Service
public class PersisteCartaoGatewayImpl implements PersisteCartaoGateway {
	
	private static final Logger LOG = LoggerFactory.getLogger(PersisteCartaoGatewayImpl.class);
	
	private CartaoRepository cartaoRepository;
	
	@Autowired
	public PersisteCartaoGatewayImpl(CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}

	@Override
	public void persistir(CartaoNovo cartao) {
		Objects.requireNonNull(cartao, "O objeto cartao não pode ser nulo");
		cartaoRepository.save(Cartao.of(cartao));
		LOG.info("Cartão {} gravado na base de dados", cartao.getNumero());
	}
}
