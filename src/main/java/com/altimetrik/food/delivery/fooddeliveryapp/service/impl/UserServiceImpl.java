package com.altimetrik.food.delivery.fooddeliveryapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.food.delivery.fooddeliveryapp.dto.UserDto;
import com.altimetrik.food.delivery.fooddeliveryapp.dto.Utils;
import com.altimetrik.food.delivery.fooddeliveryapp.entity.UserEntity;
import com.altimetrik.food.delivery.fooddeliveryapp.repository.UserRepository;
import com.altimetrik.food.delivery.fooddeliveryapp.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;



	/**
	 * Get user by user id method
	 */
	public UserDto getUserByUserId(String userId) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);

		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	/**
	 * Update user method
	 */
	public UserDto updateUser(String userId, UserDto user) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		UserEntity updatedUserDetails = userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUserDetails, returnValue);

		return returnValue;
	}

	/**
	 * Delete use method
	 */
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		userRepository.delete(userEntity);
	}

	public List<UserDto> getUsers() {
		List<UserDto> returnValue = new ArrayList<UserDto>();
		Iterable<UserEntity> iterableObjects = userRepository.findAll();
		for (UserEntity userEntity : iterableObjects) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		return returnValue;
	}

}
