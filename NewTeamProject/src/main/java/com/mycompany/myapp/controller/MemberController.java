package com.mycompany.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.myapp.service.MemberService;

public class MemberController {
private int a;
private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/member/login")
	public String login(){
		logger.info("login()");
		//return "redirect:/member/login";
		return "member/login";
	}
	
	
}
