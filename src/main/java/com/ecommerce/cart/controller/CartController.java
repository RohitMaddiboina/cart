package com.ecommerce.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cart.dao.CartDao;
import com.ecommerce.cart.dao.CartDaoImpl;
import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.model.Item;
import com.ecommerce.cart.model.User;
import com.ecommerce.cart.repository.ItemRepository;
import com.ecommerce.cart.repository.UserRepository;
import com.ecommerce.cart.service.CartService;
import com.ecommerce.cart.util.JwtUtil;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private JwtUtil jwt;
	
	public static final String  TOKEN_STRING  = "Authorization";

	@PostMapping("/{itemId}")
	public Cart addToCart(@RequestHeader(TOKEN_STRING) String token,@PathVariable("itemId") int itemId) {
		 if(token!=null && token.startsWith("Bearer")){
	       
	            String email = jwt.extractUsername(token.substring(7));
		
	            return cartService.addToCart(email, itemId);
		 }
		 
			 return  null;
		 
	}
	
	@GetMapping("/{itemId}")
	public Cart getCartByEmailAndItemId(@RequestHeader(TOKEN_STRING) String token,@PathVariable int itemId) {
		 if(token!=null && token.startsWith("Bearer")){
		       
	            String email = jwt.extractUsername(token.substring(7));
	            return cartService.getCartByUseridAndItemid(email, itemId);
		 }
		
			 return null;
		 
	}
	
	@GetMapping("")
	public List<Cart> getCartByEmail(@RequestHeader(TOKEN_STRING) String token) {
		 if(token!=null && token.startsWith("Bearer")){
		       
	            String email = jwt.extractUsername(token.substring(7));
	            return cartService.getCartByUserId(email);
		 }
		 
			 
			 return null;
		 
	}
	
	@DeleteMapping("/{itemId}")
	public Cart removeFromCart(@RequestHeader(TOKEN_STRING) String token ,@PathVariable int itemId) {
		 if(token!=null && token.startsWith("Bearer")){
		       
	            String email = jwt.extractUsername(token.substring(7));
	            return cartService.removeFromCart(email, itemId);
		 }
		 
			 return null;
		 
	}
	
	@DeleteMapping("/{itemId}/-")
	public Cart removeOneItemFromCart(@RequestHeader(TOKEN_STRING) String token,@PathVariable int itemId) {
		 if(token!=null && token.startsWith("Bearer")){
		       
	            String email = jwt.extractUsername(token.substring(7));
	            return cartService.removeOneItemFromCart(email, itemId);
		 }
		 
			 return null;
		 
	}
	@GetMapping("/count")
	public int getCartCount(@RequestHeader(TOKEN_STRING) String token) {
		 if(token!=null && token.startsWith("Bearer")){
		       
	            String email = jwt.extractUsername(token.substring(7));
	            return cartService.getCartCount(email);
		 }
		 return 0;
	} 
}
