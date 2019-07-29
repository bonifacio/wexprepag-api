package com.bonifacio.wexprepag.api.gateway.http;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartaoResourceIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveEmitirUmCartao_quandoOForemPassadosTodosAtributosValidos() throws Exception {

		String content = "{\"nome\": \"João\",\"saldo\":\"100\"}";
		
		mockMvc.perform(post("/cartao").contentType(MediaType.APPLICATION_JSON).content(content))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.numero").exists())
			.andExpect(jsonPath("$.nome").exists())
			.andExpect(jsonPath("$.cvv").exists())
			.andExpect(jsonPath("$.saldo").exists())
			.andExpect(jsonPath("$.senha").exists())
			.andExpect(jsonPath("$.validade").exists())
			.andReturn();
	}
	
	@Test
	public void deveRetornarBadRequest_quandoONaoForemPassadosNomeESaldo() throws Exception {

		mockMvc.perform(post("/cartao").contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$[0].mensagem").exists())
			.andReturn();
	}
}
