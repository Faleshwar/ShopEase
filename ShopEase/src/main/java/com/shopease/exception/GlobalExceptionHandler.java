package com.shopease.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerValidationException(MethodArgumentNotValidException exception){
		Map<String, String> err = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach(e->{
			String fieldName = ((FieldError)e).getField();
			String errorMessage = e.getDefaultMessage();
			err.put(fieldName, errorMessage);
		});
		
		return ResponseEntity.badRequest().body(err);
	}

}
