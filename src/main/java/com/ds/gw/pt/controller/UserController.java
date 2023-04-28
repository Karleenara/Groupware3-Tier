package com.ds.gw.pt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ds.gw.bt.dto.DeptDto;
import com.ds.gw.bt.dto.HUDto;
import com.ds.gw.bt.dto.HobbyDto;
import com.ds.gw.bt.dto.UserDto;
import com.ds.gw.pt.service.ApiService;


@Controller
public class UserController {
	@Resource(name="apiService")
	ApiService service;
	private final RestTemplate restTemplete = new RestTemplate();
	
	// 사용자포털: (wirte)등록 페이지로 이동
	@RequestMapping(value="/user")
	public String write(DeptDto d_dto, HobbyDto h_dto, Model model) {
		List<DeptDto> deptlist = service.getDept(d_dto);
		model.addAttribute("deptlist", deptlist);
		
		List<HobbyDto> hobbylist = service.getHobby(h_dto);
		model.addAttribute("hobbylist", hobbylist);
		return "write";
	}
	
	// 등록내용 DB에 저장
	@RequestMapping(value="/user/save")
	public String save(UserDto u_dto, HUDto hu_dto, Model model) {
		service.insertUser(u_dto);

		String hulist = hu_dto.getHobby_cd();
		if(hulist.length() == 1) {
			service.insertHobby(hulist, hu_dto);
		}else {
			String[] hulist2 = hulist.split(",");
			for (int i=0; i<hulist2.length; i++) {
				hu_dto.setHobby_cd(hulist2[i]);
				service.insertHobby(hulist, hu_dto);
				System.out.println(hulist2[i]);
			}
		}
		return "redirect:/";
	}

	

}
