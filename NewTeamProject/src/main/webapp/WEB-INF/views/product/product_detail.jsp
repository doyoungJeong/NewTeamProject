<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			body {
				height: 500px;
				font-size: "12px";
				color: white;
			}
			/* @font-face {
   					font-family: MANIFESTO;
   					src: url(MANIFESTO.ttf);
					} */
			span {
				display: inline-block;
				margin: 2px 10px;
			}
			span.title {
				margin: 2px 10px;
				border: 1px solid darkgray;
				background: #B2CCFF;
				width: 70px;
				text-align: center;
			}
			pre {
				margin: 10px;
				border: 1px solid darkgray;
				padding: 10px;
				width: 90%;
				height: 100px;
				font-sixe:12px;
			}
			
			#part1 {
				display: flex;
			}
			#part1_1 {
				flex: 1;
			}
			#part1_2 {
				width: 120px;
				text-align: center;
			
			}
			#part1_2 img {
				display: block;
				padding: 10px;		
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
			
		</style>
	</head>
	
	<body>
		<h4>게시물보기</h4>
		<div id="part1">
			<div id="part1_1">   <!-- el은 결국 get메소드를 호출하는 것이다. -->
				<span class="title">상품이름:</span> <span class="content"></span>${product.name}<br/>
				<span class="title">색상:</span> <span class="content"></span>${product.color}<br/>
				<span class="title">사이즈:</span> <span class="content"></span>${product.size}<br/>
				<span class="title">수량:</span> <span class="content"></span>${product.amount}<br/>
				<span class="title">상품설명: </span> <span class="content"></span>${product.description}<br/>
				<span class="title">가격: </span> <span class="content"></span>${product.price}<br/>
			</div>
			
			 <div id="part1_2">
			<!-- 동적으로 context의 이름을 받아내는 것이다. -->
				<img src="${pageContext.request.contextPath}/resources/uploadfiles/${product.filesystemName}" 
				width="100px" height="100px" />
				<button>다운로드</button>
			</div> 
		</div>
		
		
		<div id= "buttonGroup" >
			<a href="productList">목록</a>
			<a href="updateForm?boardNo=${product.productNo}">수정</a>
			<a href="delete?boardNo=${product.productNo}">삭제</a>
		</div>
	</body>
</html>