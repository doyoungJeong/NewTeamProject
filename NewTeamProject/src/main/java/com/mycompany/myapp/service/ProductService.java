package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.ProductDao;
import com.mycompany.myapp.dto.Product;


//스프링이 boardService객체를 알아서 생성하게 한다.
@Component
public class ProductService {


	@Autowired
	private ProductDao productDao;
	
	
	public List<Product> getPage(int pageNo, int rowsPerPage) {
		List<Product> list = productDao.selectByPage(pageNo, rowsPerPage);
		return list;
	}
	
	public Product getProduct(int productNo) {
		Product product = productDao.selectByPk(productNo);
		return product;
	}
	
	

	public int getTotalProductNo(){
		int rows =productDao.selectCount();
		return rows;
	}

}

