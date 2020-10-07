package com.meru.carts.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meru.carts.entity.Cart;
import com.meru.carts.repository.CartsRepository;

@Component
public class CartsDAOImpl implements CartsDAO{

	@Autowired
	private CartsRepository cartRepo;
	
	@Override
	public Cart addToCart(Cart cart) {
		Cart savedCart = cartRepo.save(cart);
		return savedCart;
	}

	@Override
	public List<Cart> getAllCartByCustomer(Long customerId) {
		List<Cart> carts = cartRepo.findByCustomerId(customerId);
		return carts;
	}

	@Override
	public Cart updateCart(Long cartId, Cart cart) {
		Optional<Cart> optional = cartRepo.findById(cartId);
		Cart value = null;
		if (null != optional) {
			Cart dbData = optional.get();
			dbData.setQuantity(cart.getQuantity());
			value = cartRepo.save(dbData);
		}
		return value;
	}

	@Override
	public List<Cart> deleteCart(Cart cart) {
		Long customerId = cart.getCustomerId();
		cartRepo.delete(cart);
		List<Cart> values = cartRepo.findByCustomerId(customerId);
		return values;
	}
}
