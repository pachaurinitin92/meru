package com.meru.carts.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meru.carts.entity.Cart;

@FeignClient(name = "CARTS-SERVICE")
public interface CartFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/carts/{customerId}")
	public List<Cart> getAllCartByCustomer(@PathVariable Long customerId);
	
	@RequestMapping(method = RequestMethod.POST, value = "/carts")
	public ResponseEntity<Cart> addItemToCart(@RequestBody Cart cart);
	
	@RequestMapping(method = RequestMethod.PUT, value = "/carts/{id}/cart")
	public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart);
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/carts")
	public List<Cart> deletCart(@RequestBody Cart cart);
}
