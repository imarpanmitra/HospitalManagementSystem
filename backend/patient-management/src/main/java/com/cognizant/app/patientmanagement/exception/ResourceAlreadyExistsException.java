package com.cognizant.app.patientmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Resource already exists")
public class ResourceAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyExistsException(String message) {
		super(message);
	}

}
