package com.altimetrik.food.delivery.fooddeliveryapp.service;

import java.util.List;

import com.altimetrik.food.delivery.fooddeliveryapp.dto.FoodDto;


public interface FoodService {

	FoodDto createFood(FoodDto food);
	FoodDto getFoodById(String foodId);
	FoodDto updateFoodDetails(String foodId, FoodDto foodDetails);
	void deleteFoodItem(String id);
	List<FoodDto> getFoods();
}
