package com.altimetrik.food.delivery.fooddeliveryapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.food.delivery.fooddeliveryapp.entity.FoodEntity;


@Repository
public interface FoodRepository extends CrudRepository<FoodEntity, Long> {

	FoodEntity findByFoodId(String foodId);
}
