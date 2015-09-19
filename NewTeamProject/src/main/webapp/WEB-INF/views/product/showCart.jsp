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
			#buttonGroup > a {
				text-decoration: none;
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
		<h4> cart 리스트 </h4>
		<table>
			<tr>
				<th style="width:50px">품번</th>
				<th >상품이름</th>
				<th style="width:60px">수량</th>
				<th style="width:60px">가격</th>
			</tr>
			
			<c:forEach var="cart" items="${list}">
				<tr>
					<td>${cart.productNo}</td>
					<td>${cart.productName}</td>
					<td>${cart.cartAmount}</td>
					<td>${cart.cartTotalPrice}</td>
				</tr>	
			</c:forEach>
		</table>
			
		<div id="buttonGroup">
			<a href="../order/result" >주문하기</a>
				
			</a>&nbsp;&nbsp;&nbsp;
			<a href="delete">카트 비우기</a>
		</div>
	</body>
</html>