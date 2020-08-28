package com.altimetrik.food.delivery.fooddeliveryapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.food.delivery.fooddeliveryapp.dto.FoodDto;
import com.altimetrik.food.delivery.fooddeliveryapp.dto.Utils;
import com.altimetrik.food.delivery.fooddeliveryapp.entity.FoodEntity;
import com.altimetrik.food.delivery.fooddeliveryapp.repository.FoodRepository;
import com.altimetrik.food.delivery.fooddeliveryapp.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	FoodRepository foodRepository;

	@Autowired
	Utils utils;

	/**
	 * Create food method
	 */
	public FoodDto createFood(FoodDto food) {
		ModelMapper modelMapper = new ModelMapper();
		FoodEntity foodEntity = modelMapper.map(food, FoodEntity.class);
		String publicFoodId = utils.generateFoodId(30);
		foodEntity.setFoodId(publicFoodId);
		FoodEntity storedFood = foodRepository.save(foodEntity);
		FoodDto foodDto = new FoodDto();
		foodDto = modelMapper.map(storedFood, FoodDto.class);
		return foodDto;
	}

	/**
	 * Get food by food id method
	 */
	public FoodDto getFoodById(String foodId) {
		FoodDto returnValue = new FoodDto();
		ModelMapper modelMapper = new ModelMapper();
		FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
		if (foodEntity == null) {
		}
		returnValue = modelMapper.map(foodEntity, FoodDto.class);
		return returnValue;
	}

	/**
	 * Update food method
	 */
	public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) {
		FoodDto returnValue = new FoodDto();
		ModelMapper modelMapper = new ModelMapper();
		FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
		foodEntity.setFoodCategory(foodDetails.getFoodCategory());
		foodEntity.setFoodName(foodDetails.getFoodName());
		foodEntity.setFoodPrice(foodDetails.getFoodPrice());
		FoodEntity updatedFoodEntity = foodRepository.save(foodEntity);
		returnValue = modelMapper.map(updatedFoodEntity, FoodDto.class);
		return returnValue;
	}

	/**
	 * Delete use method
	 */
	public void deleteFoodItem(String id) {
		FoodEntity foodEntity = foodRepository.findByFoodId(id);
		foodRepository.delete(foodEntity);

	}

	public List<FoodDto> getFoods() {
		List<FoodDto> returnValue = new ArrayList<FoodDto>();
		Iterable<FoodEntity> iterableObjects = foodRepository.findAll();
		for (FoodEntity foodEntity : iterableObjects) {
			FoodDto foodDto = new FoodDto();
			BeanUtils.copyProperties(foodEntity, foodDto);
			returnValue.add(foodDto);
		}
		return returnValue;
	}

}
