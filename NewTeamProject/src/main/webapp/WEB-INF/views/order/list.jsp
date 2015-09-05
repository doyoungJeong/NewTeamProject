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
		<h4>주문 정보</h4>
		
		<table>
			<tr>
				<th style="width:50px">주문번호</th>
				<th>주문금액</th>
				<th style="width:150px">주문날짜</th>
				<th style="width:150px">주문자</th>
				
			</tr>
			
			<c:forEach var="order" items="${list}">
				<tr>
					<td>${order.orderNo}</td>
					<td><a class="orderItem"  href="detail?orderNo=${order.orderNo}">${order.orderPrice}</a></td>
					<td>${order.orderDate}</td>
					<td>${order.memberName}</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<div id="buttonGroup">
			<a >글쓰기</a>
		</div>
	</body>
</html>








