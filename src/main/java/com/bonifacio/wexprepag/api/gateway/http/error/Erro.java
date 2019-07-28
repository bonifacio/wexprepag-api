package com.bonifacio.wexprepag.api.gateway.http.error;

public class Erro {

	private String codigo;
	
	private String mensagem;
	
	public Erro(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Erro(String codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
}
