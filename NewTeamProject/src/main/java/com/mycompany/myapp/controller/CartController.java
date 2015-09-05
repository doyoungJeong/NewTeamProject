package com.mycompany.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.service.CartService;


@Controller
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	
		@Autowired
		private CartService cartService;
		
		
		@RequestMapping("/product/showCart")
		public String showCart(Model model){
			List<Cart> list = cartService.getCart("r");
			model.addAttribute("list", list);
			return "product/showCart";
		}
		
		@RequestMapping("/product/cartList")	
		public String cartList(@RequestParam(defaultValue = "1") String loginID, int pageNo, Model model, HttpSession session) {
			
			session.setAttribute("pageNo", pageNo);
			
			int rowsPerPage = 10;
			int pagesPerGroup = 5;

			int totalBoardNo = cartService.getTotalproductNo();
		
			int totalPageNo = totalBoardNo / rowsPerPage;
			if (totalBoardNo % rowsPerPage != 0) {
				totalPageNo++;
			} 

			int totalGroupNo = totalPageNo / pagesPerGroup;
			if (totalPageNo % pagesPerGroup != 0) {
				totalGroupNo++;
			}
		
			int groupNo = (pageNo - 1) / pagesPerGroup + 1;
			int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
			int endPageNo = startPageNo + pagesPerGroup - 1;
			if (groupNo == totalGroupNo) {
				endPageNo = totalPageNo;
			}
			
			List<Cart> list = cartService.getPage(loginID, pageNo, rowsPerPage);

			
			model.addAttribute("pagesPerGroup", pagesPerGroup);
			model.addAttribute("totalPageNo", totalPageNo);
			model.addAttribute("totalGroupNo", totalGroupNo);
			model.addAttribute("groupNo", groupNo);
			model.addAttribute("startPageNo", startPageNo);
			model.addAttribute("endPageNo", endPageNo);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("list", list);

			return "product/cartList";
		}
		
		
		
}
