package com.bonifacio.wexprepag.api.gateway.http.error;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bonifacio.wexprepag.api.domain.exception.BusinessException;

@ControllerAdvice
public class WexprepagApiResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleCartaoInexistenteException(BusinessException exception) {		
		return	ResponseEntity.unprocessableEntity().body(new Erro(exception.getCodigo(), exception.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {		

		List<Erro> erros = obterMensagens(exception);
		return	ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest request) {		
		return	ResponseEntity.badRequest().body(new Erro(ExceptionUtils.getRootCauseMessage(exception)));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleDefaultException(Exception exception, WebRequest request) {		
		return	ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Erro("ocorreu um erro inesperado"));
	}
	
	private List<Erro> obterMensagens(MethodArgumentNotValidException exception) {
		
		return exception.getBindingResult().getAllErrors().stream().map(erro -> {
			FieldError fieldError = (FieldError) erro;
			return new Erro(String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()));
		}).collect(Collectors.toList());
	}
}
