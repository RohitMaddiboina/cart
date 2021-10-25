package com.ecommerce.cart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.model.Item;
import com.ecommerce.cart.model.User;
import com.ecommerce.cart.repository.CartRepository;
import com.ecommerce.cart.repository.ItemRepository;
import com.ecommerce.cart.repository.UserRepository;

@Service
public class CartDaoImpl implements CartDao {

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Override
	public Cart addToCart(Cart cart) {
		return cartRepo.save(cart);
	}
	@Override
	public Cart getCartByUseridAndItemid(int  userId, int itemId) {		
		return cartRepo.findByUser_idAndItem_ItemId(userId, itemId);
	}
	@Override
	public int countByUser_UserIdAndItem_ItemId(int userId, int itemId) {		
		return cartRepo.countByUser_idAndItem_ItemId(userId, itemId);
	}
	@Override
	public User getUserById(int userId) {
		return userRepo.getById(userId);
	}
	@Override
	public Item getItemById(int itemId) {
		return itemRepo.getById(itemId);
	}
	@Override
	public List<Cart> getCartByUserId(int userId) {
		return cartRepo.findByUser_id(userId);
	}
	@Override
	public int getCartCount(int userId) {
		return cartRepo.getCartCount(userId);
	}
	@Override
	public int countByUser(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int countByUserId(int userId) {
		return cartRepo.countByUser_id(userId);
	}
	@Override
	public User getUserIdByEmailFromUser(String email) {		
		User u=userRepo.findByEmail(email);
		return u;
	}
	@Override
	public long countNumberOfRows() {
		return cartRepo.count();
	}
	
	
}
