package com.springboot.mongo.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.mongo.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
    
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex,
			HttpServletRequest request) {
    	HttpStatus status = HttpStatus.NOT_FOUND;
    	return ResponseEntity.status(status)
    			.body(new StandardError(Instant.now(), status.value(), "Obejeto não encontrado", ex.getMessage(), request.getRequestURI()));
    }
	
	/*@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException ex, HttpServletRequest request) {
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	return ResponseEntity.status(status)
    			.body(new StandardError(Instant.now(), status.value(), "Database error", ex.getMessage(), request.getRequestURI()));
    }*/
}
