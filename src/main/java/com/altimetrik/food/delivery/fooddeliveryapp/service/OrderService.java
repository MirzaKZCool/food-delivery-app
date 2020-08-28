package com.altimetrik.food.delivery.fooddeliveryapp.service;

import java.util.List;

import com.altimetrik.food.delivery.fooddeliveryapp.dto.OrderDto;


public interface OrderService {

	OrderDto createOrder(OrderDto order);
	OrderDto getOrderById(String orderId);
	OrderDto updateOrderDetails(String orderId, OrderDto order);
	void deleteOrder(String orderId);
	List<OrderDto> getOrders();
}
