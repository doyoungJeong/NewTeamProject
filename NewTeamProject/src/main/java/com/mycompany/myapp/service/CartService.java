package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.CartDao;
import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.Product;

@Component
public class CartService {
	
	@Autowired
	private CartDao cartDao;

	//카트에 상품 추가
	public void cart_insert(Product product, String loginID, Cart cart){
		cartDao.insert(product, loginID, cart);
	}
	
	//카트 비우기
	public void remove(String loginID) {
		cartDao.delete(loginID);
		}
	
	//카트 페이징
	public List<Cart>  getPage(String loginID, int pageNo, int rowsPerPage) {
		List<Cart> list = cartDao.selectByPageNo(loginID, pageNo, rowsPerPage);
		return list;
	}
	
	//카트 다 불러오기
	public List<Cart> getCart(String loginID){
		List<Cart> list = cartDao.select("r");
		return list;
	}
	
	//카트에 총 상품 수 구하기
	public int getTotalproductNo() {
		int rows = cartDao.selectCount();
		return rows;
	}
	
	
	
}
