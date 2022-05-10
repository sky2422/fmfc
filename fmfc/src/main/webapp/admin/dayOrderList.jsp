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
	<!-- 1. ************************************************************* -->
	<c:if test="${dayOrderList ne null && dayOrderList.size() > 0}">
	
	<!-- 1-1. [주문 대기] ******************************************************** -->
		<div>
			<h3>상품 주문 대기</h3>
		</div>
		<table>
			<c:set var="doneLoop" value="false" />
			<c:set var="done" value="false" />
				<c:forEach var="order" items="${dayOrderList}" varStatus="status">
				<%-- varStatus="반복 상태를 알 수 있는 변수 " - index : item에서 정의한 항목을 가르키는 index번호, 0부터 시작
													 - count : 몇 번째 반복인지를 나타냄. 1부터 시작
													 - first : 첫 번쨰 반복이면 true, 아니면 false
													 - last  : 마지막반복이면 true, 아니면 false 
				--%>
					<c:if test="${not doneLoop && not done}">
						<c:choose>
							<c:when test="${!status.last && order.order_status eq 'order'}">
								<tr>
									<th>주문 번호</th>
									<th>고객 ID</th>
									<th>주문 시간</th>
									<th>주문 상태</th>
									<th>총 금액</th>
									<th>주문'승인/취소'</th>
								</tr>
								<%--위에서 내가 원하는 결과를 출력 후 true로 선언한 이유 : for문의 break; 효과와 비슷하다 --%>
								<c:set var="doneLoop" value="true" />
								
								<c:set var="done" value="true" /> <%-- 이 때, 아래에서 주문대기리스트를 출력하기 위해 --%>
							</c:when>
							
							<c:when test="${status.last && (order.order_status eq 'get' || order.order_status eq 'cancle') }">
								아직 주문 대기 리스트가 없습니다.
								<c:set var="doneLoop" value="true" />
							</c:when>
						</c:choose>
					</c:if>
				</c:forEach>	
				
			<c:if test="${done}">
				<form action="post">
					<c:forEach var="order" items="${dayOrderList}" varStatus="status">
						<c:if test="${order.order_status eq 'order'}">
							<tr>
								<td>
									<b> <a href="orderDetail.adm?order_num=${order.order_num }&c_id=${order.c_id }">
	                                    ${order.order_num }</a>
	                           		</b>
	
								</td>
								<td>${order.c_id}</td>
								<td>${order.order_date}</td>
								<td>주문 승인 대기 상태</td>
								<c:choose>
									<c:when test="${order.totalMoney < 50000}">
										<td>${order.totalMoney+saleTotalMoney}원</td> 
									</c:when>
									<c:otherwise>
										<td>${order.totalMoney}원</td>
									</c:otherwise>
								</c:choose>
								<td><!-- order_num으로 주문내역정보를 조회하여 주문상태를 get 또는 cancel로 변경하기 위해  -->
								<a href="orderGet.adm?order_num=${order.order_num}">주문승인</a>
								&nbsp;
								<a href="orderCancel.adm?order_num=${order.order_num}">주문취소</a>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</form>
			</c:if>
		</table>
		<br/><br/><br/>
		
		<!-- 1-2. [주문 승인] : get  ******************************************************** -->
		<div>
			<h3>주문 승인</h3>
		</div>
		<table>
			<c:set var="doneLoop2" value="false" />
			
				<c:forEach var="order" items="${dayOrderList}" varStatus="status">
					<c:if test="${not doneLoop2 && order.order_status eq 'get'}">
						<c:choose>
							<c:when test="${order.order_status eq 'get'}">
								<tr>
									<th>주문 번호</th>
									<th>고객 ID</th>
									<th>주문 시간</th>
									<th>주문 상태</th>
									<th>총 금액</th>
								</tr>
								<c:set var="doneLoop2" value="true" />
							</c:when>
							
							<c:otherwise>
								아직 주문 승인된 리스트가 없습니다.
								<c:set var="doneLoop2" value="true" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>	
				
			<form action="post">
				<c:forEach var="order" items="${dayOrderList}" varStatus="status">
					<c:if test="${order.order_status eq 'get'}">
						<tr>
							<td>
								<b> 
                              <a href="orderDetail.adm?order_num=${order.order_num }&c_id=${order.c_id }">
                                 ${order.order_num }
                              </a>
                           </b>

							</td>
							<td>${order.c_id}</td>
							<td>${order.order_date}</td>
							<td>주문 승인 상태</td>
							<c:choose>
									<c:when test="${order.totalMoney < 50000}">
										<td>${order.totalMoney+saleTotalMoney}원</td> 
									</c:when>
									<c:otherwise>
										<td>${order.totalMoney}원</td>
									</c:otherwise>
							</c:choose>
							
						</tr>
					</c:if>
				</c:forEach>
			</form>
		</table>
		
		<br/><br/><br/>
		
		<!-- 1-3. [주문 취소] : cancel  ******************************************************** -->
		<div>
			<h3>주문 취소</h3>
		</div>
		<table>
			<c:set var="doneLoop3" value="false" />
			
				<c:forEach var="order" items="${dayOrderList}" varStatus="status">
					<c:if test="${not doneLoop3 && order.order_status eq 'cancel'}">
						<c:choose>
							<c:when test="${order.order_status eq 'cancel'}">
								<tr>
									<th>주문 번호</th>
									<th>고객 ID</th>
									<th>주문 시간</th>
									<th>주문 상태</th>
									<th>총 금액</th>
								</tr>
								<c:set var="doneLoop3" value="true" />
							</c:when>
							
							<c:otherwise>
								아직 주문 취소된 리스트가 없습니다.
								<c:set var="doneLoop3" value="true" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>	
				
			<form action="post">
				<c:forEach var="order" items="${dayOrderList}" varStatus="status">
					<c:if test="${order.order_status eq 'cancel'}">
						<tr>
							<td>
								<b> 
                              <a href="orderDetail.adm?order_num=${order.order_num }&c_id=${order.c_id }">
                                 ${order.order_num }
                              </a>
                           		</b>

							</td>
							<td>${order.c_id}</td>
							<td>${order.order_date}</td>
							<td>주문 취소</td>
							<c:choose>
									<c:when test="${order.totalMoney < 50000}">
										<td>${order.totalMoney+saleTotalMoney}원</td> 
									</c:when>
									<c:otherwise>
										<td>${order.totalMoney}원</td>
									</c:otherwise>
								</c:choose>		
						</tr>
					</c:if>
				</c:forEach>
			</form>
		</table>
		
		<br/><br/><br/>
		
	</c:if>
	
	<!-- 2. ************************************************************* -->
	<c:if test="${dayOrderList eq null}">
		<p>아직 주문한 상품내역이 없습니다.</p>
	</c:if>
	</section>
</body>
</html>