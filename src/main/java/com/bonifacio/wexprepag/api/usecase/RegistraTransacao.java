package com.bonifacio.wexprepag.api.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.Transacao;
import com.bonifacio.wexprepag.api.gateway.PersisteTransacaoGateway;

@Service
public class RegistraTransacao {

	private PersisteTransacaoGateway persisteTransacaoGateway;
	
	@Autowired
	public RegistraTransacao(PersisteTransacaoGateway persisteTransacaoGateway) {
		this.persisteTransacaoGateway = persisteTransacaoGateway;
	}

	public void executar(Transacao transacao) {
		
		persisteTransacaoGateway.persistir(transacao);
	}
}
