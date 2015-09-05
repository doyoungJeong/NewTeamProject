package com.mycompany.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.myapp.dto.Order;
import com.mycompany.myapp.dto.OrderItem;
import com.mycompany.myapp.service.OrderItemService;
import com.mycompany.myapp.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	//아디디 session이용해서 만들어 놓기!!
	String tempID= "t";
	
	@RequestMapping("/order/list")
	public String list(@RequestParam(defaultValue="1") int pageNo, Model model){
		
		
		//session으로 get해서 아이디 가져오기!!
		
		List<Order> list= orderService.getOrder(tempID);
		model.addAttribute("list", list);
		return "order/list";
	}
	
	@RequestMapping("/order/detail")
	public String detail(int orderNo, Model model){
		
		
		
		//session으로 아이디 가져올것 
		
		
		System.out.println("orderController의 detail들어옴");
		List<OrderItem> list = orderItemService.getOrderItems(tempID, orderNo);
		model.addAttribute("list", list);
		System.out.println("orderController의 detail끝남");
		return "order/detail";
		
	}
	
}
