package com.bonifacio.wexprepag.api.domain.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -8115326227778571977L;

	private String codigo;
	
	public BusinessException(String codigo, String mensagem) {
		super(mensagem);
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
}
