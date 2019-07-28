package com.bonifacio.wexprepag.api.gateway;

import com.bonifacio.wexprepag.api.domain.VendaNova;

public interface PersisteVendaGateway {

	void persistir(VendaNova venda);
}
