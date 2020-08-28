package com.altimetrik.food.delivery.fooddeliveryapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.food.delivery.fooddeliveryapp.dto.OrderDto;
import com.altimetrik.food.delivery.fooddeliveryapp.model.request.OrderDetailsRequestModel;
import com.altimetrik.food.delivery.fooddeliveryapp.model.response.OperationStatusModel;
import com.altimetrik.food.delivery.fooddeliveryapp.model.response.OrderDetailsResponse;
import com.altimetrik.food.delivery.fooddeliveryapp.model.response.RequestOperationName;
import com.altimetrik.food.delivery.fooddeliveryapp.model.response.RequestOperationStatus;
import com.altimetrik.food.delivery.fooddeliveryapp.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@GetMapping("/get/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) {
		OrderDetailsResponse returnValue = new OrderDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		OrderDto orderDto = orderService.getOrderById(id);
		returnValue = modelMapper.map(orderDto, OrderDetailsResponse.class);
		return returnValue;
	}
	
	@PostMapping("/create")
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDetailsResponse returnValue = new OrderDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		OrderDto createdOrder = orderService.createOrder(order);
		returnValue = modelMapper.map(createdOrder, OrderDetailsResponse.class);
		return returnValue;
	}
	
	@PutMapping("/update/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) {
		OrderDetailsResponse returnValue = new OrderDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		OrderDto orderDto = new OrderDto();
		orderDto = modelMapper.map(order, OrderDto.class);
		OrderDto updatedOrder = orderService.updateOrderDetails(id, orderDto);
		returnValue = modelMapper.map(updatedOrder, OrderDetailsResponse.class);
		return returnValue;
	}
	
	@DeleteMapping("/delete/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		orderService.deleteOrder(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
	
	@GetMapping("/get-orders")
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDetailsResponse> returnValue = new ArrayList<OrderDetailsResponse>();
		List<OrderDto> orders = orderService.getOrders();
		for(OrderDto orderDto : orders) {
			OrderDetailsResponse response = new OrderDetailsResponse();
			BeanUtils.copyProperties(orderDto, response);
			returnValue.add(response);
		}
		return returnValue;
	}
}
