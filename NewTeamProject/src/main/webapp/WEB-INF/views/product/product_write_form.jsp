<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset=" UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
		table {
			color: white;
		}
		</style>

	</head>
	<body>
		<form method="POST"  action="write" enctype="multipart/form-data">
		<table id="form_table">
			<tr>
				<td>상품이름</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>색상</td>
				<td><input type="text" name="color" /></td>
			</tr>
			<tr>
				<td>사이즈</td>
				<td><input type="text" name="size" /></td>
			</tr>
				<tr>
				<td>수량</td>
				<td><input type="text" name="amount" /></td>
			</tr>
			<tr>
				<td>상품설명</td>
				<td><textarea name="description" rows="5" cols="50"></textarea></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price" /></td>
			</tr>
			<tr>
				<td>첨부</td>
				<td><input type="file"  name="attach" /></td>
			</tr>
		</table>
		<input type="submit" value="상품등록" />
		<input type="reset" value="다시작성" />
		</form>
	</body>
</html>



