package com.bonifacio.wexprepag.api.gateway.database;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.gateway.AtualizaSaldoCartaoGateway;
import com.bonifacio.wexprepag.api.gateway.database.entity.Cartao;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;

@Service
public class AtualizaSaldoCartaoGatewayImpl implements AtualizaSaldoCartaoGateway {

	private static final Logger LOG = LoggerFactory.getLogger(AtualizaSaldoCartaoGatewayImpl.class);
			
	private CartaoRepository cartaoRepository;

	@Autowired
	public AtualizaSaldoCartaoGatewayImpl(CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}
	
	@Override
	public void atualizar(String numeroCartao, BigDecimal saldoAtual) {

		Optional<Cartao> cartao = cartaoRepository.findById(numeroCartao);
		if (cartao.isPresent()) {
			Cartao cartaoRetornado = cartao.get();
			cartaoRetornado.setSaldo(saldoAtual);
			cartaoRepository.save(cartaoRetornado);
			LOG.info("Atualizando o saldo do cartão {} para {}", cartaoRetornado.getNumero(), saldoAtual);
		} else {
			LOG.error("Erro ao atualizar o saldo");
			throw new BusinessException("Erro ao atualizar o saldo");
		}
	}
}
