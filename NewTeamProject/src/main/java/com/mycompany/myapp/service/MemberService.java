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
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				is_right=false;
			}
		} else{
			System.out.println("���̵� �������� �ʽ��ϴ�.");
			is_right=false;
		}

		return is_right;

	}

}