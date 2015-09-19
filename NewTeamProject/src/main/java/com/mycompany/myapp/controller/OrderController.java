package com.mycompany.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	//占쏙옙占쏙옙占쏙옙 session占쎈��占썩�븝옙��占쏙옙 筌�占쏙옙�쇽옙占� 占쏙옙疫뀐옙!!
	String tempID= "t";
	
	@RequestMapping("/order/list")
	public String list(@RequestParam(defaultValue="1") int pageNo, Model model,HttpSession session){
		
		
		//session占쎌�곤옙 get占쎈��占쏙옙 占쏙옙占쎈��占쏙옙 揶�占쏙옙紐�占썬�븍┛!!
		
		List<Order> list= orderService.getOrder((String)session.getAttribute("memberId"));
		model.addAttribute("list", list);
		return "order/list";
	}
	
	@RequestMapping("/order/detail")
	public String detail(int orderNo, Model model,HttpSession session){
		
		
		
		//session占쎌�곤옙 占쏙옙占쎈��占쏙옙 揶�占쏙옙紐�占싼�占� 
		
		
		System.out.println("orderController占쏙옙 detail占썬�쇽옙��占쏙옙");
		List<OrderItem> list = orderItemService.getOrderItems((String)session.getAttribute("memberId"), orderNo);
		model.addAttribute("list", list);
		System.out.println("orderController占쏙옙 detail占쏙옙占쏙옙");
		return "order/detail";
		
	}
	
}
