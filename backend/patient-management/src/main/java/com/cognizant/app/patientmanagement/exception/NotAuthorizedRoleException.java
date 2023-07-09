package com.cognizant.app.patientmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Unauthorized Role")
public class NotAuthorizedRoleException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotAuthorizedRoleException(String message) {
		super(message);
		
	}

}
