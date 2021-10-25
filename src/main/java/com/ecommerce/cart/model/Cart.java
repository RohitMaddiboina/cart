package com.ecommerce.cart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="cart")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	private long cartId;
	@ManyToOne
	private User user;
	@ManyToOne
	private Item item;
	private int quantity;
}
