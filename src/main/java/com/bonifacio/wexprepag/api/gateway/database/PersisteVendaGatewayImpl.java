package com.bonifacio.wexprepag.api.gateway.database;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.gateway.PersisteVendaGateway;
import com.bonifacio.wexprepag.api.gateway.database.entity.Cartao;
import com.bonifacio.wexprepag.api.gateway.database.entity.Venda;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;
import com.bonifacio.wexprepag.api.gateway.database.repository.TransacaoRepository;

@Service
public class PersisteVendaGatewayImpl implements PersisteVendaGateway {
	
	private CartaoRepository cartaoRepository;
	
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	public PersisteVendaGatewayImpl(CartaoRepository cartaoRepository, TransacaoRepository transacaoRepository) {
		this.cartaoRepository = cartaoRepository;
		this.transacaoRepository = transacaoRepository;
	}

	@Override
	public void persistir(VendaNova vendaNova) {
		
		Venda venda = Venda.of(vendaNova);
		
		Optional<Cartao> cartao = cartaoRepository.findById(vendaNova.getNumeroCartao());
		if (cartao.isPresent()) {
			venda.setCartao(cartao.get());
			transacaoRepository.save(venda);
		} else {
			throw new BusinessException("Erro ao persitir a venda");
		}
	}
}
