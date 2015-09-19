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

		</style>
	</head>
	<body>
		<c:if test="${result=='success'}">
			<h4>HELLO ^3^  </h4> ${memberId}~
			<div>
				<a href="logout"><button>LOGOUT</button></a>
			</div>
		</c:if>
		
		<c:if test="${result=='wrong Password'}">
			<h4>WRONG PASSWORD T_T </h4>
		</c:if>
		
		<c:if test="${result=='wrong ID'}">
			<h4>WRONG ID T_T </h4>
		</c:if>
	</body>
</html>

