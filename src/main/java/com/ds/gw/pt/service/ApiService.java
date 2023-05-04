package com.ds.gw.pt.service;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ds.gw.pt.dto.DeptDto;
import com.ds.gw.pt.dto.HUDto;
import com.ds.gw.pt.dto.HobbyDto;
import com.ds.gw.pt.dto.UserDto;

@Service
public class ApiService {
	private final RestTemplate restTemplete = new RestTemplate();
	
// ------------user----------------
	public List<UserDto> getUser(UserDto u_dto){
		String searchKeyword = u_dto.getSearchKeyword();
		String uri = "http://localhost:9090/api/userlist?searchKeyword="+searchKeyword;
		List<UserDto> userlist = restTemplete.getForObject(uri, List.class);
		return userlist;
	}
	
	public UserDto getView(String user_id,UserDto u_dto) {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/getview")
				.queryParam("user_id", user_id)
				.encode()
				.build()
				.toUri();
		UserDto resultdto = restTemplete.getForObject(uri, UserDto.class);
		return resultdto;
	}
	
	public void insertUser(UserDto u_dto) {
		String uri = "http://localhost:9090/api/insertUser";
		restTemplete.postForObject(uri, u_dto, UserDto.class);
	}
	
	public void updateUser(String user_id, UserDto u_dto) {
		String uri = "http://localhost:9090/api/updateUser";
		restTemplete.postForObject(uri, u_dto, UserDto.class);
	}
	
	public void deleteUser(String user_id, UserDto u_dto) {
		String uri = "http://localhost:9090/api/deleteUser";
		restTemplete.postForObject(uri, u_dto, UserDto.class);
	}
	
// -----------dept-----------------
	public List<DeptDto> getDept(DeptDto d_dto){
		String uri = "http://localhost:9090/api/deptlist";
		List<DeptDto> deptlist = restTemplete.getForObject(uri, List.class);
		return deptlist;
	}
	
	
// -----------hobby------------------
	public List<HobbyDto> getHobby(HobbyDto h_dto){
		String uri = "http://localhost:9090/api/hobbylist";
		List<HobbyDto> hobbylist = restTemplete.getForObject(uri, List.class);
		return hobbylist;
	}
	
// -----------userhobby-------------
	public String viewuh(String user_id,HUDto hu_dto) {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/userhobby")
				.queryParam("user_id", user_id)
				.encode()
				.build()
				.toUri();
		String list_eh = restTemplete.getForObject(uri, String.class);
		return list_eh;
		
	}
	
	public void insertHobby(String user_id, HUDto hu_dto) {
		String uri = "http://localhost:9090/api/insertHobby";
		restTemplete.postForObject(uri, hu_dto, HUDto.class);
	}
	
	public void deleteHobby(String user_id, HUDto hu_dto) {
		String uri = "http://localhost:9090/api/deleteHobby";
		restTemplete.postForObject(uri, hu_dto, HUDto.class);
	}
	

}
