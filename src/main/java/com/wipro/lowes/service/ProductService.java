package com.wipro.lowes.service;

import java.util.List;

import com.wipro.lowes.entity.Product;

public interface ProductService {

	public void createproduct(Product product);

	public Product getProduct(int productId);

	public void updateProduct(int productId, Product product);

	public List<Product> getAllProduct();

	public Boolean isContain(int productId);
}
