package com.meru.orders.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meru.orders.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

	public List<Order> findByCustomerId(Long customerId);
}
