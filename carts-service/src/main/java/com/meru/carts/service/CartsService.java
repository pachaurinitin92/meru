package com.meru.carts.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.meru.carts.dao.CartsDAO;
import com.meru.carts.entity.Cart;

@Service
@Transactional
public class CartsService {

	private CartsDAO cartsDAO;

	public Cart addToCart(Cart cart) {
		Cart savedCart = cartsDAO.addToCart(cart);
		return savedCart;
	}

	public List<Cart> getAllCartByCustomer(Long customerId) {
		List<Cart> carts = cartsDAO.getAllCartByCustomer(customerId);
		return carts;
	}

	public Cart updateCart(Long cartId, Cart cart) {
		Cart value = cartsDAO.updateCart(cartId, cart);
		return value;
	}

	public List<Cart> deleteCart(Cart cart) {
		List<Cart> values = cartsDAO.deleteCart(cart);
		return values;
	}
}
