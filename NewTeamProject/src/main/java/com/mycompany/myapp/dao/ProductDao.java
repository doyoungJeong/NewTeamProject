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

import com.mycompany.myapp.dto.Product;

public class ProductDao {
	private Connection conn;
	
	public ProductDao(Connection conn){
		this.conn =conn;
	}
	
	public List<Product> selectByAll() throws SQLException{
	      List<Product> list = new ArrayList<Product>( );
	      String sql = "select * from products order by product_no asc";
	      
	      PreparedStatement pstmt =  conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()) {
	            Product product = new Product();
	            product.setProductNo( rs.getInt("product_no") );
	            product.setProductName( rs.getString("product_name") );
	            product.setProductPrice( rs.getInt("product_price") );
	            product.setProductInfo( rs.getString("product_info") );
	            list.add(product);
	         }
	         rs.close();
			pstmt.close();
			return list;
	         
	}
}
