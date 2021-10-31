package com.ecommerce.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration	
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ecommerce"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiDeatils());
	}
	
	private ApiInfo apiDeatils()
	{
		return new ApiInfo(
				"Cart Microservice API",
				"Cart Microservice interacts with Item Microservice and User Microservice, but it gets invoked from Fassico(E-Commerce)Application. Post authorization of request, Cart Microservice allows the following operations: \r\n" + 
				" \r\n" + 
				"•	Add Item to Cart using ItemId from Item Microservice.  \r\n" + 
				"•	Get Cart by Email from User Microservice. \r\n" + 
				"•	Remove Item From Cart with ItemId from Item Microservice. \r\n" + 
				"•	Remove one Item From Cart with ItemId from Item Microservice. \r\n" + 
				"•	Get Cart Items Count. ",
				"1.0",
				"©Copyright Application",
				new springfox.documentation.service.Contact("Fassico", "https://Fassico.com", "Fassico@gmail.com"),
				"API License",
				"https://Fassico.com",
				Collections.emptyList());
		
	}
}
