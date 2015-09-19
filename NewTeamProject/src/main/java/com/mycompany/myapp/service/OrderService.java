package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.CartDao;
import com.mycompany.myapp.dao.OrderDao;
import com.mycompany.myapp.dao.OrderItemDao;
import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.Order;

@Component
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	public void ordering(String loginID)  {
	
			List<Cart> list = cartDao.select(loginID);
			int order_no = orderDao.insert(list, loginID);
			orderItemDao.insert(list, loginID, order_no);
			cartDao.delete(loginID);

	}

	
	public List<Order> getOrder(String loginID) {
		System.out.println("getorder硫������ㅼ�댁��");
			List<Order> list = orderDao.select(loginID);
			System.out.println("getorde.select ��");
			return list;
	}
}
