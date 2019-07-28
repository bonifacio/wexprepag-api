package com.bonifacio.wexprepag.api.usecase.security;

import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.domain.Venda;
import com.bonifacio.wexprepag.api.domain.exception.BusinessException;
import com.bonifacio.wexprepag.api.domain.exception.MensagemErro;

public class SegurancaValidator {

	public static void validar(Venda venda, Cartao cartao) {
		
		if (!venda.getCvv().equals(cartao.getCvv())) {
			throw new BusinessException(MensagemErro.CVV_INVALIDO);
		}
		if (!SenhaUtil.encriptar(venda.getSenha()).equals(cartao.getSenhaCriptografada())) {
			throw new BusinessException(MensagemErro.SENHA_INVALIDA);
		}
	}
}
