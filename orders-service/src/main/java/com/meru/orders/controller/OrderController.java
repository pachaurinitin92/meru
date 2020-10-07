package com.meru.orders.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.meru.orders.common.OrderAlreadyPaidException;
import com.meru.orders.common.OrderNotFoundException;
import com.meru.orders.entity.Order;
import com.meru.orders.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders/{customerId}")
	public List<Order> getOrderByCustomer(@PathVariable Long customerId) {
		List<Order> orders = orderService.getOrdersByCustomer(customerId);
		if (CollectionUtils.isEmpty(orders)) {
			throw new OrderNotFoundException("Order with customer id: "+ customerId + " not found");
		}
		return orders;
	}
	
	@GetMapping("/orders/{id}/order")
	public Order getOrderDetail(@PathVariable Long id) {
		Order order = orderService.getOrderDetails(id);
		if(null == order) {
			throw new OrderNotFoundException("Order with order id: "+ id + " not found");
		}
		return order;
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Order> saveOrder(@RequestBody Order order){
		Order savedOrder = orderService.saveOrder(order);
		
		URI location;
		/*
		http://localhost:10000/order/{id}
		
		*/
		location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedOrder.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/orders/{id}")
	public Order updateOrder(@PathVariable("id") Long orderId, @RequestBody Order order) {
		Order updatedOrder = orderService.updateOrder(orderId, order);
		if(null == updatedOrder) {
			throw new OrderNotFoundException("Order with order id: "+ orderId + " not found");
		}
		return updatedOrder;
	}
	
	@DeleteMapping("/Order")
	public void cancelOrder(@RequestBody Order order) {
		if(order.getIsPaid()) {
			throw new OrderAlreadyPaidException("Order is already paid");
		}
		orderService.deleteOrder(order);
	}
}
