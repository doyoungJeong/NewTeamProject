<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset=UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			body{
				color:white;
			}
		
		</style>
	</head>
	<body>
		<c:if test="${ck=='success' }">
			<h4>회원가입이 성공했습니다.</h4>
		</c:if>
		
		<c:if test="${ck=='fail' }">
			<h4>중복되는 아이디가 있습니다. 다른 아이디로 가입해주세요.</h4>
		</c:if>
	</body>
</html>