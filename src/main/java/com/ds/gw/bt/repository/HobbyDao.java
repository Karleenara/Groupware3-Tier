package com.ds.gw.bt.repository;

import java.util.List;

import com.ds.gw.bt.dto.HUDto;
import com.ds.gw.bt.dto.HobbyDto;

public interface HobbyDao {
	List<HobbyDto> getHobby(HobbyDto dto);

}
