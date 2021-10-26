package com.ecommerce.cart.dao;

import java.util.List;

import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.model.Item;
import com.ecommerce.cart.model.User;

public interface CartDao {
	Cart addToCart(Cart cart);
	
	Cart getCartByUseridAndItemid(int userId,int itemId);
		
	int countByUserUserIdAndItemItemId(int userId,int itemId);

	User getUserById(int userId);
	
	Item getItemById(int itemId) ;

	List<Cart> getCartByUserId(int userId);

	int getCartCount(int userId);

	int countByUser(int userId);

	int countByUserId(int userId);

	User getUserIdByEmailFromUser(String email);
	
	long countNumberOfRows();
}
