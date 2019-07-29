package com.bonifacio.wexprepag.api.gateway.database;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.gateway.PersisteVendaGateway;
import com.bonifacio.wexprepag.api.gateway.database.entity.Cartao;
import com.bonifacio.wexprepag.api.gateway.database.entity.Venda;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;
import com.bonifacio.wexprepag.api.gateway.database.repository.VendaRepository;

@Service
public class PersisteVendaGatewayImpl implements PersisteVendaGateway {
	
	private static final Logger LOG = LoggerFactory.getLogger(PersisteVendaGatewayImpl.class);
	
	private CartaoRepository cartaoRepository;
	
	private VendaRepository vendaRepository;
	
	@Autowired
	public PersisteVendaGatewayImpl(CartaoRepository cartaoRepository, VendaRepository vendaRepository) {
		this.cartaoRepository = cartaoRepository;
		this.vendaRepository = vendaRepository;
	}

	@Override
	public void persistir(VendaNova vendaNova) {
		
		Venda venda = Venda.of(vendaNova);
		
		Optional<Cartao> cartao = cartaoRepository.findById(vendaNova.getNumeroCartao());
		if (cartao.isPresent()) {
			venda.setCartao(cartao.get());
			vendaRepository.save(venda);
			LOG.info("Registrando venda {} com o valor {} para o cartão {}", venda.getId(), venda.getValor(), vendaNova.getNumeroCartao());
		} else {
			LOG.error("Erro ao persistir venda");
			throw new BusinessException("Erro ao persitir a venda");
		}
	}
}
