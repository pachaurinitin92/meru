package com.meru.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.meru.orders.entity.Item;
import com.meru.orders.entity.Order;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MeruApplicationOrdersServiceTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@SuppressWarnings("serial")
	@Test
	@org.junit.jupiter.api.Order(1)
	public void _saveOrder() {
		Order order = new Order(1L, new Date(), 65000, 1L, Boolean.TRUE);
		Set<Item> items = new HashSet<Item>() {
			{
				add(new Item(1L, 1L, "Iphone11", 10500, 1));
			}
		};
		order.setItems(items);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Order> request = new HttpEntity<>(order, headers);
		ResponseEntity<Order> result = this.restTemplate.postForEntity("/orders", request, Order.class);
		assertEquals(201, result.getStatusCodeValue());
	}
	
	@Test
	@org.junit.jupiter.api.Order(2)
	public void _getOrderByCustomer() {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("customerId", "1");
		Order order = this.restTemplate.getForObject("/orders/{customerId}", Order.class, params);
		System.out.println("get: "+ order.toString());
	}
	
	@Test
	@org.junit.jupiter.api.Order(3)
	public void _getOrderDetail() {
		
	}
}
