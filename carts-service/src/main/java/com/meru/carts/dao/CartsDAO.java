package com.meru.carts.dao;

import java.util.List;

import com.meru.carts.entity.Cart;

public interface CartsDAO {

	public Cart addToCart(Cart cart);
	public List<Cart> getAllCartByCustomer(Long customerId);
	public Cart updateCart(Long cartId, Cart cart);
	public List<Cart> deleteCart(Cart cart);
}
