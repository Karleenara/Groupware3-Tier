package com.ds.gw.bt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ds.gw.bt.dto.UserDto;
import com.ds.gw.bt.repository.UserDao;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	UserDao dao;

	@Override
	public List<UserDto> getList(UserDto dto) {
		return dao.getList(dto);
	}

	@Override
	public void insertUser(UserDto dto) {
		dao.insertUser(dto);
	}

	@Override
	public UserDto getView(UserDto dto) {
		return dao.getView(dto);
	}

	@Override
	public void updateUser(UserDto dto) {
		dao.updateUser(dto);
	}

	@Override
	public void deleteUser(UserDto dto) {
		dao.deleteUser(dto);
	}


	
}
