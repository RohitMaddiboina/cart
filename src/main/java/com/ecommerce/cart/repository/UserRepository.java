package com.ecommerce.cart.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.cart.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//	@Query(value="from User where email=:email")
//	public User findByEmail(@Param("email") String email);
	
	public User findByEmail(String email);

}
