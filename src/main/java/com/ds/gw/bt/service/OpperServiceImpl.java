package com.ds.gw.bt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ds.gw.bt.dto.HUDto;
import com.ds.gw.bt.repository.OpperDao;

import javax.annotation.Resource;

@Service("opperService")
public class OpperServiceImpl implements OpperService{
	@Resource(name="opperDao")
	OpperDao dao;
	
	@Override
	public void insertHobby(HUDto hu_dto) {
		dao.insertHobby(hu_dto);
	}

	@Override
	public List<HUDto> userHobby(HUDto hu_dto) {
		return dao.userHobby(hu_dto);
	}

	@Override
	public void deleteHobby(HUDto hu_dto) {
		dao.deleteHobby(hu_dto);
	}

}
