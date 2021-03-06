package com.mycompany.myapp.controller;

import javax.servlet.http.HttpSession;

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
	public String check(String memberID, String memberPW, HttpSession session ,Model model){
		
		String result=memberService.login(memberID, memberPW, session);
		model.addAttribute("result", result);

		return "login/check";
		
	}

	@RequestMapping("/login/newmember")
	public String New(){
		return "login/newmember";
	}
	
	@RequestMapping("/login/add")
	public String add(String memberID, String memberName, String memberPW, Model model){
		Member member=new Member();
		member.setMemberId(memberID);
		member.setMemberName(memberName);
		member.setMemberPW(memberPW);
		String ck=memberService.addMember(member);
		model.addAttribute("ck", ck);
		return "login/add";
	}
	
	@RequestMapping("/login/logout")
	public String logout(HttpSession session){
		session.removeAttribute("memberId");
		return "redirect:list";
	}
	
}
