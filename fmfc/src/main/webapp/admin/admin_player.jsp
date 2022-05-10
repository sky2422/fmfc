<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>선수</title>
</head>
<body>
	<div>
		<a href ="${pageContext.request.contextPath}/playerAdminList.adm">선수 관리</a>
		|
		<a href ="${pageContext.request.contextPath}/playerRegistForm.adm">선수 추가</a>
	</div>
</body>
</html>