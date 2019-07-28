package com.bonifacio.wexprepag.api.gateway.http.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bonifacio.wexprepag.api.domain.exception.BusinessException;

@ControllerAdvice
public class WexprepagApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleCartaoInexistenteException(BusinessException exception, WebRequest request) {		

		return	handleExceptionInternal(
				exception,
				new Erro(exception.getCodigo(), exception.getMessage()),
				new HttpHeaders(),
				HttpStatus.BAD_REQUEST,
				request);
	}
}