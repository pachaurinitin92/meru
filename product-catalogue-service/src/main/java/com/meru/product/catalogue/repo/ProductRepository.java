package com.meru.product.catalogue.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meru.product.catalogue.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	
}
