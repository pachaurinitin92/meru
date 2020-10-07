package com.meru.orders.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.meru.orders.entity.Item;
import com.meru.orders.entity.Order;
import com.meru.orders.repository.OrderRepository;

@Component
public class OrderDAOImpl implements OrderDAO{

@Autowired
private OrderRepository orderRepo;
	
	@Override
	public List<Order> getOrdersByCustomer(Long customerId) {
		List<Order> orders = orderRepo.findByCustomerId(customerId);
		return orders;
	}

	@Override
	public Order getOrderDetails(Long orderId) {
		Order order = null;
		Optional<Order> option = orderRepo.findById(orderId);
		if(null != option) {
			order = option.get();
		}
		return order;
	}

	@Override
	public Order saveOrder(Order order) {
		Order savedOrder = orderRepo.save(order);
		return savedOrder;
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepo.delete(order);
	}

	@Override
	public Order updateOrder(Long orderId, Order order) {
		Optional<Order> optional = orderRepo.findById(orderId);
		Order fetchedOrder = optional.get();
		if (!CollectionUtils.isEmpty(order.getItems())) {
			for (Item item : order.getItems()) {
				for (Item fetchItem : fetchedOrder.getItems()) {
					if (item.getId().equals(fetchItem.getId()))
						fetchItem.setQuantity(item.getQuantity());
				}
			}
		}
		Order saved = orderRepo.save(fetchedOrder);
		return saved;
	}
}
