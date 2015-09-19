
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<link href='https://fonts.googleapis.com/css?family=Orbitron' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Play' rel='stylesheet' type='text/css'>
		<title>Home</title>
		<style type="text/css">
		

			* {
				margin: 0px;
				padding: 0px;
			}
			/*------------------------------------*/
			body {
				width:960px;
				margin: 0px auto;
			
				color: #FFFFFF;

				background-image: url(resources/image/img.png);
				background-size: auto 100%;
			
			}	
			/*------------------------------------*/				
			#page {
				height: 100vh;
				display: flex;
				flex-direction: column;
			}
			/*------------------------------------*/			
			#header {
				margin: 20px 0px;
				left: 50px;
				font-family: Orbitron;
			}
			#content {
				border: 3px solid white;	
				border-radius: 10px;		
			}
			#content1 {
				height: 200px;
				display: flex;
			}
			#content2 {
				flex: 1;
				margin-top: 10px;
				min-height: 300px;
				display: flex;
			}
			#footer {
				margin: 20px 0px;
			}			
			/*------------------------------------*/	
			#content1 #login {
				width: 250px;
				border: 3px solid white;	
				border-radius: 10px;			
			}
			
			#content1 #frontImage {
				flex: 1;
				border: 3px solid white;
				border-radius: 10px;
				margin-left: 10px;
				overflow: hidden;
			}
			
			#content1 #frontImage img {
				width: 100%;
				
			}

			/*------------------------------------*/
			#content2 #menu {
				width: 250px;
				border: 3px solid white;	
				border-radius: 10px;
				overflow-y: auto;
			}
			
			#content2 #menu ul li{
				margin-top: 10px;
				padding-left: 5px;
			}
			
			#content2 #menu a {
				display:block;
				margin: 50px;
				text-decoration: none;
				font-size: 20px;
				color: white;
				font-family:Play;
			}
			
			#content2 #menu a:hover {
				color: gray;
			}
			
			#content2 #content {
				flex: 1;	
				margin-left: 10px;		
				position: relative;		
			}
			
			#content2 #content iframe {
				width: 100%;
				height: 100%;
				position: absolute;
				border: none;
			}
			
			img{
				height: 100%;
			}
		</style>
	</head>
	
	<body>
		<div id="page">
			<div id="header">
				<h1>Team4's Project</h1>
			</div>

			
			<div id="content2">
				<div id="menu">
					<ul>
						<li><a href="login/list" target="iframe">LOGIN</a></li>
						<li><a href="product/productList" target="iframe">PRODUCT</a></li>
						<li><a href="product/showCart" target="iframe">CART</a></li>
						<li><a href="order/list" target="iframe">ORDER</a></li>
					</ul>
				</div>
				<div id="content">
					<iframe name="iframe" height="100%" width="100%"></iframe>
				</div>
			</div>
			
			<div id="footer">
				<h5>Team4</h5>
			</div>
		</div>
	</body>
</html>

