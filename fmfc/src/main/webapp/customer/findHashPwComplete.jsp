<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호 찾기 결과</title>
</head>
<body>
	<h3>고객님의 임시 비밀번호는 ${ramdom_password}입니다.</h3>
	<a href="customerLogin.cus">[로그인]</a>
	<%-- <a href="customerHashPwChangeForm.cus?c_id=<%=request.getAttribute("c_id")%>">[암호화된 비밀번호 변경]</a> --%>
	<a href="customerHashPwChangeForm.cus?c_id=${c_id}">[암호화된 비밀번호 변경]</a>
</body>
</html>