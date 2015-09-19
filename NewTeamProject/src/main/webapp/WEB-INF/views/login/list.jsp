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
			
			#new{
				margin-top: 10px;
			}
		
		</style>
	</head>
	<body>
	
		<c:if test="${memberId == null }">
			<form method="post" action="check" >
				<h4>아이디:<input type="text" name="memberID" size="15" ></h4>
				<h4>비밀번호:<input type="password" name="memberPW" size="15" ></h4>
				<input type="submit" value="로그인">
			</form>
			<div id="new">
				<a href="newmember"><button>회원가입</button></a>
			</div>
		</c:if>
		
		<c:if test="${memberId != null }">
			${memberId}님이<h4>로그인 중입니다.</h4>
			<div>
				<a href="logout"><button>로그아웃</button></a>
			</div>
		</c:if>
	</body>
</html>