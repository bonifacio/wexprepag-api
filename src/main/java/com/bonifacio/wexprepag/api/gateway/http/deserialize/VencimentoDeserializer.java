package com.bonifacio.wexprepag.api.gateway.http.deserialize;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class VencimentoDeserializer extends StdDeserializer<LocalDate> {
	
	private static final long serialVersionUID = 7622097211573674508L;
	
	public VencimentoDeserializer() {
		this(null);
	}

	protected VencimentoDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		
		String validade = String.format("01/%s", p.getText());
		try {
			return LocalDate.parse(validade, DateTimeFormatter.ofPattern("dd/MM/yy")).with(TemporalAdjusters.lastDayOfMonth());
		} catch (Exception e) {
			throw new JsonMappingException(p, String.format("a data %s é inválida. formato esperado: MM/yy", p.getText()));
		}
	}

}
