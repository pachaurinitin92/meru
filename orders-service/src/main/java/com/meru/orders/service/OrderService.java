package com.meru.orders.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meru.orders.dao.OrderDAO;
import com.meru.orders.entity.Order;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderDAO orderDao;
	
	public List<Order> getOrdersByCustomer(Long customerId) {
		List<Order> orders = orderDao.getOrdersByCustomer(customerId);
		return orders;
	}

	public Order getOrderDetails(Long orderId) {
		Order order = orderDao.getOrderDetails(orderId);
		return order;
	}

	public Order saveOrder(Order order) {
		Order savedOrder = orderDao.saveOrder(order);
		return savedOrder;
	}

	public void deleteOrder(Order order) {
		orderDao.deleteOrder(order);
	}

	public Order updateOrder(Long orderId, Order order) {
		Order saved = orderDao.updateOrder(orderId, order);
		return saved;
	}
}
