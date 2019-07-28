package com.bonifacio.wexprepag.api.gateway;

import com.bonifacio.wexprepag.api.domain.CartaoNovo;

public interface PersisteCartaoGateway {

	void persistir(CartaoNovo cartao);
}
