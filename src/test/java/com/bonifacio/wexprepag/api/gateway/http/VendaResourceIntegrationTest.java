package com.bonifacio.wexprepag.api.gateway.http;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bonifacio.wexprepag.api.domain.CartaoNovo;
import com.bonifacio.wexprepag.api.usecase.EmiteCartao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VendaResourceIntegrationTest {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private EmiteCartao emiteCartao;

	@Autowired
	private MockMvc mockMvc;

	private CartaoNovo cartao;

	@Before
	public void emiteCartao() {
		this.cartao = emiteCartao.emitir("João", BigDecimal.valueOf(1000));
	}

	@Test
	public void deveRegistrarVenda_quandoForPassadaUmaVendaValida() throws Exception {

		String content = montarVenda(this.cartao.getSenha());

		mockMvc.perform(post("/venda/autorizacao").contentType(MediaType.APPLICATION_JSON).content(content))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.codigo").exists())
			.andExpect(jsonPath("$.saldo").exists())
			.andReturn();
	}
	
	@Test
	public void deveRetornarBadRequest_quandoNaoForemPassadosNenhumAtributo() throws Exception {

		mockMvc.perform(post("/venda/autorizacao").contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$[0].mensagem").exists())
			.andReturn();
	}
	
	@Test
	public void deveRetornarUnprocessableEntity_quandoASenhaForInvalida() throws Exception {

		String content = montarVenda("0000");
		
		mockMvc.perform(post("/venda/autorizacao").contentType(MediaType.APPLICATION_JSON).content(content))
			.andExpect(status().isUnprocessableEntity())
			.andExpect(jsonPath("$.codigo").exists())
			.andExpect(jsonPath("$.mensagem").exists())
			.andReturn();
	}

	private String montarVenda(String senha) throws JsonProcessingException {

		Map<String, Object> body = new HashMap<>();
		body.put("cartao", this.cartao.getNumero());
		body.put("cvv", this.cartao.getCvv());
		body.put("estabelecimento", "Teste");
		body.put("senha", senha);
		body.put("validade",  this.cartao.getValidade().format(DateTimeFormatter.ofPattern("MM/yy")));
		body.put("valor", 10);

		return OBJECT_MAPPER.writeValueAsString(body);
	}

}
