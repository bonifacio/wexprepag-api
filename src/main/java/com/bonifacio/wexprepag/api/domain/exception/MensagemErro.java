package com.bonifacio.wexprepag.api.domain.exception;

public enum MensagemErro {
	CARTAO_INEXISTENTE("01", "cartão informado não existe"),
	CARTAO_EXPIRADO("02", "cartão expirado"),
	CVV_INVALIDO("03", "cvv inválido"),
	SENHA_INVALIDA("04", "senha inválida"),
	SALDO_INSUFICIENTE("05", "saldo insuficiente"),
	DATA_VALIDADE_INVALIDA("06", "data de validade inválida");
	
	private String codigo;
	
	private String mensagem;

	private MensagemErro(String codigo, String mensagem) {
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
