package com.bonifacio.wexprepag.api.gateway;

import com.bonifacio.wexprepag.api.domain.Cartao;

public interface PersisteCartaoGateway {

	Cartao persistir(Cartao cartao);
}