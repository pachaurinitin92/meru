package com.meru.carts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meru.carts.entity.Cart;

@Repository
public interface CartsRepository extends CrudRepository<Cart, Long>{

	public List<Cart> findByCustomerId(Long customerId);
}
