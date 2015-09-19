package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.CartDao;
import com.mycompany.myapp.dao.OrderDao;
import com.mycompany.myapp.dao.OrderItemDao;
import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.Product;

@Component
public class CartService {
	
	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderItemDao orderitemDao;

	//īƮ�� ��ǰ �߰�
	public void cart_insert(Product product, String loginID, Cart cart){
		cartDao.insert(product, loginID, cart);
	}
	
	//�ֹ��ϱ�
	public void order(String loginID, List<Cart> cart){
		Integer orderNo = orderDao.insert(cart, loginID);
		orderitemDao.insert(cart,loginID,orderNo);
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
