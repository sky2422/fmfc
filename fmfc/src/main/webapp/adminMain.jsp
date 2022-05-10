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
	<jsp:include page="adminHeader.jsp" />
	
	<section id = "section">
		<c:if test="${showAdmin ne null}">
		
			<div>
				<jsp:include page="${showAdmin}" />
				<c:choose>
					<c:when test="${a_id eq null and showAdmin eq null}">
						<jsp:include page="admin/adminLoginForm.jsp" />
					</c:when>
				</c:choose>
			</div>
		
		</c:if>
	</section>
	
	<jsp:include page="adminFooter.jsp" />
</div>






</body>
</html>