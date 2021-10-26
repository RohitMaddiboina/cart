package com.ecommerce.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cart.client.UserClient;
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
public class CartController implements ICartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private JwtUtil jwt;
	
	@Autowired
	private UserClient userClient;
	
	public static final String  TOKEN_STRING  = "Authorization";

	@Override
	public ResponseEntity<Cart> addToCart(@RequestHeader(TOKEN_STRING) String token,@PathVariable("itemId") int itemId) {
		 if(token!=null && token.startsWith("Bearer")){
			 if(userClient.validateToken(token.substring(7)).getStatusCodeValue()==200) {
				 
				 String email = jwt.extractUsername(token.substring(7));
				
				 return ResponseEntity.ok(cartService.addToCart(email, itemId));
			 }
			 
		 }
		 
			 return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
		 
	}
	
	@Override
	public  ResponseEntity<Cart> getCartByEmailAndItemId(@RequestHeader(TOKEN_STRING) String token,@PathVariable int itemId) {
		 if(token!=null && token.startsWith("Bearer")){
			 if(userClient.validateToken(token.substring(7)).getStatusCodeValue()==200) {
				 
				 String email = jwt.extractUsername(token.substring(7));
				
				 return ResponseEntity.ok(cartService.getCartByUseridAndItemid(email, itemId));
			 }
		 }
		
		 return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
		 
	}
	
	@Override
	public ResponseEntity<List<Cart>> getCartByEmail(@RequestHeader(TOKEN_STRING) String token) {
		 if(token!=null && token.startsWith("Bearer")){
			 if(userClient.validateToken(token.substring(7)).getStatusCodeValue()==200) {
	            String email = jwt.extractUsername(token.substring(7));
	            return  ResponseEntity.ok(cartService.getCartByUserId(email));
			 }
		 }
		 
		 return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
		 
	}
	
	@Override
	public  ResponseEntity<Cart>  removeFromCart(@RequestHeader(TOKEN_STRING) String token ,@PathVariable int itemId) {
		 if(token!=null && token.startsWith("Bearer")){
			 if(userClient.validateToken(token.substring(7)).getStatusCodeValue()==200) {
	            String email = jwt.extractUsername(token.substring(7));
	          
	           
	            return  ResponseEntity.ok(cartService.getCartByUseridAndItemid(email, itemId));
			 }
		 }
		 
		 return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
		 
	}
	
	@Override
	public  ResponseEntity<Cart>  removeOneItemFromCart(@RequestHeader(TOKEN_STRING) String token,@PathVariable int itemId) {
		 if(token!=null && token.startsWith("Bearer")){
			 if(userClient.validateToken(token.substring(7)).getStatusCodeValue()==200) { 
	            String email = jwt.extractUsername(token.substring(7));
	       
	           
	            return ResponseEntity.ok(cartService.removeOneItemFromCart(email, itemId));
			 }
		 }
		 
		 return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
		 
	}
	@Override
	public  ResponseEntity<Integer>  getCartCount(@RequestHeader(TOKEN_STRING) String token) {
		 if(token!=null && token.startsWith("Bearer")){
			 if(userClient.validateToken(token.substring(7)).getStatusCodeValue()==200) { 
				 
				 String email = jwt.extractUsername(token.substring(7));
				 return ResponseEntity.ok(cartService.getCartCount(email));
			 }
		 }
		 return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
	} 
}
