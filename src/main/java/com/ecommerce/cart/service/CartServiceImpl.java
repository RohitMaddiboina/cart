package com.ecommerce.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.cart.dao.CartDao;
import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.model.User;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDao cartDao;
	@Override
	public Cart addToCart(String email, int itemId) {
		Cart c;
		int userId=GetUserIdByEmail(email);
		if(cartDao.countByUser_UserIdAndItem_ItemId(userId, itemId)>0) {
			c=cartDao.getCartByUseridAndItemid(userId, itemId);
			c.setQuantity(c.getQuantity()+1);
			return cartDao.addToCart(c);
		}else {
//			if(cartDao.countNumberOfRows()==0) {
//				return cartDao.addToCart(new Cart(cartDao.getUserById(userId),cartDao.getItemById(itemId),1));
//			}
			return cartDao.addToCart(new Cart(cartDao.countNumberOfRows()+1,cartDao.getUserById(userId),cartDao.getItemById(itemId),1));
		}
	}
	@Override
	public int countByUser_UserIdAndItem_ItemId(String email, int itemId) {
		return 0;
	}
	@Override
	public Cart getCartByUseridAndItemid(String email, int itemId) {
		return null;
	}
	@Override
	public List<Cart> getCartByUserId(String email) {
		int userId=GetUserIdByEmail(email);
		return cartDao.getCartByUserId(userId);
	}
	@Override
	public Cart removeFromCart(String email, int itemId) {
		Cart c=null;
		int userId=GetUserIdByEmail(email);
		if(cartDao.countByUser_UserIdAndItem_ItemId(userId, itemId)>0) {
			c=cartDao.getCartByUseridAndItemid(userId, itemId);
			c.setQuantity(0);
			return cartDao.addToCart(c);
		}
		return c;
	}
	@Override
	public Cart removeOneItemFromCart(String email, int itemId) {
		int userId=GetUserIdByEmail(email);
		Cart c=null;
		if(cartDao.countByUser_UserIdAndItem_ItemId(userId, itemId)>0) {
			c=cartDao.getCartByUseridAndItemid(userId, itemId);
			c.setQuantity(c.getQuantity()-1);
			return cartDao.addToCart(c);
		}
		return c;
	}
	@Override
	public int getCartCount(String email) {
		int userId=GetUserIdByEmail(email);
		if(cartDao.countByUserId(userId)>0) {
			return cartDao.getCartCount(userId);
		}else {
			return 0;
		}
	}
	private int GetUserIdByEmail(String email) {
		return cartDao.getUserIdByEmailFromUser(email).getId();
	}

	
}
