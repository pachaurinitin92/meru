package com.meru.product.catalogue.dao;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.meru.product.catalogue.entity.Product;
import com.meru.product.catalogue.exception.ProductNotFoundException;
import com.meru.product.catalogue.repo.ProductRepository;


@Component
public class ProductCatalogueDAOImpl implements ProductCatalogueDAO{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProduct(Long id) {
		Optional<Product> productOptional 	= productRepository.findById(id);
        Product 		  product 			= productOptional.orElseThrow(() -> new ProductNotFoundException("Product with id:"+id+" not available"));
        return product;
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Optional<Product> optionalProduct  	= productRepository.findById(id);
		Product           updatedProduct	= null;
		if (!optionalProduct.isEmpty()) {
			updatedProduct = productRepository.save(product);
		}
		return updatedProduct;
	}

}
