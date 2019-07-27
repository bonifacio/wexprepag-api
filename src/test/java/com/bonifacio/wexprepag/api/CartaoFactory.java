package com.bonifacio.wexprepag.api;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bonifacio.wexprepag.api.domain.Cartao;

public class CartaoFactory {

	public static Cartao umCartao() {
		
		return Cartao.builder()
				.comNome("João")
				.comNumero("1234567898765432")
				.comSaldo(BigDecimal.valueOf(1000))
				.comSenha("1234")
				.comValidade(LocalDate.now())
				.build();
	}

}
