package com.ds.gw.bt.service;

import java.util.List;

import com.ds.gw.bt.dto.UserDto;

public interface UserService {
	List<UserDto> getList(UserDto dto); 
	void insertUser(UserDto dto);
	UserDto getView(UserDto dto);
	void updateUser(UserDto dto);
	void deleteUser(UserDto dto);
}
