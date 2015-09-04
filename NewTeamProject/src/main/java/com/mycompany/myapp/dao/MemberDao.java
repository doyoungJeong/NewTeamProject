package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;

@Component
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer insert(Member member){
		Integer pk = null;
		String sql = "insert into members"
				+ " (member_id, member_name, member_password) "
				+ "values (?,?, ?)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getMemberId());
				pstmt.setString(2, member.getMemberName());
				pstmt.setString(3, member.getMemberPW()); 
				
				return pstmt;
			}
			
		}, keyHolder);
		Number keyNumber=keyHolder.getKey();
		pk=keyNumber.intValue();
		return pk;
	}
	
	
	//로그인하기 위해 id,password 등 개인정보 가져옴!
	public Member select(String memberID)throws SQLException{
		
		String sql = "select * from members where member_id =?";
	
		
		Member member=jdbcTemplate.queryForObject(sql,
				new Object[] {memberID},
				new RowMapper<Member>() { 
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member= new Member();
						member.setMemberId(rs.getString("member_id")); 
						member.setMemberName(rs.getString("member_name"));
						member.setMemberPW(rs.getString("member_password"));
						return member;
					}
				}
			);
			return member;
	}
}
