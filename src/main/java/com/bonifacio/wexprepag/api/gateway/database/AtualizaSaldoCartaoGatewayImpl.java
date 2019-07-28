package com.bonifacio.wexprepag.api.gateway.database;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.gateway.AtualizaSaldoCartaoGateway;
import com.bonifacio.wexprepag.api.gateway.database.entity.Cartao;
import com.bonifacio.wexprepag.api.gateway.database.repository.CartaoRepository;

@Service
public class AtualizaSaldoCartaoGatewayImpl implements AtualizaSaldoCartaoGateway {

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
		} else {
			throw new BusinessException("Erro ao atualizar o saldo");
		}
	}
}
