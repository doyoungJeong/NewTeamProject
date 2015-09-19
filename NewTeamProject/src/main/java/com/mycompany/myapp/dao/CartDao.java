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
		Product product= jdbcTemplate.queryForObject(    // ?¶?†ë¹˜çŒ¿ï¿? ? ?ˆë¦?? ?„?Œç­Œë¾?Š£ï¿½ë±½ ? ?Œë§„å ?ˆë¦? query
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

		String sql = "select p.product_no, p.product_name, p.product_price,c.cart_amount, c.cart_totalprice from "
				+ " carts c, products p  where c.product_no = p.product_no  " + "and  c.member_id= ? ";
		
		List<Cart> list = jdbcTemplate.query(   //  queryï¿½ëµ³ï¿½ë—ª?‰˜ ? ?™?˜™? ?Œë¿?? ?Œëµ? list
				sql,
				new Object[] {loginID},// ? ? ?ˆ?•¾ ç­Œë¾?Š¦å¯ƒï¿½ ? ?Œëµ? ï¿½â‘¤?ƒ?‚‰ï¿? ?¶?…ï¿½ã‚Œë±? ? ?„?Œ? ?ˆ??
				new RowMapper<Cart>() { // sql? ?ˆ?“ ? ?„?£ ?¶?‰?˜™? ?Œì£¬å ?Œê¶? ?‡?‚´?ˆ–ï¿½ì“¥? ?Œë²? ?¶?…ï¿½ã‚Œë±? dto? ?ˆ?“  ? ?ˆ?ˆ¡? ?ˆêµ¡å ?ˆ?“  ç­Œë¾ë²é‡‰ï¿? ? ?ˆë»»å ?„?…•? ?ˆë®? ?‡ê»‰í”ï¿½ë±½ RowMapper?š¥?ˆ½?˜™ ? ?ˆë¦½å ?ˆë¼?.
				
					@Override // ? ?Œ?’­ï¿½ëµ³ï¿½ëŒ?˜™ ? ?ˆë¥´å ?ˆ?² rs , ç­Œë¥…?˜™ ?¶?†ë®‡ï¿½ë²? ? ?ˆë»¬å ?Œë±? ?¶?‰?˜™? ?Œì£¬å ?Œ?„…? ?ˆë®‰æ¶?‰?˜™ rowNum
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


		List<Cart> list = jdbcTemplate.query(   //  queryï¿½ëµ³ï¿½ë—ª?‰˜ ? ?™?˜™? ?Œë¿?? ?Œëµ? list
				sql,
				new Object[] {loginID, (pageNo-1)*rowsPerPage , rowsPerPage},// ? ? ?ˆ?•¾ ç­Œë¾?Š¦å¯ƒï¿½ ? ?Œëµ? ï¿½â‘¤?ƒ?‚‰ï¿? ?¶?…ï¿½ã‚Œë±? ? ?„?Œ? ?ˆ??
				new RowMapper<Cart>() { // sql? ?ˆ?“ ? ?„?£ ?¶?‰?˜™? ?Œì£¬å ?Œê¶? ?‡?‚´?ˆ–ï¿½ì“¥? ?Œë²? ?¶?…ï¿½ã‚Œë±? dto? ?ˆ?“  ? ?ˆ?ˆ¡? ?ˆêµ¡å ?ˆ?“  ç­Œë¾ë²é‡‰ï¿? ? ?ˆë»»å ?„?…•? ?ˆë®? ?‡ê»‰í”ï¿½ë±½ RowMapper?š¥?ˆ½?˜™ ? ?ˆë¦½å ?ˆë¼?.

					@Override // ? ?Œ?’­ï¿½ëµ³ï¿½ëŒ?˜™ ? ?ˆë¥´å ?ˆ?² rs , ç­Œë¥…?˜™ ?¶?†ë®‡ï¿½ë²? ? ?ˆë»¬å ?Œë±? ?¶?‰?˜™? ?Œì£¬å ?Œ?„…? ?ˆë®‰æ¶?‰?˜™ rowNum
					public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
						Cart cart = new Cart();
						cart.setProductNo(rs.getInt("product_no"));
						cart.setProductName(rs.getString("product_name"));
						cart.setProductPrice(rs.getInt("product_price"));
						cart.setCartAmount(rs.getInt("cart_amount"));
						cart.setCartTotalPrice(rs.getString("cart_totalPrice"));
						return cart;
					} // ? ?ˆë»¬å ?Œë±? ?¶?‰?˜™ç­Œìš‘?˜™ï¿½â‘¨?˜™ ? ?™?˜™? ?„?£ dto? ?ˆ?“  ? ?ˆ?„ ? ?ˆ?„¯?‡ê»“ì˜™ ? ?™?˜™? ?Œ?‚¢? ?ˆë§? ?‡ê»‰í”ï¿½ëµ¥?¶?‰?˜™.	
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
