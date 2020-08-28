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

import com.altimetrik.food.delivery.fooddeliveryapp.dto.FoodDto;
import com.altimetrik.food.delivery.fooddeliveryapp.model.request.FoodDetailsRequestModel;
import com.altimetrik.food.delivery.fooddeliveryapp.model.response.FoodDetailsResponse;
import com.altimetrik.food.delivery.fooddeliveryapp.model.response.OperationStatusModel;
import com.altimetrik.food.delivery.fooddeliveryapp.model.response.RequestOperationName;
import com.altimetrik.food.delivery.fooddeliveryapp.model.response.RequestOperationStatus;
import com.altimetrik.food.delivery.fooddeliveryapp.service.FoodService;

@RestController
@RequestMapping("/foods")
public class FoodController {
	
	@Autowired
	FoodService foodService;

	@GetMapping("/get-food/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) {
		FoodDetailsResponse returnValue = new FoodDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		FoodDto foodDto = foodService.getFoodById(id);
		returnValue = modelMapper.map(foodDto, FoodDetailsResponse.class);
		return returnValue;
	}

	@PostMapping("/create-food")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		
		FoodDetailsResponse returnValue = new FoodDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		FoodDto foodDto = modelMapper.map(foodDetails, FoodDto.class);
		FoodDto createFood = foodService.createFood(foodDto);
		
		returnValue = modelMapper.map(createFood, FoodDetailsResponse.class);
		
		return returnValue;
	}

	@PutMapping("/update-food/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) {
		
		FoodDetailsResponse returnValue = new FoodDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		FoodDto foodDto = new FoodDto();
		foodDto = modelMapper.map(foodDetails, FoodDto.class);
		
		FoodDto updatedUser = foodService.updateFoodDetails(id, foodDto);
		returnValue = modelMapper.map(updatedUser, FoodDetailsResponse.class);
		return returnValue;
	}

	@DeleteMapping("/delete-food/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		foodService.deleteFoodItem(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
	
	@GetMapping("/get-food")
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDetailsResponse> returnValue = new ArrayList<FoodDetailsResponse>();
		
		List<FoodDto> foods = foodService.getFoods();
		
		for(FoodDto foodDto: foods) {
			FoodDetailsResponse response = new FoodDetailsResponse();
			BeanUtils.copyProperties(foodDto, response);
			returnValue.add(response);
		}
		return returnValue;
	}
}
