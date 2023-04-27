package com.ds.gw.bt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ds.gw.bt.dto.HobbyDto;
import com.ds.gw.bt.repository.HobbyDao;

import javax.annotation.Resource;

@Service("hobbyService")
public class HobbyServiceImpl implements HobbyService {
	@Resource(name="hobbyDao")
	HobbyDao dao;

	@Override
	public List<HobbyDto> getHobby(HobbyDto dto) {
		return dao.getHobby(dto);
	}




}
