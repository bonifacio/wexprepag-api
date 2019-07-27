package com.bonifacio.wexprepag.api.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.domain.ResultadoOperacao;
import com.bonifacio.wexprepag.api.domain.Venda;
import com.bonifacio.wexprepag.api.gateway.BuscaCartaoGateway;
import com.bonifacio.wexprepag.api.usecase.validator.VendaValidator;

@Service
public class AutorizaVenda {

	private BuscaCartaoGateway buscaCartaoGateway;
	
	private RegistraTransacao registraTransacao;

	@Transactional
	public ResultadoOperacao executar(Venda venda) {

		
		Cartao cartao = buscaCartaoGateway.buscarPor(venda.getCartao());
		VendaValidator.validar(venda, cartao);
		registraTransacao.executar();
//		Transacao transacao = Transacao.builder()
//			.comEstabelecimento(venda.getEstabelecimento())
//			.comData(LocalDateTime.now())
//			.comSaldoAnterior(cartao.getSaldo())
//			.comValor(venda.getValor());
//			.build();
			
		return null;
	}

}
