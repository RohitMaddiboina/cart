package com.ecommerce.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "UserClient",url = "http://localhost:8080/fasscio")
public interface UserClient {
	
	 public static final String   TOKEN_STRING  = "Authorization";
	 
	 @PostMapping("/validate")
	   public ResponseEntity<Object> validateToken(@RequestHeader(TOKEN_STRING) String token);
	 
	
}
