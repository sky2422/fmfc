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
	<section>
		<c:if test="${orderDetailList ne null && orderDetailList.size() > 0}">
			<h3>
				주문번호 : ${orderDetailList.get(0). order_num}
			</h3>
			
			<table>
				<tr>
					<th>상품 번호</th>
					<th>상품 코드</th>
					<th>상품 명</th>
					<th>주문 수량</th>
				</tr>
				
				<tr>
				<c:forEach var="orderDetail" items="${orderDetailList}" varStatus="status">
					<td>${status.index+1}</td>
					<td>${orderDetail.p_code}</td>
					<td>${orderDetail.p_name}</td>
					<td>${orderDetail.quantity}</td>
					
					<c:if test="${ ((status.index+1) mod 1) == 0 }">
						</tr>
						<tr>
					</c:if>
				</c:forEach>
				</tr>
			</table>
			
			<br><br><br>
			
			<h3>고객 정보</h3>
				<table>
					<tr>
						<th>고객 ID</th>
						<th>고객 이름</th>
						<th>고객 연락처</th>
						<th>고객 이메일</th>
						<th>고객 주소</th>
					</tr>
					
					<tr>
						<td>${customerInfo.c_id}</td>
						<td>${customerInfo.c_name}</td>
						<td>${customerInfo.c_call}</td>
						<td>${customerInfo.c_email}</td>
						
						<td>
						${customerAddrInfo.address1} &nbsp;
						${customerAddrInfo.address2} &nbsp;
						${customerAddrInfo.postcode} &nbsp;
						</td>
					</tr>
				</table>
		</c:if>
		
		<c:if test="${orderDetailList eq null}">
			주문 상세 정보가 없습니다.
		</c:if>
		
	</section>
</body>
</html>