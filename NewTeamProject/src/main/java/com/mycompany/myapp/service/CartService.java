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

	//īƮ�� ��ǰ �߰�
	public void cart_insert(Product product, String loginID, Cart cart){
		cartDao.insert(product, loginID, cart);
	}
	
	//�ֹ��ϱ�
	public void order(String loginID, List<Cart> cart){
		orderDao.insert(cart, loginID);
	}
	
	//īƮ ����
	public void remove(String loginID) {
		cartDao.delete(loginID);
		}
	
	//īƮ ����¡
	public List<Cart>  getPage(String loginID, int pageNo, int rowsPerPage) {
		List<Cart> list = cartDao.selectByPageNo(loginID, pageNo, rowsPerPage);
		return list;
	}
	
	//īƮ �� �ҷ�����
	public List<Cart> getCart(String loginID){
		List<Cart> list = cartDao.select(loginID);
		return list;
	}
	
	//īƮ�� �� ��ǰ �� ���ϱ�
	public int getTotalproductNo() {
		int rows = cartDao.selectCount();
		return rows;
	}
	
	
	
}
