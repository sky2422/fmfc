<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>주문번호에 대한 주문상세정보</title>
</head>
<body>
	<section>
		<c:if test="${myOrderDetailList ne null && myOrderDetailList.size() > 0}">
			<table>
				<tr>
					<th colspan="4">
						<h2>주문번호에 대한 [상품 주문 상세 내역]</h2>
					</th>
				</tr>
				<tr>
					<th colspan="4">
						<h2>주문번호 : ${myOrderDetailList.get(0).order_num}</h2>
					</th>
				</tr>
				<tr>
					<th>메뉴 번호</th>
					<th>메뉴 명</th>
					<th>주문 수량</th>
					<th>메뉴 금액</th>
				</tr>
				<tr>
					<c:forEach var="orderDetail" items="${myOrderDetailList}" varStatus="status">
						<td>${status.count}</td>
						<td>${orderDetail.p_name}</td>
						<td>${orderDetail.quantity}</td>
						<td>${orderDetail.p_price * orderDetail.quantity}원</td>
					
					<%-- 오류 발생하면 주석삭제!! --%>
					<%-- <c:if test="${(status.index+1 mod 1) eq 0}"> --%>	
					<c:if test="${(status.count mod 1) eq 0}">
						</tr>
						<tr>
					</c:if>
					
					</c:forEach>
					
				</tr>
			</table>
			
			<c:choose>
				<c:when test="${productTotalMoney < 50000}">
				<section>
					상품금액 : ${productTotalMoney}원 <br/>
					배송비 :  ${saleTotalMoney}원<br/>
					결제금액 : ${productTotalMoney+saleTotalMoney}원 <br/>
				</section>
				</c:when>
				<c:otherwise>
					<section>
					결제금액 : ${totalMoney}원 <br/>
					</section>
				</c:otherwise>
			</c:choose>
			
			
			
		</c:if>
		
		<c:if test="${myOrderDetailList eq null}">
			<div>주문번호에 대한 주문내역 정보가 없습니다.</div>
		</c:if>
	</section>
	
	<hr/>
</body>
</html>