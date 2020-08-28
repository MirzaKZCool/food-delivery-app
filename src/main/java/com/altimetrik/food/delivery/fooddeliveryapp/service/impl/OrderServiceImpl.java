package com.altimetrik.food.delivery.fooddeliveryapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.food.delivery.fooddeliveryapp.dto.OrderDto;
import com.altimetrik.food.delivery.fooddeliveryapp.dto.Utils;
import com.altimetrik.food.delivery.fooddeliveryapp.entity.OrderEntity;
import com.altimetrik.food.delivery.fooddeliveryapp.repository.OrderRepository;
import com.altimetrik.food.delivery.fooddeliveryapp.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	Utils utils;

	/**
	 * Create order method
	 */
	public OrderDto createOrder(OrderDto order) {
		ModelMapper modelMapper = new ModelMapper();
		OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
		String orderId = utils.generateOrderId(10);
		orderEntity.setOrderId(orderId);
		orderEntity.setStatus(false);
		OrderEntity storedOrder = orderRepository.save(orderEntity);
		OrderDto returnValue = modelMapper.map(storedOrder, OrderDto.class);
		return returnValue;
	}

	/**
	 * Get order by order id method
	 */
	public OrderDto getOrderById(String orderId) {

		OrderDto returnValue = new OrderDto();
		ModelMapper modelMapper = new ModelMapper();
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		returnValue = modelMapper.map(orderEntity, OrderDto.class);
		return returnValue;
	}

	/**
	 * Update food method
	 */
	public OrderDto updateOrderDetails(String orderId, OrderDto order) {
		OrderDto returnValue = new OrderDto();
		ModelMapper modelMapper = new ModelMapper();

		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		orderEntity.setCost(order.getCost());
		orderEntity.setItems(order.getItems());
		orderEntity.setStatus(true);
		OrderEntity updatedOrder = orderRepository.save(orderEntity);
		returnValue = modelMapper.map(updatedOrder, OrderDto.class);
		return returnValue;
	}

	public void deleteOrder(String orderId) {
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		orderRepository.delete(orderEntity);

	}

	/**
	 * Delete use method
	 */
	public List<OrderDto> getOrders() {
		List<OrderDto> returnValue = new ArrayList<OrderDto>();
		Iterable<OrderEntity> iteratableObjects = orderRepository.findAll();
		for (OrderEntity orderEntity : iteratableObjects) {
			OrderDto orderDto = new OrderDto();
			BeanUtils.copyProperties(orderEntity, orderDto);
			returnValue.add(orderDto);
		}
		return returnValue;
	}

}
