<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="customerHeader.jsp"/>
	</div>
	
	
	<c:if test="${board_showMenu ne null}">
		<div>
			<jsp:include page="${board_showMenu}" />
		</div>
	</c:if>
	
	<c:if test="${board_showMenu eq null}">
	<div>
	</div>
	</c:if>
	
	
	<div>
		<jsp:include page="customerFooter.jsp"/>
	</div>
	
</body>
</html>