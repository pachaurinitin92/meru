package com.meru.carts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.meru.carts.entity.Cart;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MeruApplicationCartsServiceTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void _addItemToCart() {
		Cart cart = new Cart(1L, 1L, "Iphone11", 105000.0, 1);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<Cart> request = new HttpEntity<>(cart, headers);
		ResponseEntity<Cart> result = this.restTemplate.postForEntity("/carts", request, Cart.class);
		assertEquals(201, result.getStatusCodeValue());
	}

	@Test
	@Order(2)
	public void _addItemToCart2() {
		Cart cart = new Cart(2L, 1L, "Iphone10", 750000.0, 1);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<Cart> request = new HttpEntity<>(cart, headers);
		ResponseEntity<Cart> result = this.restTemplate.postForEntity("/carts", request, Cart.class);
		assertEquals(201, result.getStatusCodeValue());
	}

	@Test
	@Order(3)
	public void _getAllCartByCustomer() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("customerId", "1");
		Cart cart = this.restTemplate.getForObject("/carts/{customerId}", Cart.class, params);
		System.out.println("get: " + cart.toString());
	}

	@Test
	@Order(4)
	public void _updateCart() {
		Cart cart = new Cart();
		cart.setId(1L);
		cart.setQuantity(2);
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<Cart> request = new HttpEntity<>(cart, headers);

		ResponseEntity<String> result = this.restTemplate.exchange("/carts/{id}", HttpMethod.PUT, request, String.class,
				params);
		assertEquals(200, result.getStatusCodeValue());
	}
}
