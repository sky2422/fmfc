<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>고객 정보 리스트</title>
</head>
<body>

		<h3>고객 정보</h3>
				<table>
					<tr>
						<th>고객 아이디</th>
						<th>고객 이름</th>
						<th>고객 연락처</th>
						<th>고객 이메일</th>
						<th>고객 주소</th>
					</tr>
					<c:forEach var="customer" items="${customerListInfo}">
						<tr>
							<td>${customer.c_id}</td>
							<td>${customer.c_name}</td>
							<td>${customer.c_call}</td>
							<td>${customer.c_email}</td>
							<c:forEach var="customerAddr" items="${customerAddrListInfo }">
								<c:if test="${customer.c_id == customerAddr.c_id }">
									<td>
									${customerAddr.address1} &nbsp;
									${customerAddr.address2} &nbsp;
									${customerAddr.postcode} &nbsp;
									</td>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
</body>
</html>