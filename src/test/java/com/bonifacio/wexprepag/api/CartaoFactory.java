package com.bonifacio.wexprepag.api;

import java.math.BigDecimal;

import com.bonifacio.wexprepag.api.domain.CartaoLeitura;
import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.domain.security.SenhaUtil;

public class CartaoFactory {

	public static CartaoLeitura construir(VendaNova venda) {

		return CartaoLeitura.builder()
				.comNumero(venda.getNumeroCartao())
				.comValidade(venda.getValidade())
				.comSaldo(BigDecimal.TEN)
				.comSenha(SenhaUtil.encriptar(venda.getSenha()))
				.build();
	}

}
