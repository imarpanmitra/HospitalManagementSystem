package com.cognizant.app.patientmanagement.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AuthorizationKeyNotValidException.class)
	public final ResponseEntity<Object> handlePostNotFoundExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex, WebRequest request) {
		final String message = "Invalid path variable. Expected number!!";
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), message, request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public final ResponseEntity<Object> handleResourceAlreadyExistsException(
			ResourceAlreadyExistsException ex, WebRequest request) {
		final String message = "Resource already exists";
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), message, request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				"Error: " + ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}