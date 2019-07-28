package com.bonifacio.wexprepag.api.domain.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -8115326227778571977L;

	private String codigo;
	
	public BusinessException(MensagemErro mensagemErro) {
		super(mensagemErro.getMensagem());
		this.codigo = mensagemErro.getCodigo();
	}

	public String getCodigo() {
		return codigo;
	}
}
