package com.mycompany.myapp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.Order;

@Component
public class OrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Order> select(String memberID)  {
		System.out.println("orderDao select메소드들어옴");
		
		String sql = "select o.order_no,  o.order_price, o.order_date ,m.member_name  "
				+ "from orders o , members  m  where o.member_id = m.member_id "
				+ "and m.member_id=? order by o.order_no asc ";
	
		
		List<Order> orderlist =jdbcTemplate.query(sql,
				new Object[]{memberID},
				new RowMapper<Order>(){ 
					@Override
					public Order mapRow(ResultSet rs, int rownum) throws SQLException {
						Order order = new Order();
						order.setOrderNo(rs.getInt("order_no"));
						order.setOrderPrice(rs.getInt("order_price"));
						order.setOrderDate(rs.getString("order_date"));
						order.setMemberName(rs.getString("member_name"));
						order.setMemberId(memberID);
						return order;
					}
				}		
			);	
		System.out.println("orderDao select메소드 끝냄");
		return orderlist;
	}

	public int insert(List<Cart> cart, String loginId)  {
		
		Date now = new Date();
		Integer pk = null;
		String sql = "insert into orders (member_id, order_date,order_price) values(?,?,?)";
		KeyHolder keyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"order_no" });
				int totalPrice = 0;
				pstmt.setString(1, loginId);
				pstmt.setString(2, now.toString());
				for (Cart cartlist : cart) {
					totalPrice += cartlist.getProductPrice() * cartlist.getCartAmount();

				}
				pstmt.setInt(3, totalPrice);
				return pstmt;
			}
			
		}, keyHolder);
		Number keyNumber=keyHolder.getKey();
		pk=keyNumber.intValue();
		return pk;

	
	}

}
