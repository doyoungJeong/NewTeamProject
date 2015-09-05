package com.mycompany.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/login/list")
	public String list(){
		return "login/list";
	}
	
	@RequestMapping("/login/check")
	public String check(String memberID, String memberPW, Model model){
		
		Member member=new Member();
		
		boolean is_right=memberService.login(memberID, memberPW);
		
		if(is_right){
			model.addAttribute("is_right", is_right);
		}
		return "/login/check";
		
	}
}
