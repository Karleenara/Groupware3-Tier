package com.ds.gw.pt.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ds.gw.bt.dto.DeptDto;
import com.ds.gw.bt.dto.HUDto;
import com.ds.gw.bt.dto.HobbyDto;
import com.ds.gw.bt.dto.UserDto;
import com.ds.gw.bt.service.DeptService;
import com.ds.gw.bt.service.HobbyService;
import com.ds.gw.bt.service.OpperService;
import com.ds.gw.bt.service.UserService;

import javax.annotation.Resource;

@Controller
public class UserController {
	
	private final RestTemplate restTemplete = new RestTemplate();
	
	// 사용자포털: (wirte)등록 페이지로 이동
	@RequestMapping(value="/user")
	public String write(Model model) {
		List<DeptDto> deptlist = restTemplete.getForObject("http://localhost:9090/api/deptlist", List.class);
		model.addAttribute("deptlist", deptlist);
		List<HobbyDto> hobbylist = restTemplete.getForObject("http://localhost:9090/api/hobbylist", List.class);
		model.addAttribute("hobbylist", hobbylist);
		return "write";
	}
	
	// 등록내용 DB에 저장
	@RequestMapping(value="/user/save")
	public String save(UserDto u_dto, HUDto hu_dto, Model model) {
		restTemplete.postForObject("http://localhost:9090/api/insertUser", u_dto, UserDto.class);

		System.out.println(hu_dto.getHobby_cd());
		String hulist = hu_dto.getHobby_cd();
		System.out.println(hulist);
		if(hulist.length() == 1) {
			restTemplete.postForObject("http://localhost:9090/api/insertHobby", hu_dto, HUDto.class);
		}else {
			String[] hulist2 = hulist.split(",");
			for (int i=0; i<hulist2.length; i++) {
				hu_dto.setHobby_cd(hulist2[i]);
				restTemplete.postForObject("http://localhost:9090/api/insertHobby", hu_dto, HUDto.class);
				System.out.println(hulist2[i]);
			}
		}
		return "redirect:/";
	}

	

}
