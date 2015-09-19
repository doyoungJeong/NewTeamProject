package com.mycompany.myapp.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.MemberDao;
import com.mycompany.myapp.dto.Member;

@Component
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public void addMember(Member member){
		memberDao.insert(member);
	}
	
	public String login(String memberID, String memberPW, HttpSession session){
		String state = null;
		Member member=memberDao.select(memberID);
	
		if(member!=null){
			if(memberID.equals(member.getMemberId())){
				if(memberPW.equals(member.getMemberPW())){
					session.setAttribute("memberId", memberID);
					state="success";
				}
				else{
					state="wrong Password";
				}
			}
		}
		else{
			state="wrong ID";
		}
		
		return state;

	}

}