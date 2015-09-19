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
				color:white;
				font-family:Play;
				margin: 20px;
				font-size: 30px;
			}
		
		</style>
	</head>
	<body>
		<c:if test="${ck=='success' }">
			<h4>SUCCESS! THANK YOU ^_^ </h4>
		</c:if>
		
		<c:if test="${ck=='fail' }">
			<h4>ANOTHER ID PLEASE T_T </h4>
		</c:if>
	</body>
</html>