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
		Product product= jdbcTemplate.queryForObject(    // 媛앹껜 �븯�굹留뚯쓣 �쐞�븳 query
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
		
		List<Cart> list = jdbcTemplate.query(   //  query由ы꽩 ���엯�씠 list
				sql,
				new Object[] {loginID},// ? �닔 留뚰겮 �씠 怨녹뿉 媛믪쓣 �굹�뿴
				new RowMapper<Cart>() { // sql�뿉�꽌 媛��졇�삩 移쇰읆�쓽 媛믪쓣 dto�뿉 �븘�뱶�뿉 留듯븨 �떆�궎�뒗 寃껋쓣 RowMapper濡� �븳�떎.

					@Override // �슦由ш� �븣�뜕 rs , 紐� 媛쒖쓽 �뻾�쓣 媛��졇�솕�뒗媛� rowNum
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


		List<Cart> list = jdbcTemplate.query(   //  query由ы꽩 ���엯�씠 list
				sql,
				new Object[] {loginID, (pageNo-1)*rowsPerPage , rowsPerPage},// ? �닔 留뚰겮 �씠 怨녹뿉 媛믪쓣 �굹�뿴
				new RowMapper<Cart>() { // sql�뿉�꽌 媛��졇�삩 移쇰읆�쓽 媛믪쓣 dto�뿉 �븘�뱶�뿉 留듯븨 �떆�궎�뒗 寃껋쓣 RowMapper濡� �븳�떎.

					@Override // �슦由ш� �븣�뜕 rs , 紐� 媛쒖쓽 �뻾�쓣 媛��졇�솕�뒗媛� rowNum
					public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
						Cart cart = new Cart();
						cart.setProductNo(rs.getInt("product_no"));
						cart.setProductName(rs.getString("product_name"));
						cart.setProductPrice(rs.getInt("product_price"));
						cart.setCartAmount(rs.getInt("cart_amount"));
						cart.setCartTotalPrice(rs.getString("cart_totalPrice"));
						return cart;
					} // �뻾�쓣 媛�吏�怨� ���꽌 dto�뿉 �뼱�뼸寃� ���옣�븷 寃껋씤媛�.	
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

}
