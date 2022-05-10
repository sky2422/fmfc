<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"name="viewport"
	content="width=device-width, initial-scale=1.0">
	
<title>사용자와 관리자의 주문 상태</title>

<script type="text/javascript">
	function window_onload(){
		//setInterval()함수를 이용해 일정시간 간격 (5초)을 두고 함수를 실행하는 방법
		setInterval('win_refresh()', 5000);// 5초 후 (1000=1초)
	}
	
	function win_refresh(){
		//[사용자모드]: '구매하기' 클릭하여 '실시간 주문'요청을 하면
		//[관리자모드]: '실시간 주문관리' 메뉴에서 order_status를 'order'->'get(주문승인)' 또는 'order'->'cancel(주문취소)'로 변경
		location.href="realtimeOrder.odr";
	}
	
</script>

</head>
<body onload="javascript:window_onload()">
	<c:if test="${sessionScope.latestOrder ne null }">
		주문번호: ${sessionScope.latestOrder.order_num }<br>
		주문시간: ${sessionScope.latestOrder.order_date }<br>
		
		<strong>
			주문상태:
				<c:choose>
					<c:when test="${sessionScope.latestOrder.order_status eq 'order' }">상품주문 대기상태</c:when>
					<c:when test="${sessionScope.latestOrder.order_status eq 'get' }">상품 주문완료</c:when>
					<c:otherwise>주문취소</c:otherwise>
				</c:choose>
		</strong>
	</c:if>
	
	<c:if test="${sessionScope.latestOrder eq null }">
		관리자가 상품 주문을 아직 확인하지 않았습니다.
	</c:if>
</body>
</html>