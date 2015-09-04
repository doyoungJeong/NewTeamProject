package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.OrderItem;

@Component
public class OrderItemDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<OrderItem> select(String memberID, int order_no) throws SQLException {
		
		
		String sql = "select p.product_no, p.product_name, o.orderitem_amount ,o.orderitem_price from "
				+ " orderitems o, products p  where o.product_no = p.product_no  " + "and  o.order_no= ?";

		List<OrderItem> orderItemList =jdbcTemplate.query(sql,
				new Object[]{memberID},
				new RowMapper<OrderItem>(){ 
					@Override
					public OrderItem mapRow(ResultSet rs, int rownum) throws SQLException {
						OrderItem order = new OrderItem();
						order.setProductNo(rs.getInt("product_no"));
						order.setOrderItemName(rs.getString("product_name"));
						order.setOrderItemAmount(rs.getInt("orderitem_amount"));
						order.setOrderItemPrice(rs.getInt("orderitem_price"));
						String totalPrice = order.getOrderItemAmount() + " * " + order.getOrderItemPrice();
						order.setOrderItemtotalPrice(totalPrice);
						return order;
					}
				}		
			);	
	
		return orderItemList;
		
	
	}

	public int insert(List<Cart> cartlist, String loginID, int orderNo) throws SQLException {
		Integer pk = null;
		String sql = "insert into orderitems (order_no,"
				+ " product_no, orderitem_amount, orderitem_price) values(?,?,?,?)";
		
		for (Cart cart : cartlist) {
		KeyHolder keyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"orderitem_no" });
			
					pstmt.setInt(1, orderNo);
					pstmt.setInt(2, cart.getProductNo());
					pstmt.setInt(3, cart.getCartAmount());
					pstmt.setInt(4, cart.getProductPrice());
					return pstmt;
			}
			
		}, keyHolder);
		Number keyNumber=keyHolder.getKey();
		pk=keyNumber.intValue();
		}
		return pk;	
	}
}
