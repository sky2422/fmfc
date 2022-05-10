<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>상품</title>
</head>
<body>
	<div>
		<a href ="${pageContext.request.contextPath}/productAdminList.adm">상품 관리</a>
		|
		<a href ="${pageContext.request.contextPath}/productAddForm.adm">상품 추가</a>
	</div>
</body>
</html>