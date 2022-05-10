<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>사용자의 주문내역</title>
</head>
<body>
	<div>
		<jsp:include page="customerHeader.jsp" />
	</div>
	
	<section>
		<c:if test="${sessionScope.productList != null && sessionScope.productList.size() > 0 }">
			<h3>${sessionScope.c_name}님의 주문내역</h3>
			<form action="post">
				<table>
					<tr>
						<th>번호</th>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
					</tr>
					
					<c:forEach var="product" items="${sessionScope.productList}" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${product.p_name}</td>
						<td>${product.p_price}</td>
						<td>${product.quantity}</td>
					</tr>
					</c:forEach>
					
					<c:choose>
						<c:when test="${sessionScope.totalMoney < 50000}">
						<tr>
							<td colspan="4">합계 금액 : ${sessionScope.totalMoney}원</td>
						</tr>
						
						<tr>
							<td colspan="4">배송비 : +${sessionScope.saleTotalMoney}원</td>
						</tr>
						
						<tr>
							<td colspan="4">결제 금액 : ${sessionScope.totalMoney+sessionScope.saleTotalMoney}원</td>
						</tr>
						</c:when>
						<c:otherwise>
						<tr>
							<td colspan="4">합계 금액 : ${sessionScope.totalMoney}원</td>
						</tr>
						</c:otherwise>
					</c:choose>
					
					
					
					<tr>
						<td colspan="4">
							<jsp:include page="customer/orderStatus.jsp" />
						</td>
					</tr>
					
				</table>
			</form>
		</c:if>
	
		<c:if test="${sessionScope.productList == null }">
			<h3>${sessionScope.c_name}님의 주문내역이 없습니다.</h3>
		</c:if>
	
	</section>
	
	
	<hr/>
	
	<div>
		<jsp:include page="customerFooter.jsp" />
	</div>
</body>
</html>