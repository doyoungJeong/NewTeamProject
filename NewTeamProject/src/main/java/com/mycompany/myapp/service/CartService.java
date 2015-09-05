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
	
	//카트 보기
	public List<Cart>  getPage(String loginID, int pageNo, int rowsPerPage) {
		List<Cart> list = cartDao.selectByPageNo(loginID, pageNo, rowsPerPage);
		return list;
	}
}
