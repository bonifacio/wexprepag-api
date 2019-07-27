package com.bonifacio.wexprepag.api.gateway;

import com.bonifacio.wexprepag.api.domain.Transacao;

public interface PersisteTransacaoGateway {

	void persistir(Transacao transacao);
}
