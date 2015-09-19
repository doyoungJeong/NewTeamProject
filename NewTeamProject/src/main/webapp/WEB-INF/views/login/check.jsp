<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset=UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			body{
				color: white;
			}

		</style>
	</head>
	<body>
		<c:if test="${result=='success'}">
			${memberID}님<h4>로그인 되었습니다.</h4>
			<div id="new">
				<a href="logout"><button>로그아웃</button></a>
			</div>
		</c:if>
		<c:if test="${result=='wrong Password' }">
			<h4>비밀번호가 틀렸습니다.</h4>
		</c:if>
		
		<c:if test="${result='wrong ID'}">
			<h4>아이디가 틀렸습니다.</h4>
		</c:if>
	</body>
</html>

