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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Product;

@Component
public class ProductDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Product> selectByPage(int pageNo, int rowsPerPage) {
	   
	      String sql = "select * from products order by product_no asc limit ?,?";
	      
	      List<Product> list = jdbcTemplate.query(
					sql,
					new Object[ ]{ (pageNo-1)*rowsPerPage, rowsPerPage },
					new RowMapper<Product>(){
						@Override
						public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
							Product product = new Product();
				            product.setProductNo( rs.getInt("product_no") );
				            product.setProductName( rs.getString("product_name") );
				            product.setProductPrice( rs.getInt("product_price") );
				            product.setProductInfo( rs.getString("product_info") );
				            return product;
						}
					}				
				);
			return list;
		}
	public Product selectByPk (int productNo) {
		
		String sql = "select * from products where product_no=?";
		Product product = jdbcTemplate.queryForObject(
				sql, 
				new Object[]{productNo},
				new RowMapper<Product>(){

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
	
	public int selectCount(){
		String sql = "select count(*) from products";
		int rows = jdbcTemplate.queryForObject(sql, Integer.class);
				//자동 타입변환 (언박싱) 
		return rows;
	}
}