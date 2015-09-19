package com.mycompany.myapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.mycompany.myapp.dao.*;
import com.mycompany.myapp.dto.*;

@Component
public class CartService {
	
	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrderDao orderDao;

	//카트에 상품 추가
	public void cart_insert(Product product, String loginID, Cart cart){
		cartDao.insert(product, loginID, cart);
	}
	
	//주문하기
	public void order(String loginID, List<Cart> cart){
		orderDao.insert(cart, loginID);
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
		List<Cart> list = cartDao.select(loginID);
		return list;
	}
	
	//카트에 총 상품 수 구하기
	public int getTotalproductNo() {
		int rows = cartDao.selectCount();
		return rows;
	}
	
	
	
}
