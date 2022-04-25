package com.project.OMS.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.OMS.ResourceNotFoundException;
import com.project.OMS.Entity.Item;
import com.project.OMS.Entity.Order;
import com.project.OMS.Repositpry.ItemRepository;
import com.project.OMS.Repositpry.OrderRepository;
import com.project.OMS.Service.OrderService;
/**
 * ServiceImpl class to implement methods from service class.
 * @Autowired - Used to inject the object dependency.

 * @Service - The Annotation is used to mark the class as service provider.@Service annotation is 
   used with classes that provide some business functionalities. Spring context will auto detect 
   these classes when annotation-based configuration and class path scanning is used.
 */

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Integer orderId) {
		Order order = orderRepository.getById(orderId);
		orderRepository.delete(order);

	}
	
	@Override
	public Order getOrderById(Integer orderId) {
		orderRepository.findById(orderId).orElseThrow(() ->
		new ResourceNotFoundException("Order Not Found"));
		return orderRepository.getById(orderId);
	}

	@Override
	public Order addItemsToOrder(Integer orderId, Integer id) {
		Order order = orderRepository.getById(orderId);
		Item item = itemRepository.getById(id);
		item.addOrders(order);
		order.addItems(item);
		return orderRepository.save(order);
	}

	@Override
	public String totalCost(String orderId) {
		Order order = orderRepository.getById(Integer.parseInt(orderId));
		Double cost = 0d;
		for (Item item : order.getItems()) {
			cost = cost + item.getCost();
		}
		return String.valueOf(cost);
	}

	@Override
	public List<Order> deleteOrders(Integer orderId) {
		Order order = orderRepository.getById(orderId);
		List<Item> items = order.getItems();
		if (items == null) {
			orderRepository.delete(order);
		} else {
			for (Item item : items) {
				item.removeOrders(order);
			}
			orderRepository.delete(order);
		}

		return getAllOrders();
	}

}
