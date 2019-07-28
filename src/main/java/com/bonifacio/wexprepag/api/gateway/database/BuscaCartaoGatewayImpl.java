package com.bonifacio.wexprepag.api.gateway.database;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.domain.exception.MensagemErro;
import com.bonifacio.wexprepag.api.gateway.BuscaCartaoGateway;
import com.bonifacio.wexprepag.api.gateway.database.entity.CartaoData;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;

@Service
public class BuscaCartaoGatewayImpl implements BuscaCartaoGateway {

	private CartaoRepository cartaoRepository;
	
	private ModelMapper mapper;

	@Autowired
	public BuscaCartaoGatewayImpl(CartaoRepository cartaoRepository, ModelMapper mapper) {
		this.cartaoRepository = cartaoRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Cartao buscarPor(String numero) {
		Optional<CartaoData> cartaoData = cartaoRepository.findById(numero);
		if (cartaoData.isEmpty()) {
			throw new BusinessException(MensagemErro.CARTAO_INEXISTENTE);
		}
		return mapper.map(cartaoData.get(), Cartao.class);
	}
}
