package com.bonifacio.wexprepag.api.gateway;

import java.math.BigDecimal;

public interface AtualizaSaldoCartaoGateway {

	void atualizar(String numeroCartao, BigDecimal saldoAtual);
}
