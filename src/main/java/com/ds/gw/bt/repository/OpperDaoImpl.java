package com.ds.gw.bt.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ds.gw.bt.dto.HUDto;

@Repository("opperDao")
public class OpperDaoImpl implements OpperDao {
	@Autowired
	SqlSessionTemplate sm;
	
	@Override
	public void insertHobby(HUDto hu_dto) {
		sm.insert("insertHobby", hu_dto);
	}

	@Override
	public List<HUDto> userHobby(HUDto hu_dto) {
		return sm.selectList("userHobby", hu_dto);
	}

	@Override
	public void deleteHobby(HUDto hu_dto) {
		sm.delete("deleteHobby", hu_dto);
}
}
