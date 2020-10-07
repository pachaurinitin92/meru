package com.meru.product.catalogue.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.meru.product.catalogue.entity.Product;
import com.meru.product.catalogue.entity.ProductCategory;
import com.meru.product.catalogue.exception.ProductCategoryNotFoundException;
import com.meru.product.catalogue.repo.ProductCategoryRepository;


@Component
public class ProductCategoryDAOImpl implements ProductCategoryDAO{
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Override
	public List<Product> getProductByCategory(String productCategory) {
		List<Optional<ProductCategory>> productCategoryOptional	= productCategoryRepository.findByProductCategoryName(productCategory);
		if(!CollectionUtils.isEmpty(productCategoryOptional)) {
			List<Product> productList = new ArrayList<Product>();
			for(Optional<ProductCategory> prodCatOptnl : productCategoryOptional) {
				ProductCategory prodCategory = prodCatOptnl.get();
				productList.addAll(prodCategory.getProducts());
			}
			return productList;			
		}else {
			throw new ProductCategoryNotFoundException("Product with category:"+productCategory+" not available");
		}

	}
	
	

}
