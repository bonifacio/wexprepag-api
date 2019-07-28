package com.bonifacio.wexprepag.api.gateway.http;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonifacio.wexprepag.api.domain.ResultadoOperacao;
import com.bonifacio.wexprepag.api.gateway.http.model.AutorizacaoVendaRequest;
import com.bonifacio.wexprepag.api.gateway.http.model.AutorizacaoVendaResponse;
import com.bonifacio.wexprepag.api.usecase.AutorizaVenda;

@RestController
@RequestMapping("/venda")
public class VendaResource {
	
	private AutorizaVenda autorizaVenda;
	
	@Autowired
	public VendaResource(AutorizaVenda autorizaVenda) {
		this.autorizaVenda = autorizaVenda;
	}

	@PostMapping("/autorizacao")
	public ResponseEntity<AutorizacaoVendaResponse> autorizar(@Valid @RequestBody AutorizacaoVendaRequest autorizacaoVendaRequest) {
		ResultadoOperacao resultadoOperacao = autorizaVenda.autorizar(autorizacaoVendaRequest.getVenda());
		return ResponseEntity.ok(AutorizacaoVendaResponse.of(resultadoOperacao));
	}
}
