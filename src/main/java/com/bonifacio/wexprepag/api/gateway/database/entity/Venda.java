package com.bonifacio.wexprepag.api.gateway.database.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.bonifacio.wexprepag.api.domain.VendaNova;

@Entity
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String estabelecimento;
	
	private LocalDateTime data;
	
	private BigDecimal saldoAnterior;
	
	private BigDecimal valor;
	
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public Venda() { }

	public Venda(VendaNova vendaNova) {
		estabelecimento = vendaNova.getEstabelecimento();
		data = LocalDateTime.now();
		saldoAnterior = vendaNova.getSaldoAnterior();
		valor = vendaNova.getValor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public static Venda of(VendaNova venda) {
		return new Venda(venda);
	}
}
