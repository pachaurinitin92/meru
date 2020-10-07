package com.meru.orders.controller;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meru.orders.entity.Item;
import com.meru.orders.entity.Order;
import com.meru.orders.service.OrderService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@TestMethodOrder(OrderAnnotation.class)
public class OrdersControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderService orderService;
	
	@SuppressWarnings("serial")
	@Test
	@org.junit.jupiter.api.Order(1)
	void test_saveOrder() throws Exception {
		Order order = new Order(1L, new Date(), 65000, 1L, Boolean.TRUE);
		Set<Item> items = new HashSet<Item>() {
			{
				add(new Item(1L, 1L, "Iphone11", 10500, 1));
			}
		};
		order.setItems(items);

		Order dummy = new Order(1L, new Date(), 65000, 1L, Boolean.TRUE);
		dummy.setItems(items);
		Mockito.when(orderService.saveOrder(Mockito.any(Order.class))).thenReturn(order);

		RequestBuilder request = MockMvcRequestBuilders.post("/orders").accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(dummy)).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();

		System.out.println("Response:" + response.getHeader(HttpHeaders.LOCATION));

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost/orders/1", response.getHeader(HttpHeaders.LOCATION));
	}
	
	@SuppressWarnings("serial")
	@Test
	@org.junit.jupiter.api.Order(2)
	void test_getOrderDetail() throws Exception {
		Long orderId = 1L;
		Order order = new Order(orderId, new Date(), 65000, 1L, Boolean.FALSE);
		Set<Item> items = new HashSet<Item>() {
			{
				add(new Item(1L, 1L, "Iphone11", 65000, 1));
			}
		};
		order.setItems(items);

		Mockito.when(orderService.getOrderDetails(Mockito.anyLong())).thenReturn(order);
		RequestBuilder request = MockMvcRequestBuilders.get("/orders/{id}/order", orderId).accept(MediaType.APPLICATION_JSON);

		String expectedResult = "{id:1, orderDate:'28-09-2020 11:05:00', amount:65000, customerId:1, isPaid:false,"
				+ "items:[{id:1, orderId:1, itemName:'Iphone11', quantity:1, unitPrice:65000}]}";

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(expectedResult)).andReturn();
	}
	
	@SuppressWarnings("serial")
	@Test
	@org.junit.jupiter.api.Order(3)
	void test_getOrderByCustomer() throws Exception {
		Long customerId = 1L;
		Order order = new Order(1L, new Date(), 65000, customerId, Boolean.TRUE);
		Set<Item> items = new HashSet<Item>() {
			{
				add(new Item(1L, 1L, "Iphone11", 1, 105000));
			}
		};
		order.setItems(items);
		List<Order> orders = new ArrayList<Order>() {
			{
				add(order);
			}
		};

		Mockito.when(orderService.getOrdersByCustomer(Mockito.anyLong())).thenReturn(orders);
		RequestBuilder request = MockMvcRequestBuilders.get("/orders/{customerId}", customerId)
				.accept(MediaType.APPLICATION_JSON);
		String expectedResult = "[{id:1, orderDate:'28-09-2020 11:05:00', amount:65000, customerId:1,"
				+ " isPaid:false, items :[{id:1, orderId:1, itemName:'Iphone11', quantity:1, unitPrice:105000}]}]";
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(expectedResult)).andReturn();
	}
	
	@Test
	@org.junit.jupiter.api.Order(4)
	void test_deleteOrder() {
		
	}
	
	private static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
}
