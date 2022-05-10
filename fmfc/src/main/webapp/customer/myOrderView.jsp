<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>사용자의 전체 주문 내역 보기</title>
</head>
<body>

	<section>
		<c:if test="${myOrderList ne null && myOrderList.size() > 0}">
			<table>
				<tr>
					<th colspan="4">
						<h3>나의 주문 내역</h3>
						주문번호를 클릭하면 '주문 금액 상세 정보'를 확인하실 수 있습니다.
					</th>
				</tr>
				
				<tr>
					<th>상품주문번호</th>
					<th>회원 아이디</th>
					<th>회원 이메일</th>
					<th>주문시간</th>
					<th>주문상태</th>
				</tr>
				<tr>
				<c:forEach var="order" items="${myOrderList}" varStatus="status">
					<td> <!-- totalMoney 전송이유? totalMoney에는 할인된 가격이 저장. 그래서, 그 다음 뷰 페이지 myOrderDetailView.jsp에 출력-->
						<a href="myOrderDetail.odr?order_num=${order.order_num}&totalMoney=${order.totalMoney}">
						${order.order_num}
						</a>
					</td>
					<td>${order.c_id}</td>
					<td>${order.c_email}</td>
					<td>${order.order_date}</td>
					
					
					<td>
						<c:choose>
							<c:when test="${order.order_status eq 'order'}">주문대기</c:when>
							<c:when test="${order.order_status eq 'get'}">주문완료</c:when>
							<c:otherwise>주문취소</c:otherwise>
						</c:choose>
					</td>
					
					
				
				<c:if test="${((status.index+1) mod 1) eq 0}">
					</tr>
					<tr>
				</c:if>
				
				</c:forEach>
				</tr>
			</table>
		</c:if>
		
		<c:if test="${myOrderList eq null}">
			<div>주문한 상품이 없습니다.</div>
		</c:if>
   </section>
</body>
</html>