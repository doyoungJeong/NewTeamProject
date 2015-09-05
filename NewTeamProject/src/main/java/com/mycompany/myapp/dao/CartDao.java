package com.mycompany.myapp.dao;

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
import com.mycompany.myapp.dto.Product;
@Component
public class CartDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Product select(int product_no){
		
		String sql = "select * from products where product_no=?";
		Product product= jdbcTemplate.queryForObject(    // ?ถ?๋น็ฟ๏ฟ? ? ?๋ฆ?? ??็ญ๋พ?ฃ๏ฟฝ๋ฑฝ ? ?๋งๅ ?๋ฆ? query
				sql,
				new Object[] {product_no},
				new RowMapper<Product>() {

					@Override
					public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
						Product product = new Product();
						product.setProductNo(rs.getInt("product_no"));
						product.setProductName(rs.getString("product_name"));
						product.setProductPrice(rs.getInt("product_price"));
						product.setProductInfo(rs.getString("product_info"));
						product.setOriginalFileName(rs.getString("product_original_file_name"));
						product.setFilesystemName(rs.getString("product_filesystem_name"));  
						product.setContentType(rs.getString("product_content_type"));
						return product;
					}
					
				}
			);   	
		return product;
	}
	
	public Integer insert(Product product, String loginId, Cart cart)  {
		Integer pk = null;
		
		String sql = "insert into carts (product_no, member_id, cart_amount, cart_totalprice) values(?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"cart_no"});
				
				pstmt.setInt(1, product.getProductNo());
				pstmt.setString(2, loginId);
				pstmt.setInt(3, cart.getCartAmount());
				pstmt.setString(4, cart.getCartTotalPrice());
				return pstmt;
			}	
		}, keyHolder );
		Number keyNumber = keyHolder.getKey();
		pk = keyNumber.intValue();
		return pk;
	}


	public List<Cart> select(String loginID)  {

		
		// String sql = "select distinct p.product_no, p.product_name,
		// c.cart_amount, c.cart_totalprice from carts c, products p where
		// c.product_no = p.product_no and member_id = ?";
		// String sql = "select * from carts where member_id=?";

		String sql = "select p.product_no, p.product_name, p.product_price,c.cart_amount, c.cart_totalprice from "
				+ " carts c, products p  where c.product_no = p.product_no  " + "and  c.member_id= ? ";
		
		List<Cart> list = jdbcTemplate.query(   //  query๏ฟฝ๋ตณ๏ฟฝ๋ช? ? ??? ?๋ฟ?? ?๋ต? list
				sql,
				new Object[] {loginID},// ? ? ??พ ็ญ๋พ?ฆๅฏ๏ฟฝ ? ?๋ต? ๏ฟฝโค??๏ฟ? ?ถ?๏ฟฝใ๋ฑ? ? ??? ???
				new RowMapper<Cart>() { // sql? ?? ? ??ฃ ?ถ??? ?์ฃฌๅ ?๊ถ? ??ด?๏ฟฝ์ฅ? ?๋ฒ? ?ถ?๏ฟฝใ๋ฑ? dto? ??  ? ??ก? ?๊ตกๅ ??  ็ญ๋พ๋ฒ้๏ฟ? ? ?๋ปปๅ ??? ?๋ฎ? ?๊ปํ๏ฟฝ๋ฑฝ RowMapper?ฅ?ฝ? ? ?๋ฆฝๅ ?๋ผ?.

					@Override // ? ??ญ๏ฟฝ๋ตณ๏ฟฝ๋? ? ?๋ฅดๅ ??ฒ rs , ็ญ๋ฅ? ?ถ?๋ฎ๏ฟฝ๋ฒ? ? ?๋ปฌๅ ?๋ฑ? ?ถ??? ?์ฃฌๅ ??? ?๋ฎๆถ?? rowNum
					public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
						Cart cart = new Cart();
						cart.setProductNo(rs.getInt("product_no"));
						cart.setProductName(rs.getString("product_name"));
						cart.setProductPrice(rs.getInt("product_price"));
						cart.setCartAmount(rs.getInt("cart_amount"));
						cart.setCartTotalPrice(rs.getString("cart_totalPrice"));
						return cart;
					} 
				}        
 			);
		return list;

	}

	public List<Cart> selectByPageNo(String loginID, int pageNo, int rowsPerPage)  {

		

		String sql = "select p.product_no, p.product_name, p.product_price,c.cart_amount, c.cart_totalprice from "
				+ " carts c, products p  where c.product_no = p.product_no  " + "and  c.member_id= ? limit ?, ?";


		List<Cart> list = jdbcTemplate.query(   //  query๏ฟฝ๋ตณ๏ฟฝ๋ช? ? ??? ?๋ฟ?? ?๋ต? list
				sql,
				new Object[] {loginID, (pageNo-1)*rowsPerPage , rowsPerPage},// ? ? ??พ ็ญ๋พ?ฆๅฏ๏ฟฝ ? ?๋ต? ๏ฟฝโค??๏ฟ? ?ถ?๏ฟฝใ๋ฑ? ? ??? ???
				new RowMapper<Cart>() { // sql? ?? ? ??ฃ ?ถ??? ?์ฃฌๅ ?๊ถ? ??ด?๏ฟฝ์ฅ? ?๋ฒ? ?ถ?๏ฟฝใ๋ฑ? dto? ??  ? ??ก? ?๊ตกๅ ??  ็ญ๋พ๋ฒ้๏ฟ? ? ?๋ปปๅ ??? ?๋ฎ? ?๊ปํ๏ฟฝ๋ฑฝ RowMapper?ฅ?ฝ? ? ?๋ฆฝๅ ?๋ผ?.

					@Override // ? ??ญ๏ฟฝ๋ตณ๏ฟฝ๋? ? ?๋ฅดๅ ??ฒ rs , ็ญ๋ฅ? ?ถ?๋ฎ๏ฟฝ๋ฒ? ? ?๋ปฌๅ ?๋ฑ? ?ถ??? ?์ฃฌๅ ??? ?๋ฎๆถ?? rowNum
					public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
						Cart cart = new Cart();
						cart.setProductNo(rs.getInt("product_no"));
						cart.setProductName(rs.getString("product_name"));
						cart.setProductPrice(rs.getInt("product_price"));
						cart.setCartAmount(rs.getInt("cart_amount"));
						cart.setCartTotalPrice(rs.getString("cart_totalPrice"));
						return cart;
					} // ? ?๋ปฌๅ ?๋ฑ? ?ถ??็ญ์?๏ฟฝโจ? ? ??? ??ฃ dto? ??  ? ?? ? ??ฏ?๊ป์ ? ??? ??ข? ?๋ง? ?๊ปํ๏ฟฝ๋ตฅ?ถ??.	
				}        
 			);
		return list;

	}

	public int delete(String loginID)  {
		String sql = "delete from carts where member_id= ? ";
		int rows = jdbcTemplate.update(
				sql,
				loginID
			);
		return rows;
	}
	
	public int selectCount(){
		String sql = "select count(*) from products";
		int rows = jdbcTemplate.queryForObject(sql, Integer.class);
		return rows;
	}
	
	

}
