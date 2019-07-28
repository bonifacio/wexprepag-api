package com.bonifacio.wexprepag.api.gateway.http;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonifacio.wexprepag.api.domain.CartaoNovo;
import com.bonifacio.wexprepag.api.gateway.http.model.EmissaoCartaoRequest;
import com.bonifacio.wexprepag.api.gateway.http.model.EmissaoCartaoResponse;
import com.bonifacio.wexprepag.api.usecase.EmiteCartao;

@RestController
@RequestMapping("/cartao")
public class CartaoResource {
	
	private EmiteCartao emiteCartao;

	@Autowired
	public CartaoResource(EmiteCartao emiteCartao) {
		this.emiteCartao = emiteCartao;
	}
	
	@PostMapping
	public ResponseEntity<EmissaoCartaoResponse> emitir(@Valid @RequestBody EmissaoCartaoRequest emissaoCartaoRequestBody) {
		CartaoNovo cartao = emiteCartao.emitir(emissaoCartaoRequestBody.getNome(), emissaoCartaoRequestBody.getSaldo());
		return ResponseEntity.ok(EmissaoCartaoResponse.of(cartao));
	}
}
