package com.bonifacio.wexprepag.api.usecase;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.domain.ResultadoOperacao;
import com.bonifacio.wexprepag.api.domain.Transacao;
import com.bonifacio.wexprepag.api.domain.Venda;
import com.bonifacio.wexprepag.api.gateway.BuscaCartaoGateway;
import com.bonifacio.wexprepag.api.usecase.validator.VendaValidator;

@Service
public class AutorizaVenda {

	private BuscaCartaoGateway buscaCartaoGateway;
	
	private RegistraTransacao registraTransacao;
	
	@Autowired
	public AutorizaVenda(BuscaCartaoGateway buscaCartaoGateway, RegistraTransacao registraTransacao) {
		this.buscaCartaoGateway = buscaCartaoGateway;
		this.registraTransacao = registraTransacao;
	}

	@Transactional
	public ResultadoOperacao executar(Venda venda) {

		Cartao cartao = buscaCartaoGateway.buscarPor(venda.getCartao());
		
		VendaValidator.validar(venda, cartao);
		
		Transacao transacao = Transacao.builder()
			.comEstabelecimento(venda.getEstabelecimento())
			.comData(LocalDateTime.now())
			.comSaldoAnterior(cartao.getSaldo())
			.comValor(venda.getValor().negate())
			.comCartao(cartao)
			.build();
		
		registraTransacao.executar(transacao);
		
		return new ResultadoOperacao();
	}

}
