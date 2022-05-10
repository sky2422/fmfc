<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
     
<%
String cookidId = "";//★★ null로 초기화 하면 안됨

Cookie[] cookies = request.getCookies();
if(cookies != null && cookies.length > 0){
	for(int i=0; i<cookies.length; i++){
		if(cookies[i].getName().equals("a_id")){
			cookidId = cookies[i].getValue();//쿠키값(=사용자 아이디)을 얻어와
			break;
		}
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 로그인 폼 페이지</title>
<style>
#loginformArea {
   width: 640px;
   border: 1px gray solid;
   margin: auto;
   
}

h2 {
   text-align: center;
}

table {
   width: 550px;
   margin: auto;
}
</style>
</head>
<body>
<section id = "loginformArea">
	<form action="adminLoginAction.adm" method="post" name="loginform">
		<input type ="hidden" name="c_grade">
		<table>
			<tr>
				<th colspan="2">
					<h1>로그인</h1>
				</th>
			</tr>
			
			<tr>
				<th>아이디</th>
				<td><input type="text" name ="c_id" value="<%=cookidId%>" size="30" placeholder="아이디입력(필수)"/></td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name ="c_password" value="" size="31" placeholder="비밀번호입력(필수)"/></td>
			</tr>
			
			<tr>
				<th colspan="2">
					<input type="checkbox" name="remember" />아이디 저장
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="로그인" />
				</th>
			</tr>
			
			<tr>
				<th colspan="2">
					<a href = "adminIdFindForm.adm">[아이디 찾기]</a>
					<a href = "adminHashPwFindForm.adm">[비밀번호 찾기]</a>
				</th>
			</tr>
		</table>
	</form>
</section>
</body>
</html>