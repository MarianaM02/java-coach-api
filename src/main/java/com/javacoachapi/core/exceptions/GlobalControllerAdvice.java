package com.javacoachapi.core.exceptions;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{
	private static final Logger LOGGER=LoggerFactory.getLogger(GlobalControllerAdvice.class);

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Error> handleDataNotFoundException(DataNotFoundException ex){
		Error error = new Error(HttpStatus.NOT_FOUND, ex.getMessage());
		LOGGER.error(error.toString());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Error> handleIllegalArgumentException(IllegalArgumentException ex){
		Error error = new Error(HttpStatus.BAD_REQUEST, ex.getMessage());
		LOGGER.error(error.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Error error = new Error(status, ex.getMessage());
		LOGGER.error(error.toString());
		return ResponseEntity.status(status).headers(headers).body(error);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<Error> handleIOException(IOException ex){
		Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		LOGGER.error(error.toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
	@ExceptionHandler(MailException.class)
	public ResponseEntity<Error> handleMailException(MailException ex){
		Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		LOGGER.error(error.toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
}
