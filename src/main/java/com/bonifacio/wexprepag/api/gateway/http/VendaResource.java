package com.bonifacio.wexprepag.api.gateway.http;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonifacio.wexprepag.api.domain.ResultadoOperacao;
import com.bonifacio.wexprepag.api.domain.Venda;
import com.bonifacio.wexprepag.api.gateway.http.model.AutorizacaoVendaRequest;
import com.bonifacio.wexprepag.api.gateway.http.model.AutorizacaoVendaResponse;
import com.bonifacio.wexprepag.api.usecase.AutorizaVenda;

@RestController
@RequestMapping("/venda")
public class VendaResource {
	
	private AutorizaVenda autorizaVenda;
	
	private ModelMapper mapper;

	@Autowired
	public VendaResource(AutorizaVenda autorizaVenda, ModelMapper mapper) {
		this.autorizaVenda = autorizaVenda;
		this.mapper = mapper;
	}

	@PostMapping("/autorizacao")
	public ResponseEntity<AutorizacaoVendaResponse> autorizar(@RequestBody AutorizacaoVendaRequest autorizacaoVendaRequest) {
		ResultadoOperacao resultadoOperacao = autorizaVenda.executar(mapper.map(autorizacaoVendaRequest, Venda.class));
		return ResponseEntity.ok(mapper.map(resultadoOperacao, AutorizacaoVendaResponse.class));
	}
}
