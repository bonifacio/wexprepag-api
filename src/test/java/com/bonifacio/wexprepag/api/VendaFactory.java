package com.bonifacio.wexprepag.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import com.bonifacio.wexprepag.api.domain.CartaoLeitura;
import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.domain.security.Cvv;

public class VendaFactory {

	private VendaFactory() { }
	
	public static VendaNova construir() {

		String numero = "1234567890987654";
		LocalDate validade = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

		VendaNova vendaNova = VendaNova.builder()
				.comCartao(numero)
				.comCvv(new Cvv(numero, validade).get())
				.comEstabelecimento("Loja")
				.comSenha("1234")
				.comValidade(validade)
				.comValor(BigDecimal.valueOf(1))
				.build();
		
		CartaoLeitura cartaoLeitura = CartaoFactory.construir(vendaNova);
		vendaNova.setCartao(cartaoLeitura);
		return vendaNova;
	}
}
