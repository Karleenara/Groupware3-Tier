package com.ds.gw.bt.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ds.gw.bt.dto.UserDto;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	@Autowired
	SqlSessionTemplate sm;

	@Override
	public List<UserDto> getList(UserDto dto) {
		return sm.selectList("getList",dto);
	}

	@Override
	public void insertUser(UserDto dto) {
		sm.insert("insertUser",dto);
	}

	@Override
	public UserDto getView(UserDto dto) {
		return sm.selectOne("getView", dto);
	}

	@Override
	public void updateUser(UserDto dto) {
		sm.update("updateUser", dto);
	}

	@Override
	public void deleteUser(UserDto dto) {
		sm.delete("deleteUser", dto);
	}


	
}
