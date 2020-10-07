package com.meru.product.catalogue.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meru.product.catalogue.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{
	
	List<Optional<ProductCategory>> findByProductCategoryName(String productCategoryName);

}
