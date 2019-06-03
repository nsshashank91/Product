package com.wipro.lowes.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.lowes.dao.ProductRepository;
import com.wipro.lowes.entity.Product;




@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	 
	public void createproduct(Product product) {
		productRepository.save(product) ;
	}

	@Override
	public Product getProduct(int productId) {
		return productRepository.findById(productId).get();
	}

	@Override
	public void updateProduct( int productId, Product product) {
		productRepository.UpdateProductByName( productId, product.getProductName());		
	}

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Boolean isContain(int productId) {
		List<Product> list = productRepository.findAll();
		for (Product product : list) {
			if(product.getProductId() == productId)
				return true;
		}
		return true;
	}




}
