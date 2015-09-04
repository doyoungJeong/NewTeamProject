package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {
	private Connection conn;

	public OrderItemDao(Connection conn) {
		this.conn = conn;
	}

	public List<OrderItem> select(String memberID, int order_no) throws SQLException {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();

		String sql = "select p.product_no, p.product_name, o.orderitem_amount ,o.orderitem_price from "
				+ " orderitems o, products p  where o.product_no = p.product_no  " + "and  o.order_no= ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, order_no);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			OrderItem order = new OrderItem();
			order.setProductNo(rs.getInt("product_no"));
			order.setOrderItemName(rs.getString("product_name"));
			order.setOrderItemAmount(rs.getInt("orderitem_amount"));
			order.setOrderItemPrice(rs.getInt("orderitem_price"));
			String totalPrice = order.getOrderItemAmount() + " * " + order.getOrderItemPrice();
			order.setOrderItemtotalPrice(totalPrice);
			orderItemList.add(order);
		}

		rs.close();
		pstmt.close();
		return orderItemList;
	}

	public int insertFromCart(List<Cart> cartlist, String loginID, int orderNo) throws SQLException {
		int orderItem_no = 0;
		String sql = "insert into orderitems (order_no,"
				+ " product_no, orderitem_amount, orderitem_price) values(?,?,?,?)";
		// PreparedStatement pstmt = conn.prepareStatement(sql, new String[]
		// {"orderitem_no"});
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		for (Cart cart : cartlist) {
			pstmt = conn.prepareStatement(sql, new String[] { "orderitem_no" });
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, cart.getProductNo());
			pstmt.setInt(3, cart.getCartAmount());
			pstmt.setInt(4, cart.getProductPrice());
			pstmt.executeUpdate();
		}

		//rs.close();
		pstmt.close();

		return orderItem_no;

	}

}
