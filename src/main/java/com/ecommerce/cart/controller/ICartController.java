package com.ecommerce.cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.cart.model.Cart;

public interface ICartController {

	@PostMapping("/{itemId}")
	ResponseEntity<Cart> addToCart(String token, int itemId);

	@GetMapping("")
	ResponseEntity<List<Cart>> getCartByEmail(String token);

	@DeleteMapping("/{itemId}")
	ResponseEntity<Cart> removeFromCart(String token, int itemId);
	
	@DeleteMapping("/{itemId}/-")
	ResponseEntity<Cart> removeOneItemFromCart(String token, int itemId);

	@GetMapping("/count")
	ResponseEntity<Integer> getCartCount(String token);

}