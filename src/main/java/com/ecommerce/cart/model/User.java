package com.ecommerce.cart.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="user")
@Entity
@Getter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String firstName;
    private  String lastName;
    @Column(unique=true)
    private String email;
    private String gender;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;
    private String password;
    private String phone;
    private String houseNo;
    private String street;
    private String city;
    private String district;
    private String state;
    private int pincode;
    private String landmark;
    private String security_questions;
    private String security_answer;
    
    public User(String firstName, String lastName, String email, String gender, Date dob, String password,
            String phone, String houseNo, String street, String city, String district, String state, int pincode,
            String landmark, String security_questions, String security_answer) {
    super();
}
    
	@Getter(value = AccessLevel.NONE)
	@OneToMany(mappedBy="user")
	private List<Cart> carts; 
	
}
