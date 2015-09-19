package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;

@Component
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// ����媛���
	public String insert(Member member){
		String sql = "insert into members"
				+ " (member_id, member_name, member_password) "
				+ "values (?, ?, ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getMemberId());
				pstmt.setString(2, member.getMemberName());
				pstmt.setString(3, member.getMemberPW()); 
				
				return pstmt;
			}
			
		});
		return member.getMemberId();
	}
	
	
	//濡�洹몄�명��湲� ���� id,password �� 媛��몄��蹂� 媛��몄��!
	public Member select(String memberID){
		
		String sql = "select * from members where member_id =?";
		List<Member> list=jdbcTemplate.query(sql,
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
			if(list.isEmpty()){
				return null;
			}else{
				Member member=list.get(0);
				return member;
			}
	}
}