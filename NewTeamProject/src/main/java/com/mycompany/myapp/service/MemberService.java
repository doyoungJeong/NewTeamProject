package com.mycompany.myapp.service;

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
	
	public boolean login(String memberID, String memberPW){
		boolean is_right=false;
		
		Member member=memberDao.select(memberID);
		
		if(member.getMemberId().equals(memberID)){
			if(member.getMemberPW().equals(memberPW)){
				is_right=true;
			}else{
				System.out.println("비밀번호가 틀렸습니다.");
				is_right=false;
			}
		} else{
			System.out.println("아이디가 존재하지 않습니다.");
			is_right=false;
		}

		return is_right;

	}

}