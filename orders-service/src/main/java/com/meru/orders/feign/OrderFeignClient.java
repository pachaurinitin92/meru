package com.meru.orders.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meru.orders.entity.Order;

@FeignClient(name = "ORDERS-SERVICE")
public interface OrderFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/orders/{customerId}")
	public List<Order> getOrderByCustomer(@PathVariable Long customerId);
	
	@RequestMapping(method = RequestMethod.GET, value = "/orders/{id}")
	public Order getOrderDetail(@PathVariable Long id);
	
	@RequestMapping(method = RequestMethod.POST, value = "/orders")
	public ResponseEntity<Order> saveOrder(@RequestBody Order order);
	
	@RequestMapping(method = RequestMethod.PUT, value = "/orders/{id}")
	public Order updateOrder(@PathVariable("id") Long orderId, @RequestBody Order order);
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/orders")
	public void cancelOrder(@RequestBody Order order);
}
