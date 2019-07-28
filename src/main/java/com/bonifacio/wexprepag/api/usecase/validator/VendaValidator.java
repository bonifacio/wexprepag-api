package com.bonifacio.wexprepag.api.usecase.validator;

import java.math.BigDecimal;

import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.domain.Venda;
import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.domain.exception.MensagemErro;

public class VendaValidator {

	public static void validar(Venda venda, Cartao cartao) {
		
		if (!venda.getCvv().equals(cartao.getCvv())) {
			throw new BusinessException(MensagemErro.CVV_INVALIDO);
		}
		
		if (cartao.getSaldo().add(venda.getValor().negate()).compareTo(BigDecimal.ZERO) < 0) {
			throw new BusinessException(MensagemErro.SALDO_INSUFICIENTE);
		}
	}
}
