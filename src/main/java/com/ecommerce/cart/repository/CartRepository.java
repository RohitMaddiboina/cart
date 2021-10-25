package com.ecommerce.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.cart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	public Cart findByUser_idAndItem_ItemId(int userId,int itemId);
	
	public int countByUser_idAndItem_ItemId(int userId,int itemId);

	public List<Cart> findByUser_id(int userId);

	public Cart deleteByUser_idAndItem_ItemId(int userId, int itemId);
	
	@Query(value="select SUM(c.quantity) from Cart c where c.user_id=?1 ",nativeQuery=true)
	public int getCartCount(int userId);

	public int countByUser_id(int userId);
}
