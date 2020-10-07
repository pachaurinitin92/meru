package com.meru.orders.dao;

import java.util.List;

import com.meru.orders.entity.Order;

public interface OrderDAO {

	public List<Order> getOrdersByCustomer(Long customerId);
	public Order getOrderDetails(Long orderId);
	public Order saveOrder(Order order);
	public void deleteOrder(Order order);
	public Order updateOrder(Long orderId, Order order);
}
