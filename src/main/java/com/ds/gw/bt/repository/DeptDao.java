package com.ds.gw.bt.repository;

import java.util.List;

import com.ds.gw.bt.dto.DeptDto;

public interface DeptDao {
	List<DeptDto> getDept(DeptDto dto);
}
