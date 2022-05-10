<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호 찾기</title>
</head>
<body>
<form action="customerHashPwFindAction.cus" method="post">
	<h2>비밀번호 찾기</h2>
	<table>
		<tr>
			<th>아이디</th>
				<td>
					<input type="text" name="c_id"  size = "30" maxlength="12" placeholder="8~12자 영문과 숫자조합을 입력하세요." required="required" />
				</td>
		</tr>
		
		<tr>
			<th>이메일</th>
				<td>
					<input type="text" name="c_email" size = "30" placeholder="ex)aaa@naver.com" required="required" />
				</td>
		</tr>
		
		<tr>
			<th colspan="2">
				<input type="submit" value="암호화된 비밀번호 찾기" />
			</th>
		</tr>
		
	</table>
</form>
</body>
</html>