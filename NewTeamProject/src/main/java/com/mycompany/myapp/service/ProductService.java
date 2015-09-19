package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.ProductDao;
import com.mycompany.myapp.dto.Product;


//占썬�쏙옙占쏙�占쏙옙占� boardService揶�占쏙㎗��占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쎄�占쏙옙野�占� 占쏙옙占쏙옙.
@Component
public class ProductService {


	@Autowired
	private ProductDao productDao;
	
	
	public List<Product> getPage(int pageNo, int rowsPerPage) {
		List<Product> list = productDao.selectByPage(pageNo, rowsPerPage);
		return list;
	}
	
	public Product getProduct(int productNo) {
		System.out.println("getProduct�ㅼ�댁��");
		Product product = productDao.selectByPk(productNo);
		System.out.println("getProduct���� selectpk��");
		return product;
	}
	
	

	public int getTotalProductNo(){
		int rows =productDao.selectCount();
		return rows;
	}

}

