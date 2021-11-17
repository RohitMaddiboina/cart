package com.ecommerce.cart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;
import io.jsonwebtoken.ExpiredJwtException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private final String s;
	private final String t;

	public GlobalExceptionHandler(@Value("${string.exceptionValue}")String s,@Value("${string.tokenValue}")String t) {
	super();
	this.s = s;
	this.t=t;
	}
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<String> handleExpiredJwtToken(ExpiredJwtException ex) {
		return new ResponseEntity<>(t, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<String> handleFiegnException(FeignException ex) {
		return new ResponseEntity<>(t, HttpStatus.UNAUTHORIZED);
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add(s);
		return new ResponseEntity<Object>(details, status);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		return new ResponseEntity<Object>(details, HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		return new ResponseEntity<Object>(details, status);
	}

}
