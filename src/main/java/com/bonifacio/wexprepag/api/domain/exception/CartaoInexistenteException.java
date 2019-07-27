package com.bonifacio.wexprepag.api.domain.exception;

public class CartaoInexistenteException extends BusinessException {

	private static final long serialVersionUID = 4035926261089361940L;

	private static final String CODIGO = "01";

	private static final String MENSAGEM = "cartao não existe";

	public CartaoInexistenteException() {
		super(CODIGO, MENSAGEM);
	}
}
