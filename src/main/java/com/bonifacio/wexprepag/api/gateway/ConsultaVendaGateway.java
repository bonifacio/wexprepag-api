package com.bonifacio.wexprepag.api.gateway;

import java.util.List;

import com.bonifacio.wexprepag.api.domain.VendaLeitura;

public interface ConsultaVendaGateway {

	List<VendaLeitura> consultar(String cartao);

}
