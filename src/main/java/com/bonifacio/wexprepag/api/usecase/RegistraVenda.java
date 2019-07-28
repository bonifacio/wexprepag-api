package com.bonifacio.wexprepag.api.usecase;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonifacio.wexprepag.api.domain.VendaNova;
import com.bonifacio.wexprepag.api.gateway.PersisteVendaGateway;

@Service
public class RegistraVenda {

	private PersisteVendaGateway persisteVendaGateway;
	
	@Autowired
	public RegistraVenda(PersisteVendaGateway persisteVendaGateway) {
		this.persisteVendaGateway = persisteVendaGateway;
	}

	public void registrar(VendaNova vendaNova) {
		Objects.requireNonNull(vendaNova, "o objeto vendaNova não pode ser nulo");
		persisteVendaGateway.persistir(vendaNova);
	}
}
