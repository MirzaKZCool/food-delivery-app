package com.altimetrik.food.delivery.fooddeliveryapp.service;

import java.util.List;

import com.altimetrik.food.delivery.fooddeliveryapp.dto.UserDto;

public interface UserService{

	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId, UserDto user);
	void deleteUser(String userId);
	List<UserDto> getUsers();
}
