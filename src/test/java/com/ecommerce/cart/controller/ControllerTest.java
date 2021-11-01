package com.ecommerce.cart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc

@SpringBootTest
class ControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	private static final String key="Authorization";
	private static final String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2hpdHRlamExOThAZ21haWwuY29tIiwiZXhwIjoxNjM1NTc1ODAwLCJpYXQiOjE2MzU0ODk0MDB9.a1fKF_GTvzHGw40dl6ZZihoHuJr2UBoN5VNW-nnqYXc";
	

	
	@Test
	void testaddToCart() throws Exception {
	mvc.perform(get("/cart/count").header(key,"Bearer "+token)).andExpect(jsonPath("$").value(0));
	
	 mvc.perform(post("/cart/1").header(key,"Bearer "+token).contentType("application/json"))
	.andExpect(status().isOk());
	 
	 mvc.perform(post("/cart/3").header(key,"Bearer "+token).contentType("application/json"))
		.andExpect(status().isOk());
	 
	 mvc.perform(post("/cart/4").header(key,"Bearer "+token).contentType("application/json"))
		.andExpect(status().isOk());
	 
	mvc.perform(get("/cart/count").header(key,"Bearer "+token)).andExpect(jsonPath("$").exists());

	}
	@Test
	void testNaddToCart() throws Exception {
	 mvc.perform(post("/cart/5").header(key,token).header(key,"Bearer "+null).contentType("application/json"))
	.andExpect(status().isForbidden());
	}
	
	
//	@Test
//	void getCartByEmailAndItemId() throws Exception {
//
//	 mvc.perform(get("/cart/5").header(key,"Bearer "+token)).andExpect(jsonPath("$").exists());
//
//	 }
	
	@Test
	void testgetCartByEmail() throws Exception {

	 mvc.perform(get("/cart").header(key,"Bearer "+token)).andExpect(jsonPath("$").exists());

	 }
	@Test
	void testNgetCartByEmail() throws Exception {

	 mvc.perform(get("/cart").header(key,token).header(key,"Bearer "+null)).andExpect(status().isForbidden());

	 }
	
	@Test
	void testremoveFromCart() throws Exception {
	mvc.perform(delete("/cart/4").header(key,"Bearer "+token)).andExpect(jsonPath("$").exists());
	mvc.perform(delete("/cart/15").header(key,"Bearer "+token)).andExpect(jsonPath("$").doesNotExist());

	}
	@Test
	void testNremoveFromCart() throws Exception {

	 mvc.perform(delete("/cart/5").header(key,token).header(key,"Bearer "+null)).andExpect(status().isForbidden());

	 }
	
	@Test
	void testremoveOneItemFromCart() throws Exception {
	mvc.perform(delete("/cart/1/-").header(key,"Bearer "+token)).andExpect(jsonPath("$").exists());
	mvc.perform(delete("/cart/15/-").header(key,"Bearer "+token)).andExpect(jsonPath("$").doesNotExist());

	 }
	@Test
	void testNremoveOneItemFromCart() throws Exception {

	 mvc.perform(delete("/cart/1/-").header(key,token).header(key,"Bearer "+null)).andExpect(status().isForbidden());

	 }
	
//	@Test
//	void testgetCartCount() throws Exception {
//	mvc.perform(get("/cart/count").header(key,"Bearer "+token)).andExpect(jsonPath("$").exists());
//	
//
//	}
	@Test
	void testNgetCartCount() throws Exception {

	 mvc.perform(get("/cart/count").header(key,token).header(key,"Bearer "+null)).andExpect(status().isForbidden());

	 }
	
	

}
