package com.ecommerce.cart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;
import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<String> handleExpiredJwtToken(ExpiredJwtException ex) {
		return new ResponseEntity<>("Token Expired",HttpStatus.UNAUTHORIZED);
	}
	  @ExceptionHandler(FeignException.class)
	    public ResponseEntity<String> handleFiegnException(FeignException ex) {
	        return new ResponseEntity<>("Token Expired",HttpStatus.UNAUTHORIZED);
	    }
}
