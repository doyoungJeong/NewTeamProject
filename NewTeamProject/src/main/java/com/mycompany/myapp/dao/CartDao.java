package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.Product;

public class CartDao {

	private Connection conn;

	// ������ ���Թ��
	public CartDao(Connection conn) {
		this.conn = conn;
	}

	// ��ٱ��ϳֱ�
	// īƮ�� ��ǰ �߰�
	public Integer insert(int selectedProductNo, int amount, String mem_id) throws SQLException {
		String pk = null;
		String sql = "select * from products where product_no=?";
		String sql2 = "insert into carts (product_no, member_id, cart_amount, cart_totalprice) values(?,?,?,?)";

		Cart cart = new Cart();
		Product product = new Product();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, selectedProductNo);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {

			product.setProductNo(rs.getInt("product_no"));
			product.setProductName(rs.getString("product_name"));
			product.setProductPrice(rs.getInt("product_price"));

		}
		rs.close();
		pstmt.close();

		PreparedStatement pstmt2 = conn.prepareStatement(sql2, new String[] { "cart_no" });
		pstmt2.setInt(1, product.getProductNo());
		pstmt2.setString(2, mem_id);
		pstmt2.setInt(3, amount);
		String totalPrice = amount + " * " + product.getProductPrice();
		pstmt2.setInt(4, product.getProductPrice());
		pstmt2.executeUpdate(); // ������ insert�� �Ǵ� ���� �� -> 1

		System.out.println("��ٱ��Ͽ� ����Ǿ����ϴ�");

		pstmt2.close();
		return 1;

	}

	// ��ٱ��� ����
	public List<Cart> selectByLoginID(String loginID) throws SQLException {

		List<Cart> list = new ArrayList<Cart>();
		// String sql = "select distinct p.product_no, p.product_name,
		// c.cart_amount, c.cart_totalprice from carts c, products p where
		// c.product_no = p.product_no and member_id = ?";
		// String sql = "select * from carts where member_id=?";

		String sql = "select p.product_no, p.product_name, p.product_price,c.cart_amount, c.cart_totalprice from "
				+ " carts c, products p  where c.product_no = p.product_no  " + "and  c.member_id= ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, loginID);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Cart cart = new Cart();
			cart.setProductNo(rs.getInt("product_no"));
			cart.setProductName(rs.getString("product_name"));
			cart.setProductPrice(rs.getInt("product_price"));
			cart.setCartAmount(rs.getInt("cart_amount"));
			cart.setCartTotalPrice(rs.getString("cart_totalPrice"));
			list.add(cart);

		}

		rs.close();
		pstmt.close();

		return list;
	}

	// ��ٱ��� limit���� ����
	public List<Cart> selectByPageNo(String loginID, int pageNo, int rowsPerPage) throws SQLException {

		List<Cart> list = new ArrayList<Cart>();

		String sql = "select p.product_no, p.product_name, p.product_price,c.cart_amount, c.cart_totalprice from "
				+ " carts c, products p  where c.product_no = p.product_no  " + "and  c.member_id= ? limit ?, ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, loginID);
		pstmt.setInt(2, (pageNo - 1) * rowsPerPage);
		pstmt.setInt(3, rowsPerPage);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Cart cart = new Cart();
			cart.setProductNo(rs.getInt("product_no"));
			cart.setProductName(rs.getString("product_name"));
			cart.setProductPrice(rs.getInt("product_price"));
			cart.setCartAmount(rs.getInt("cart_amount"));
			cart.setCartTotalPrice(rs.getString("cart_totalPrice"));
			list.add(cart);

		}

		rs.close();
		pstmt.close();

		return list;
	}

	public void deleteAll(String loginID) throws SQLException {
		String sql = "delete from carts where member_id= ? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginID);
		pstmt.executeUpdate();

		pstmt.close();
		System.out.println("�ֹ��� �Ϸ�Ǿ����ϴ�.");
	}

}
