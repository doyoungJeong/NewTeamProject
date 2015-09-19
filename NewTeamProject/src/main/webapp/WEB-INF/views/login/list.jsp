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
			}
			
			h4{
				margin:30px;
			}
			#new{
				margin: 30px;
			}
			
			h1{
				margin: 30px;
			}
		
		</style>
	</head>
	<body>
	
		<c:if test="${memberId == null }">
			<form method="post" action="check" >
				<h4>ID:&nbsp&nbsp<input type="text" name="memberID" size="15" ></h4>
				<h4>PASSWORD:&nbsp&nbsp<input type="password" name="memberPW" size="15" ></h4>
				&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit" value="LOGIN">
			</form>
			<div id="new">
				<a href="newmember"><button>NEW</button></a>
			</div>
		</c:if>
		
		<c:if test="${memberId != null }">
			<h1>I AM &nbsp '${memberId}' -o-</h1>
			<div id="new">
				<a href="logout"><button>LOGOUT</button></a>
			</div>
		</c:if>
	</body>
</html>