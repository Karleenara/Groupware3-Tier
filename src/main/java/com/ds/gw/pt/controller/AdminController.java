package com.ds.gw.pt.controller;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ds.gw.bt.dto.DeptDto;
import com.ds.gw.bt.dto.HUDto;
import com.ds.gw.bt.dto.HobbyDto;
import com.ds.gw.bt.dto.UserDto;



@Controller
public class AdminController {
	
	private final RestTemplate restTemplete = new RestTemplate();
	
	@RequestMapping(value="/admin")
	public String getUser(Model model) {
		List<UserDto> userlist = restTemplete.getForObject("http://localhost:9090/api/userlist", List.class);
		String searchKeyword = restTemplete.getForObject("http://localhost:9090/api/searchKeyword", String.class);
		model.addAttribute("userlist", userlist);
		model.addAttribute("searchKeyword", searchKeyword);
		return "admin";
	}
	
	// 상세정보 보기
	@RequestMapping("/admin/{user_id}")
	public String getView(@PathVariable String user_id, UserDto u_dto, HUDto hu_dto, Model model ) {
		List<UserDto> userlist = restTemplete.getForObject("http://localhost:9090/api/userlist", List.class);
		List<HobbyDto> hobbylist = restTemplete.getForObject("http://localhost:9090/api/hobbylist", List.class);
		List<DeptDto> deptlist = restTemplete.getForObject("http://localhost:9090/api/deptlist", List.class);
//		String searchKeyword = restTemplete.getForObject("http://localhost:9090/api/searchKeyword", String.class);
		
		model.addAttribute("getList", userlist);
		model.addAttribute("deptlist", deptlist);
		
		
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/getview")
				.queryParam("user_id", user_id)
				.encode()
				.build()
				.toUri();
		
		URI uri2 = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/userhobby")
				.queryParam("user_id", user_id)
				.encode()
				.build()
				.toUri();
		
		String list_eh = restTemplete.getForObject(uri2, String.class);
		model.addAttribute("userHobby", list_eh);
		
		UserDto resultdto = restTemplete.getForObject(uri, UserDto.class);
		model.addAttribute("hobbylist", hobbylist);
		model.addAttribute("userDto", resultdto);
		
		
		
		return "view";
		
	}
	
	// update 정보 저장
	@RequestMapping(value="/admin/save/{user_id}")
	public String updateUser(@PathVariable String user_id,UserDto u_dto,HUDto hu_dto, Model model ) {
		List<UserDto> userlist = restTemplete.getForObject("http://localhost:9090/api/userlist", List.class);
		model.addAttribute("getList", userlist);
		
		restTemplete.postForObject("http://localhost:9090/api/updateUser", u_dto, UserDto.class);
		
		restTemplete.postForObject("http://localhost:9090/api/deleteHobby", hu_dto, HUDto.class);
		String hulist = hu_dto.getHobby_cd();
		String[] hulist2 = hulist.split(",");
		for (int i=0; i<hulist2.length; i++) {
			hu_dto.setHobby_cd(hulist2[i]);
			restTemplete.postForObject("http://localhost:9090/api/insertHobby", hu_dto, HUDto.class);
		}
		
		return "redirect:/admin";
		
	}
	
	@RequestMapping("/admin/delete/{user_id}")
	public String deleteUser(@PathVariable String user_id,UserDto u_dto,HUDto hu_dto, Model model ) {
		List<UserDto> userlist = restTemplete.getForObject("http://localhost:9090/api/userlist", List.class);
		model.addAttribute("getList", userlist);
		
		restTemplete.postForObject("http://localhost:9090/api/deleteUser", u_dto, UserDto.class);
		restTemplete.postForObject("http://localhost:9090/api/deleteHobby", hu_dto, HUDto.class);
		return "redirect:/admin";
		
	}
	
	
}
