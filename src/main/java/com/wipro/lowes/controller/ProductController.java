package com.wipro.lowes.controller;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.lowes.entity.Product;
import com.wipro.lowes.service.ProductService;



@RestController
@RequestMapping("/product/api")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping( value = "/getAllProducts", method = RequestMethod.GET)
	public List<Product> getAllProduct() {
		System.out.println("get all products");
		return productService.getAllProduct();
	}

	@RequestMapping( value = "/getProduct/{id}", method = RequestMethod.GET)
	public Product getProduct( @PathVariable("id") int productId) {
		Boolean present = productService.isContain(productId);
		if(!present )
			throw new ProductNotfoundException();
		
		Product product = productService.getProduct( productId )	;	
		return product;
	}
	//@RequestMapping( value = "/create", method = RequestMethod.POST)
	@KafkaListener(topics = "product", groupId = "group_id")
	public void createProduct(String product) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Product prod = mapper.readValue(product, Product.class);
		System.out.println(product);
		productService.createproduct(prod);
		return ;
	}

	@RequestMapping( value = "/update/{id}", method = RequestMethod.PUT)
	public void updateProduct( @PathVariable("id") int productId, @RequestBody Product product ) {
		Boolean present = productService.isContain(productId);
		if( !present )
			throw new ProductNotfoundException();
		
		productService.updateProduct( productId , product)	;
		return ;
	}
}
