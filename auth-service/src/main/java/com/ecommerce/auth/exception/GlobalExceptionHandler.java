package com.ecommerce.auth.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(
	        MethodArgumentNotValidException ex) {

	    return ResponseEntity
	            .badRequest()
	            .body("Validation failed");
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

	    return ResponseEntity
	            .badRequest()
	            .body(ex.getMessage());
	}

}
