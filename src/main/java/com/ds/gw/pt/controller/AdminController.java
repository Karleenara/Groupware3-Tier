package com.ds.gw.pt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import com.ds.gw.pt.dto.DeptDto;
import com.ds.gw.pt.dto.HUDto;
import com.ds.gw.pt.dto.HobbyDto;
import com.ds.gw.pt.dto.UserDto;
import com.ds.gw.pt.service.ApiService;



@Controller
public class AdminController {
	@Resource(name="apiService")
	ApiService service;
	private final RestTemplate restTemplete = new RestTemplate();
	
	@RequestMapping(value="/admin")
	public String getUser(UserDto u_dto, Model model) {
		List<UserDto> userlist = service.getUser(u_dto);
		model.addAttribute("userlist", userlist);
		return "admin";
	}
	
	// 상세정보 보기
	@RequestMapping("/admin/{user_id}")
	public String getView(@PathVariable String user_id, UserDto u_dto,HobbyDto h_dto, DeptDto d_dto, HUDto hu_dto, Model model ) {
		List<UserDto> userlist = service.getUser(u_dto);
		model.addAttribute("getList", userlist);
		
		List<DeptDto> deptlist = service.getDept(d_dto);
		model.addAttribute("deptlist", deptlist);
		
		List<HobbyDto> hobbylist = service.getHobby(h_dto);
		model.addAttribute("hobbylist", hobbylist);
		
		String list_eh = service.viewuh(user_id, hu_dto);
		model.addAttribute("userHobby", list_eh);
		
		UserDto resultdto = service.getView(user_id, u_dto);
		model.addAttribute("userDto", resultdto);
		
		return "view";
		
	}
	
	// update 정보 저장
	@RequestMapping(value="/admin/save/{user_id}")
	public String updateUser(@PathVariable String user_id,UserDto u_dto,HUDto hu_dto, Model model ) {
		List<UserDto> userlist = service.getUser(u_dto);
		model.addAttribute("getList", userlist);
		
		service.updateUser(user_id, u_dto);
		
		service.deleteHobby(user_id, hu_dto);
		String hulist = hu_dto.getHobby_cd();
		String[] hulist2 = hulist.split(",");
		for (int i=0; i<hulist2.length; i++) {
			hu_dto.setHobby_cd(hulist2[i]);
			service.insertHobby(user_id, hu_dto);
		}
		
		return "redirect:/admin";
		
	}
	
	@RequestMapping("/admin/delete/{user_id}")
	public String deleteUser(@PathVariable String user_id,UserDto u_dto,HUDto hu_dto, Model model ) {
		List<UserDto> userlist = service.getUser(u_dto);
		model.addAttribute("getList", userlist);
		
		service.deleteUser(user_id, u_dto);
		service.deleteHobby(user_id, hu_dto);
		return "redirect:/admin";
		
	}
	
	
}
