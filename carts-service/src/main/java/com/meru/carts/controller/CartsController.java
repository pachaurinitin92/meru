package com.meru.carts.controller;

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

import com.meru.carts.common.NotFoundException;
import com.meru.carts.entity.Cart;
import com.meru.carts.service.CartsService;

@RestController
public class CartsController {

	@Autowired
	private CartsService cartService;
	
	@GetMapping("/carts/{customerId}")
	public List<Cart> getAllCartByCustomer(@PathVariable Long customerId){
		List<Cart> carts =cartService.getAllCartByCustomer(customerId);
		if(CollectionUtils.isEmpty(carts)) {
			throw new NotFoundException("No cart for available for customer: "+ customerId);
		}
		return carts;
	}
	
	@PostMapping("/carts")
	public ResponseEntity<Cart> addItemToCart(@RequestBody Cart cart) {
		Cart savedValue = cartService.addToCart(cart);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{customerId}").buildAndExpand(savedValue.getCustomerId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/carts/{id}/cart")
	public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart) {
		Cart updatedValue = cartService.updateCart(id, cart);
		if(null == updatedValue) {
			throw new NotFoundException("No cart fount");
		}
		return updatedValue;
	}
	
	@DeleteMapping("/carts")
	public List<Cart> deletCart(@RequestBody Cart cart) {
		List<Cart> carts = cartService.deleteCart(cart);
		return carts;
	}
}
