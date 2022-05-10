<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>회원수정완료</title>
</head>
<body>
	<div>
	<%-- <h3>[${c_name}]회원님의 정보 수정이 완료되었습니다.</h3> --%>
	<h3>[${sessionScope.c_name}]회원님의 정보 수정이 완료되었습니다.</h3>
	<hr/>
	
	<a href="customerMain.jsp">[확인]</a>
	
	</div>
</body>
</html>