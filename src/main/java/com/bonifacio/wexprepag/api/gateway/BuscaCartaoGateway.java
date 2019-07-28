package com.bonifacio.wexprepag.api.gateway;

import com.bonifacio.wexprepag.api.domain.CartaoLeitura;

public interface BuscaCartaoGateway {

	CartaoLeitura buscarPor(String numero);
}
