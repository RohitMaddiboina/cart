package com.ecommerce.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.cart.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


	
	public User findByEmail(String email);

}
