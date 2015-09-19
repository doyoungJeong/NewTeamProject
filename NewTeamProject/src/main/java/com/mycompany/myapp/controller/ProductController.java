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

import com.mycompany.myapp.dto.Product;
import com.mycompany.myapp.service.CartService;
import com.mycompany.myapp.service.ProductService;



@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	

	@Autowired
	private ProductService productService;
	private CartService cartService;
	
	@RequestMapping("/product/productList")
	public String list(@RequestParam(defaultValue="1") int pageNo, Model model, HttpSession session){
		logger.info("pageNo:"+pageNo);	
		
				int rowsPerPage = 10;
				int pagesPerGroup =5;
				
				int totalProductNo= productService.getTotalProductNo();
				
				//��泥� ���댁� �� 
				int totalPageNo = totalProductNo/rowsPerPage;
				if(totalProductNo%rowsPerPage!=0){
					totalPageNo ++;
				}
				
				int totalGroupNo = totalPageNo/pagesPerGroup;
				if(totalGroupNo%pagesPerGroup!=0){
					totalGroupNo ++;
				}
				
				int groupNo = (pageNo-1)/pagesPerGroup +1;
				int startPageNo = (groupNo-1)*pagesPerGroup +1;
				int endPageNo = startPageNo + pagesPerGroup-1;
				if(groupNo==totalGroupNo){
					endPageNo = totalPageNo;
				}
				
				List<Product> list =productService.getPage(pageNo,rowsPerPage);

				/*request.setAttribute("list", list);*/
				
				model.addAttribute("pagesPerGroup", pagesPerGroup);
				model.addAttribute("totalPageNo", totalPageNo);
				model.addAttribute("totalGroupNo", totalGroupNo);
				model.addAttribute("groupNo", groupNo);
				model.addAttribute("startPageNo", startPageNo);
				model.addAttribute("endPageNo", endPageNo);
				model.addAttribute("pageNo", pageNo);
				model.addAttribute("list", list);
				
				return "product/productList";
			}

	@RequestMapping("/product/product_detail")
	public String detail(int productNo, Model model){
		logger.info("detail()");
		Product product = productService.getProduct(productNo);
		model.addAttribute("product", product);
		return "product/product_detail";
	}
	
	@RequestMapping("/product/insertCart")
		public String insertCart(int productNo, Model model) {
		
			Product product = productService.getProduct(productNo);
//			Cart cart = 
			model.addAttribute("product", product);
					
			return "redirect:/product/showCart";
		}	
	
	
}
