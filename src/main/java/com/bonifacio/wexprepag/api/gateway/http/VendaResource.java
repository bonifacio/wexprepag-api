package com.bonifacio.wexprepag.api.gateway.http;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bonifacio.wexprepag.api.domain.ResultadoOperacao;
import com.bonifacio.wexprepag.api.gateway.http.model.AutorizacaoVendaRequest;
import com.bonifacio.wexprepag.api.gateway.http.model.AutorizacaoVendaResponse;
import com.bonifacio.wexprepag.api.gateway.http.model.VendaResponse;
import com.bonifacio.wexprepag.api.usecase.AutorizaVenda;
import com.bonifacio.wexprepag.api.usecase.ConsultaVenda;

@RestController
@RequestMapping("/venda")
public class VendaResource {
	
	private AutorizaVenda autorizaVenda;
	
	private ConsultaVenda consultaVenda;
	
	@Autowired
	public VendaResource(AutorizaVenda autorizaVenda, ConsultaVenda consultaVenda) {
		this.autorizaVenda = autorizaVenda;
		this.consultaVenda = consultaVenda;
	}

	@PostMapping("/autorizacao")
	public ResponseEntity<AutorizacaoVendaResponse> autorizar(@Valid @RequestBody AutorizacaoVendaRequest autorizacaoVendaRequest) {
		ResultadoOperacao resultadoOperacao = autorizaVenda.autorizar(autorizacaoVendaRequest.getVenda());
		return ResponseEntity.ok(AutorizacaoVendaResponse.of(resultadoOperacao));
	}
	
	@GetMapping("/{cartao}")
	public ResponseEntity<List<VendaResponse>> obterVendas(@PathVariable("cartao") String cartao) {
		
		List<VendaResponse> vendas = consultaVenda.consultar(cartao)
				.stream().map(vendaLeitura -> VendaResponse.of(vendaLeitura)).collect(Collectors.toList());
		return ResponseEntity.ok(vendas);
	}
}
