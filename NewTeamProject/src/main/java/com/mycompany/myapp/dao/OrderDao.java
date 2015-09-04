package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.Order;

public class OrderDao {

	private Connection conn;

	// ������ ���Թ��
	public OrderDao(Connection conn) {
		this.conn = conn;
	}

	public List<Order> select(String memberID) throws SQLException {
		List<Order> orderlist = new ArrayList<Order>();
		String sql = "select o.order_no,  o.order_price, o.order_date ,m.member_name  "
				+ "from orders o , members  m  where o.member_id = m.member_id "
				+ "and m.member_id=? order by o.order_no asc ";
		/*
		 * String sql =
		 * "select p.product_no, p.product_name, o.orderitem_amount from " +
		 * " ORDERITEMS o, products p  where o.product_no = p.product_no(+)  " +
		 * "and  o.order_no= ?" ;
		 */
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberID);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			Order order = new Order();
			order.setOrderNo(rs.getInt("order_no"));
			order.setOrderPrice(rs.getInt("order_price"));
			order.setOrderDate(rs.getString("order_date"));
			order.setMemberId(rs.getString("member_name"));
			order.setMemberId(memberID);
			orderlist.add(order);
		}
		rs.close();
		pstmt.close();
		return orderlist;
	}

	public int insertFromCart(List<Cart> cart, String loginId) throws SQLException {
		Date now = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd��");
		// String strNow = sdf.format(now);
		int totalPrice = 0;
		int order_no = 0;
		List<Cart> insertedCart = cart;
		String sql = "insert into orders (member_id, order_date,order_price) values(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "order_no" });
		pstmt.setString(1, loginId);
		pstmt.setString(2, now.toString());
		for (Cart cartlist : insertedCart) {
			totalPrice += cartlist.getProductPrice() * cartlist.getCartAmount();

		}
		pstmt.setInt(3, totalPrice);

		int row = pstmt.executeUpdate();
		if (row == 1) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				order_no = rs.getInt(1);
			}
			rs.close();
		}
		pstmt.close();

		return order_no;
	}

}
