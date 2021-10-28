package com.ecommerce.cart.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.cart.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {


}
