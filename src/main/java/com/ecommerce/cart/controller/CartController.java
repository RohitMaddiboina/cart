package com.ecommerce.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cart.client.UserClient;
import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.service.CartService;
import com.ecommerce.cart.util.JwtUtil;


@RestController
public class CartController implements ICartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private JwtUtil jwt;

	@Autowired
	private UserClient userClient;

	public static final String TOKEN_STRING = "Authorization";
	public static final String BEARER = "Bearer";

	@Override
	public ResponseEntity<Cart> addToCart(@RequestHeader(TOKEN_STRING) String token,
			@PathVariable("itemId") int itemId) {

		if (token != null && token.startsWith(BEARER)
				&& userClient.validateToken(token.substring(7)).getStatusCodeValue() == 200) {

			String email = jwt.extractUsername(token.substring(7));

			return ResponseEntity.ok(cartService.addToCart(email, itemId));

		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);

	}

	@Override
	public ResponseEntity<List<Cart>> getCartByEmail(@RequestHeader(TOKEN_STRING) String token) {
		if (token != null && token.startsWith(BEARER)
				&& userClient.validateToken(token.substring(7)).getStatusCodeValue() == 200) {

			String email = jwt.extractUsername(token.substring(7));
			return ResponseEntity.ok(cartService.getCartByUserId(email));

		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);

	}

	@Override
	public ResponseEntity<Cart> removeFromCart(@RequestHeader(TOKEN_STRING) String token, @PathVariable int itemId) {
		if (token != null && token.startsWith(BEARER)
				&& userClient.validateToken(token.substring(7)).getStatusCodeValue() == 200) {

			String email = jwt.extractUsername(token.substring(7));

			return ResponseEntity.ok(cartService.removeFromCart(email, itemId));
		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);

	}

	@Override
	public ResponseEntity<Cart> removeOneItemFromCart(@RequestHeader(TOKEN_STRING) String token,
			@PathVariable int itemId) {
		if (token != null && token.startsWith(BEARER)
				&& userClient.validateToken(token.substring(7)).getStatusCodeValue() == 200) {

			String email = jwt.extractUsername(token.substring(7));

			return ResponseEntity.ok(cartService.removeOneItemFromCart(email, itemId));
		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);

	}

	@Override
	public ResponseEntity<Integer> getCartCount(@RequestHeader(TOKEN_STRING) String token) {
		if (token != null && token.startsWith(BEARER)
				&& userClient.validateToken(token.substring(7)).getStatusCodeValue() == 200) {

			String email = jwt.extractUsername(token.substring(7));
			return ResponseEntity.ok(cartService.getCartCount(email));
		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
}
