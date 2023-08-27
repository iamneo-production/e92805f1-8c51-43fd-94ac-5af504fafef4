package com.user.management.hackthon.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.management.hackthon.exception.UserNotFoundException;
import com.user.management.hackthon.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Sreeram
 * 
 * This handler handle the exception and send the respective exception when it occurs
 *
 */
@RestControllerAdvice
public class UserExceptionHandler {

	/**
	 * @param request
	 * @param e
	 * @return
	 */
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> HandleInputRequest(HttpServletRequest request, MissingServletRequestParameterException e) {
		ErrorResponse error = new ErrorResponse("BAD_REQUEST_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.BAD_REQUEST.value()));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleGenericNotFoundException(UserNotFoundException e) {
		ErrorResponse error = new ErrorResponse("FOUND_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.NOT_FOUND.value()));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
}
