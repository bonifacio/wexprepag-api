package com.bonifacio.wexprepag.api.gateway.http;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonifacio.wexprepag.api.domain.Cartao;
import com.bonifacio.wexprepag.api.gateway.http.model.EmissaoCartaoRequest;
import com.bonifacio.wexprepag.api.gateway.http.model.EmissaoCartaoResponse;
import com.bonifacio.wexprepag.api.usecase.EmiteCartao;

@RestController
@RequestMapping("/cartao")
public class CartaoResource {
	
	private EmiteCartao emiteCartao;
	private ModelMapper mapper;

	@Autowired
	public CartaoResource(EmiteCartao emiteCartao, ModelMapper mapper) {
		this.emiteCartao = emiteCartao;
		this.mapper = mapper;
	}
	
	@PostMapping
	public ResponseEntity<EmissaoCartaoResponse> emitir(@RequestBody EmissaoCartaoRequest emissaoCartaoRequestBody) {
		
		Cartao cartao = emiteCartao.executar(emissaoCartaoRequestBody.getNome(), emissaoCartaoRequestBody.getSaldo());
		return ResponseEntity.ok(mapper.map(cartao, EmissaoCartaoResponse.class));
	}
}
