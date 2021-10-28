package com.ecommerce.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.cart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	public static final String CARTCOUNTQUERY = "select SUM(c.quantity) from Cart c where c.user_id=?1";
	
	public Cart findByUserIdAndItemItemId(int userId,int itemId);
	
	public int countByUserIdAndItemItemId(int userId,int itemId);

	public List<Cart>  findByUserId(int userId);

	public Cart deleteByUserIdAndItemItemId(int userId, int itemId);
	
	@Query(value=CARTCOUNTQUERY,nativeQuery=true)
	public int getCartCount(int userId);

	public int countByUserId(int userId);
}
