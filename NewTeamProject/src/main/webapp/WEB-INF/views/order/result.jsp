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
	주문완료
	</body>
</html>