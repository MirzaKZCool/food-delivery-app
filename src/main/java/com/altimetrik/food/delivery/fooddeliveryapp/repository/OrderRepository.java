package com.altimetrik.food.delivery.fooddeliveryapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.food.delivery.fooddeliveryapp.entity.OrderEntity;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

	OrderEntity findByOrderId(String orderId);
}
