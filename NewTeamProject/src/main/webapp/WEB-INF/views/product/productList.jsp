<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset=" UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			h4 {
				color: white;
			}
			table {
				border-collapse: collapse;
				width:100%;
				font-size: small;
			}
			table, th, td {
				border: 1px solid white;
				text-align: center;
			}
			td {
				color: white;
			}
			th {
				background-color: aqua;		
			}
			
			#buttonGroup {
				text-align: center;
				margin: 10px;
			}
			#pager {
				margin-top: 5px;
				text-align: center;
				font-size: small;
				color: white;				
			}
			
			#pager a {
				color: white;
				text-decoration: none;
			}
			
			#pager a:hover {
				color: green;
			}
			
			#pager a.pageNo {
				margin-left: 5px;
				margin-right: 5px;
			}
			
			#pager a.pageNo.selected {
				color: aqua;	
			}
			.title {
				text-decoration: none;
				color: white;
			}
			.title:HOVER {
				color: orange;
			}		
		</style>
	</head>
	<body>
		<h4>게시물 목록</h4>
		<table>
			<tr>
				<th style="width:50px">품번</th>
				<th >상품이름</th>
				<th style="width:60px">사이즈</th>
				<th style="width:60px">색상</th>
				<th style="width:60px">가격</th>
			</tr>
			
			<c:forEach var="product" items="${list}">
				<tr>
					<td>${product.productNo}</td>
					<td><a class="title" href="product_detail?productNo=${product.productNo}">${product.name}</a></td>
					<td>${product.size}</td>
					<td>${product.color}</td>
					<td>${product.price}</td>
				</tr>	
			</c:forEach>
		</table>
		
		<div id="pager">
		
			<a href="productList?pageNo=1">[처음]</a>
			
			<c:if test="${groupNo>1}">
				<a href="productList?pageNo=${startPageNo-pagesPerGroup}">[이전]</a>
			</c:if>
				 
				 
			<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}"> 
				<a class="pageNo <c:if test="${pageNo==i}">selected</c:if>" href="productList?pageNo=${i}">${i}</a>
			</c:forEach>
			
			<c:if test="${groupNo<totalGroupNo}">
				<a href="productList?pageNo=${endPageNo + 1}">[다음]</a>
			</c:if>
			
			<a href="productList?pageNo=${totalPageNo}">[맨끝]</a> 
		</div>
		
		<div id="buttonGroup">
			<a href="product_write_form">
				글쓰기
			</a>
		</div>
	</body>
</html>