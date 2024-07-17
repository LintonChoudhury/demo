package com.blo.services;

import java.util.List;

import com.blo.userDto.UserDto;

public interface UserService {
	
//	UserDto resisterNewUser(UserDto user);
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto user,Integer id);
	
	UserDto getUserById(Integer id);
	
	List<UserDto> getAllUsers();
	
	public Boolean deleteDepartment(Integer id);

	
}
