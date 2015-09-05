package com.mycompany.myapp.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.myapp.dto.Product;
import com.mycompany.myapp.service.ProductService;



@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/product/list")
	public String list(@RequestParam(defaultValue="1") int pageNo, Model model, HttpSession session){
		logger.info("pageNo:"+pageNo);	
		
		//페이징을 위한 변수 선언
				int rowsPerPage = 10;
				int pagesPerGroup =5;
				
				//전체 게시물 수 
				int totalProductNo= productService.getTotalProductNo();
				
				//전체 페이지 수 
				int totalPageNo = totalProductNo/rowsPerPage;
				if(totalProductNo%rowsPerPage!=0){
					totalPageNo ++;
				}
				
				//전체 그룹 수
				int totalGroupNo = totalPageNo/pagesPerGroup;
				if(totalGroupNo%pagesPerGroup!=0){
					totalGroupNo ++;
				}
				
				//현재 그룹 번호, 시작페이지 번호, 끝 페이지 번호	
				int groupNo = (pageNo-1)/pagesPerGroup +1;
				int startPageNo = (groupNo-1)*pagesPerGroup +1;
				int endPageNo = startPageNo + pagesPerGroup-1;
				if(groupNo==totalGroupNo){
					endPageNo = totalPageNo;
				}
				
				//현재 페이지 게시물 리스트
				List<Product> list =productService.getPage(pageNo,rowsPerPage);
				//이 list를 어떻게 해야 jsp로 보낼 수 있을 까? request로 해주자
				/*request.setAttribute("list", list);*/
				
				
				//View로 넘길 데이터
				//꼭 편리할 때만 request를 쓰고 주로 model을 사용함
				model.addAttribute("pagesPerGroup", pagesPerGroup);
				model.addAttribute("totalPageNo", totalPageNo);
				model.addAttribute("totalGroupNo", totalGroupNo);
				model.addAttribute("groupNo", groupNo);
				model.addAttribute("startPageNo", startPageNo);
				model.addAttribute("endPageNo", endPageNo);
				model.addAttribute("pageNo", pageNo);
				model.addAttribute("list", list);
				
				return "product/list";
			}

	@RequestMapping("/product/detail")
	public String detail(int productNo, Model model){
		logger.info("detail()");
		Product product = productService.getProduct(productNo);
		model.addAttribute("product", product);
		return "product/detail";
	}

}
