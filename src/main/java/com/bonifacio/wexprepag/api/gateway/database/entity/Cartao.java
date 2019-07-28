package com.bonifacio.wexprepag.api.gateway.database.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.bonifacio.wexprepag.api.domain.CartaoLeitura;
import com.bonifacio.wexprepag.api.domain.CartaoNovo;

@Entity
public class Cartao {

	@Id
	private String numero;

	private String nome;

	private LocalDate validade;

	private String senha;

	private BigDecimal saldo;
	
	@Deprecated
	public Cartao() { }

	private Cartao(CartaoNovo cartao) {
		this.numero = cartao.getNumero();
		this.nome = cartao.getNome();
		this.validade = cartao.getValidade();
		this.senha = cartao.getSenhaCriptografada();
		this.saldo = cartao.getSaldo();
	}

	public String getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public String getSenha() {
		return senha;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public static Cartao of(CartaoNovo cartao) {
		return new Cartao(cartao);
	}

	public CartaoLeitura getCartaoLeitura() {
		
		return CartaoLeitura.builder()
				.comNome(nome)
				.comNumero(numero)
				.comSaldo(saldo)
				.comSenha(senha)
				.comValidade(validade)
				.build();
	}
}
