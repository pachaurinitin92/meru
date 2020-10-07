package com.meru.carts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import com.meru.carts.entity.Cart;
import com.meru.carts.service.CartsService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CartsController.class)
@TestMethodOrder(OrderAnnotation.class)
public class CartsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CartsService cartsService;

	@Test
	@Order(1)
	void test_addItemToCart() throws Exception {
		Cart dummyCart = new Cart(1L, 1L, "Iphone11", 105000.0, 1);
		Cart cart = new Cart(1L, 1L, "Iphone11", 105000.0, 1);

		Mockito.when(cartsService.addToCart(Mockito.any(Cart.class))).thenReturn(dummyCart);

		RequestBuilder request = MockMvcRequestBuilders.post("/carts").accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(cart)).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();

		System.out.println("Response:" + response.getHeader(HttpHeaders.LOCATION));

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost/carts/1", response.getHeader(HttpHeaders.LOCATION));
	}

	@SuppressWarnings("serial")
	@Test
	@Order(2)
	void test_getAllCartByCustomer() throws Exception {
		Long customerId = 1L;
		List<Cart> dummyList = new ArrayList<Cart>() {
			{
				add(new Cart(1L, 1L, "Iphone11", 105000.0, 1));
				add(new Cart(2L, 1L, "Iphone10", 75000.0, 1));
			}
		};

		Mockito.when(cartsService.getAllCartByCustomer(Mockito.anyLong())).thenReturn(dummyList);
		RequestBuilder request = MockMvcRequestBuilders.get("/carts/{customerId}", customerId)
				.accept(MediaType.APPLICATION_JSON);
		String expectedResult = "[{id:1, customerId:1, productName:Iphone11, price:105000.0, quantity:1},"
				+ "{id:2, customerId:1, productName:Iphone10, price:75000.0, quantity:1}]";
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(expectedResult)).andReturn();
	}

	@Test
	@Order(3)
	void test_updateCart() throws Exception {
		Cart dummyCart = new Cart(1L, 1L, "Iphone11", 105000.0, 2);
		Cart updatedCart = new Cart(2);

		Mockito.when(cartsService.updateCart(Mockito.anyLong(), Mockito.any(Cart.class))).thenReturn(dummyCart);
		RequestBuilder request = MockMvcRequestBuilders.put("/carts/{id}/cart", 1L).accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(updatedCart)).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();

		System.out.println("Response:" + response.getContentAsString());

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@SuppressWarnings("serial")
	@Test
	@Order(4)
	void test_deletCart() throws Exception {
		Cart cart = new Cart(3L, 1L, "Samsung S10", 64000.0, 1);
		List<Cart> dummyList = new ArrayList<Cart>() {
			{
				add(new Cart(1L, 1L, "Iphone11", 105000.0, 1));
				add(new Cart(2L, 1L, "Iphone10", 75000.0, 1));
			}
		};

		Mockito.when(cartsService.deleteCart(Mockito.any(Cart.class))).thenReturn(dummyList);
		RequestBuilder request = MockMvcRequestBuilders.delete("/carts", cart).accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(cart)).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();

		System.out.println("Response:" + response.getContentAsString());

		assertEquals(HttpStatus.OK.value(), response.getStatus());
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
