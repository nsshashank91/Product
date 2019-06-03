package com.wipro.lowes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.lowes.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Modifying
	@Query("Update Product p Set p.productName =:productname Where p.productId =:productid")
	int UpdateProductByName(@Param("productid") int productid , @Param("productname") String productname);
}
