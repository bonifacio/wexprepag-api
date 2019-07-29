package com.bonifacio.wexprepag.api.gateway.database;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.CartaoLeitura;
import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.domain.exception.MensagemErro;
import com.bonifacio.wexprepag.api.gateway.BuscaCartaoGateway;
import com.bonifacio.wexprepag.api.gateway.database.entity.Cartao;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;

@Service
public class BuscaCartaoGatewayImpl implements BuscaCartaoGateway {

	private CartaoRepository cartaoRepository;
	
	@Autowired
	public BuscaCartaoGatewayImpl(CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}
	
	@Override
	public CartaoLeitura buscarPor(String numero) {
		Optional<Cartao> cartaoData = cartaoRepository.findById(numero);
		if (!cartaoData.isPresent()) {
			throw new BusinessException(MensagemErro.CARTAO_INEXISTENTE);
		}
		return cartaoData.get().getCartaoLeitura();
	}
}
