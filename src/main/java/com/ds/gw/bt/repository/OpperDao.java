package com.ds.gw.bt.repository;

import java.util.List;

import com.ds.gw.bt.dto.HUDto;

public interface OpperDao {
	void insertHobby(HUDto hu_dto);
	List<HUDto> userHobby(HUDto hu_dto);
	void deleteHobby(HUDto hu_dto);
}
