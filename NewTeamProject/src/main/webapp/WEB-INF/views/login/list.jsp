<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset=UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			h4{
				color: white;
			}
		
		</style>
	</head>
	<body>
		<form method="post" action="check" >
			<h4>아이디:<input type="text" name="memberID" size="15" ></h4>
			<h4>비밀번호:<input type="password" name="memberPW" size="15" ></h4>
			<input type="submit" value="로그인">
		</form>
	</body>
</html>