package com.bonifacio.wexprepag.api.gateway.database.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bonifacio.wexprepag.api.domain.Cartao;

@Entity
@Table(name = "cartao")
public class CartaoData {

	@Id
	private String numero;

	private String nome;

	private LocalDate validade;

	private String senha;

	private BigDecimal saldo;
	
	@Deprecated
	public CartaoData() { }

	private CartaoData(Cartao cartao) {
		this.numero = cartao.getNumero();
		this.nome = cartao.getNumero();
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

	public static CartaoData of(Cartao cartao) {
		return new CartaoData(cartao);
	}
}
