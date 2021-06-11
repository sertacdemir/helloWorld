package com.sdemir.demo.car.charging.session.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class is central exception handler class
 * 
 * @author sertacdemir
 * @since 1.0
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());
		
		List<String> errors = ex.getBindingResult()
				.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		
		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);
		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFound(ResourceNotFoundException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND);
		body.put("error", ex.getMessage());


        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> customException(CustomException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST);
		body.put("error", ex.getMessage());


        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
	
}
