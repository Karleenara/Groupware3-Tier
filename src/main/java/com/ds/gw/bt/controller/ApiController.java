package com.ds.gw.bt.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.gw.bt.dto.DeptDto;
import com.ds.gw.bt.dto.HUDto;
import com.ds.gw.bt.dto.HobbyDto;
import com.ds.gw.bt.dto.UserDto;
import com.ds.gw.bt.service.DeptService;
import com.ds.gw.bt.service.HobbyService;
import com.ds.gw.bt.service.OpperService;
import com.ds.gw.bt.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Resource(name="userService")
	UserService userservice;
	@Resource(name="hobbyService")
	HobbyService hobbyservice;
	@Resource(name="opperService")
	OpperService opperservice;
	@Resource(name="deptService")
	DeptService deptservice;

	
// ---------userservice--------
	
	@RequestMapping("/userlist")
	public List<UserDto> getUser(UserDto u_dto){
		return userservice.getList(u_dto);
	}
	
	@RequestMapping("/getview")
	public UserDto getView(UserDto u_dto) {
		return userservice.getView(u_dto);
	}
	
	@RequestMapping("/insertUser")
	public ResponseEntity<UserDto> insertUser(@RequestBody UserDto u_dto){
		userservice.insertUser(u_dto);
		return ResponseEntity.ok(u_dto);
	}
	
	@RequestMapping("/updateUser")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto u_dto){
		userservice.updateUser(u_dto);
		return ResponseEntity.ok(u_dto);
	}
	
	@RequestMapping("/deleteUser")
	public ResponseEntity<UserDto> deleteUser(@RequestBody UserDto u_dto){
		userservice.deleteUser(u_dto);
		return ResponseEntity.ok(u_dto);
	}
	
	@RequestMapping("/searchKeyword")
	public String getSearchkey(UserDto u_dto) {
		return u_dto.getSearchKeyword();
	}
	
//	------------deptservice--------------------
	
	
	@RequestMapping("/deptlist")
	public List<DeptDto> getDept(DeptDto d_dto){
		return deptservice.getDept(d_dto);
	}
	
//	----------hobbyservice--------------
	
	@RequestMapping("/hobbylist")
	public List<HobbyDto> getHobby(HobbyDto h_dto){
		return hobbyservice.getHobby(h_dto);
	}
	
	
//	----------opperservice---------
	
	@RequestMapping("/userhobby")
	public StringBuffer Viewuh(HUDto hu_dto){
		List<HUDto> uhlist = opperservice.userHobby(hu_dto);
		StringBuffer userHobby = new StringBuffer();
		for(int i=0; i < uhlist.size(); i++) {
			userHobby.append(uhlist.get(i).getOpper_cd());
		}
		return userHobby; 
	}
	
	
	
	@RequestMapping("/insertHobby")
	public ResponseEntity<HUDto> insertHobby(@RequestBody HUDto hu_dto){
		opperservice.insertHobby(hu_dto);
		return ResponseEntity.ok(hu_dto);
	}
	
	@RequestMapping("/deleteHobby")
	public ResponseEntity<HUDto> deleteHobby(@RequestBody HUDto hu_dto){
		opperservice.deleteHobby(hu_dto);
		return ResponseEntity.ok(hu_dto);
	}


	

}
