<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			body {
				color: white;
			}
			table {
				width: 100%;
				border-collapse: collapse;
				font-size: small;
			}
			table, th, td {
				border: 1px solid white;
				text-align: center;
				height:25px;
			}
			th {
				background-color: gray;
				color:black;
			}
			
			#buttonGroup {
				margin: 10px;
				text-align: center;
			}
			
			#buttonGroup a {
				display:inline-block;
				width: 70px;
				line-height: 30px;
				text-decoration: none;
				font-size: small;
				color: white;
				border: 1px solid darkgray;
				background-color: gray;
				font-weight: bold;
			}
			
			#buttonGroup a:hover {
				color: black;
				background-color: lightgray;
			}
			#pager {
				margin-top: 5px;
				text-align: center;
				font-size: small;
			}
			#pager a{
				text-decoration:none;
				color: white;

			}
			
			#pager a:hover{
				color:gray;
			}
			
			#pager a.pageNo{
				margin-left:5px;
				margin-right:5px;
			}
			
			#pager a.pageNo.selected{
				color:darkgray;
			}
			
			.title{
				text-decoration:none;
				color:white;
			}
			
			.title:hover{
				color:gray;
			}
			
		</style>
	</head>
	
	<body>
		<h4>주문상세정보</h4>
		
		<table>
			<tr>
				<th style="width:50px">상품번호</th>
				<th>상품명</th>
				<th style="width:60px">수량</th>
				<th style="width:80px">가격</th>
				
			</tr>
			
			<c:forEach var="orderItem" items="${list}">
				<tr>
					<td>${orderItem.productNo}</td>
					<td>${orderItem.orderItemName}</td>
					<td>${orderItem.orderItemAmount}</td>
					<td>${orderItem.orderItemPrice}</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<div id="buttonGroup">
			<a >글쓰기</a>
		</div>
	</body>
</html>








