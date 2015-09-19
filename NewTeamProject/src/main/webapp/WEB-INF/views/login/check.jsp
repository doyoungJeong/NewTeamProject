<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<link href='https://fonts.googleapis.com/css?family=Play' rel='stylesheet' type='text/css'>
		<meta charset=UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			body{
				color: white;
				font-family:Play;
			}
			h1{
				margin: 30px;
			}
			

		</style>
	</head>
	<body>
		<c:if test="${result=='success'}">
			<h1>HELLO ^3^ &nbsp&nbsp '${memberId}'~</h1>
			<div>
				&nbsp&nbsp&nbsp&nbsp&nbsp<a href="logout"><button>LOGOUT</button></a>
			</div>
		</c:if>
		
		<c:if test="${result=='wrong Password'}">
			<h1>WRONG PASSWORD T_T </h1>
		</c:if>
		
		<c:if test="${result=='wrong ID'}">
			<h1>WRONG ID T_T </h1>
		</c:if>
	</body>
</html>

