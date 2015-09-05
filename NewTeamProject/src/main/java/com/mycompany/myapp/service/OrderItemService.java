package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.OrderItemDao;
import com.mycompany.myapp.dto.OrderItem;

@Component
public class OrderItemService {
	
	@Autowired
	private OrderItemDao orderItemDao;

	public List<OrderItem> getOrderItems(String loginID, int order_no) {
		System.out.println("orderItemService의 getOrderItems들어옴");
			List<OrderItem> list = orderItemDao.select(loginID, order_no);
			System.out.println("orderItemService의 getOrderItems끝남");
			return list;
	}
}
