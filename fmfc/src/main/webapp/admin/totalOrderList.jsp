<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.Calendar" %>

<%
Calendar cal = Calendar.getInstance();

String yearR = request.getParameter("year");//아래에서 입력한 2021
String monthR = request.getParameter("month");//아래에서 입력한 10

//값을 파라미터로 전송받지 않았을 때, 기본값(현재 시스템의 날짜. 만약 2022년 4월)
int year = cal.get(Calendar.YEAR);//현재 시스템의 날짜 //2022
int month = cal.get(Calendar.MONTH);//3이 드러감(why?Calendar의 월은 0부터 시작하므로)

if(yearR != null && monthR != null && !yearR.trim().equals("") && !monthR.trim().equals("")){
	year = Integer.parseInt(yearR);
	month = Integer.parseInt(monthR)-1;//★★★ 중요 : Calendar의 월은 0~11이므로 -1를 반드시 해줘야한다!!
	
}

//달력 시작 시점 지정, 세팅한 시점부터 값을 결정
cal.set(year, month, 1);//돌려주는 타입이 void()이므로 =으로 받을 필요가 없다!
//Calendar.DAY_OF_WEEK : 그 달의 시작일의 요일을 알 수 있다.(1~7 사이의 정수를 리턴 : 1:월 ~ 7:토)
int dayOfweek = cal.get(Calendar.DAY_OF_WEEK);//월의 시작일의 요일을 정수로 리턴(2022년 4월 1일 금요일이면 6을 리턴)
//달력 마지막 날 지정(예 : 30일 또는 31일 또는 2월달은 26/28 리턴함)
int lastday = cal.getActualMaximum(Calendar.DATE);//2022년 4월이라면 30을 리턴 

/* ------------------------------------------------------------------------------------------------------*/
//◀ 이전 버튼
int before_year = year;
int before_month = month;//★★★ 중요 : before_month값은 month(월 0~11)이므로 그대로 저장 
if(before_month == 0){// ◀ 이전 버튼 클릭하여 월이 0(=1월)이 되면
	before_year = before_year -1;//년도를 1감소
	before_month = 12;//12월로 
}

//▶ 다음 버튼
int after_year = year;
int after_month = month+2;//★★★ 중요 : after_month값은 month(월 0~11) -> 2를 더하여 월2~13으로 만들어 저장해야 함
if(after_month == 13){// ▶ 다음 버튼 클릭하여 월이 13이 되면
	after_year = after_year +1;//년도를 1증가
	after_month = 1;//1월로 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
<style>
table {
	text-align :center;
}
</style>
</head>
<body>

	
	<caption>
		<button type="button" onclick="location.href='totalOrderManage.adm?year=<%=before_year%>&month=<%=before_month%>'">◀</button>
		<%=year%>년<%=month+1%>월
		<button type="button" onclick="location.href='totalOrderManage.adm?year=<%=after_year%>&month=<%=after_month%>'">▶</button>
	</caption>
	
	<section id="section">
	<!-- 1. ************************************************************* -->
	<c:if test="${totalOrderList ne null && totalOrderList.size() > 0}">
	
	<!-- 1-1. [주문 대기] ******************************************************** -->
		<div>
			<h3>주문 대기</h3>
		</div>
		<table>
			<c:set var="doneLoop" value="false" />
			<%-- <c:set var="done" value="false" /> --%>
			
				<c:forEach var="order" items="${totalOrderList}" varStatus="status">
				<%-- varStatus="반복 상태를 알 수 있는 변수 " - index : item에서 정의한 항목을 가르키는 index번호, 0부터 시작
													 - count : 몇 번째 반복인지를 나타냄. 1부터 시작
													 - first : 첫 번쨰 반복이면 true, 아니면 false
													 - last  : 마지막반복이면 true, 아니면 false 
				--%>
					<c:if test="${not doneLoop}">
						<c:choose>
							<c:when test="${order.order_status eq 'order'}">
								<tr>
									<th>주문 번호</th>
									<th>고객 ID</th>
									<th>고객 EMAIL</th>
									<th>주문 시간</th>
									<th>주문 상태</th>
									<th>총 금액</th>
					
								</tr>
								<%--위에서 내가 원하는 결과를 출력 후 true로 선언한 이유 : for문의 break; 효과와 비슷하다 --%>
								<c:set var="doneLoop" value="true" />
								
								<%-- <c:set var="done" value="true" /> 이 때, 아래에서 주문대기리스트를 출력하기 위해 --%>
							</c:when>
							
							
						</c:choose>
					</c:if>
				</c:forEach>	
				
			<%-- <c:if test="${done}"> --%>
				<form action="post">
					<c:forEach var="order" items="${totalOrderList}" varStatus="status">
						<c:if test="${order.order_status eq 'order'}">
							<tr>
								<td>
									<b> <a href="orderDetail.adm?order_num=${order.order_num }&c_id=${order.c_id }">
	                                    ${order.order_num }</a>
	                           		</b>
	
								</td>
								<td>${order.c_id}</td>
								<td>${order.c_email}</td>
								<td>${order.order_date}</td>
								<td>주문 승인 대기 </td>
								<c:choose>
									<c:when test="${order.totalMoney < 50000}">
										<td>${order.totalMoney}원<span style="font-size:11px; font-family:'휴먼가는샘체'">(※ 배송비 미포함)</span></td> 
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
		<%-- </c:if> --%>
		<br/><br/><br/>
		
			<!-- 1-2. [주문 취소] : cancel  ******************************************************** -->
		<div>
			<h3>주문 취소</h3>
		</div>
		<table>
			<c:set var="doneLoop3" value="false" />
			
				<c:forEach var="order" items="${totalOrderList}" varStatus="status">
					<c:if test="${not doneLoop3 && order.order_status eq 'cancel'}">
						<c:choose>
							<c:when test="${order.order_status eq 'cancel'}">
								<tr>
									<th>주문 번호</th>
									<th>고객 ID</th>
									<th>고객 EMAIL</th>
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
				<c:forEach var="order" items="${totalOrderList}" varStatus="status">
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
							<td>${order.c_email}</td>
							<td>${order.order_date}</td>
							<td>주문 취소</td>
							<c:choose>
									<c:when test="${order.totalMoney < 50000}">
										<td>${order.totalMoney}원<span style="font-size:11px; font-family:'휴먼가는샘체'">(※ 배송비 미포함)</span></td>
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
		
		<!-- 1-3. [주문 승인] : get  ******************************************************** -->
		<div>
			<h3>주문 승인</h3>
		</div>
		<table>
			<c:set var="doneLoop2" value="false" />
			
				<c:forEach var="order" items="${totalOrderList}" varStatus="status">
					<c:if test="${not doneLoop2 && order.order_status eq 'get'}">
						<c:choose>
							<c:when test="${order.order_status eq 'get'}">
								<tr>
									<th>주문 번호</th>
									<th>고객 ID</th>
									<th>고객 EMAIL</th>
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
				<c:forEach var="order" items="${totalOrderList}" varStatus="status">
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
							<td>${order.c_email}</td>
							<td>${order.order_date}</td>
							<td>주문 승인 </td>
							<c:choose>
									<c:when test="${order.totalMoney < 50000}">
										<td>${order.totalMoney}원<span style="font-size:11px; font-family:'휴먼가는샘체'">(※ 배송비 미포함)</span></td> 
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
		
		
		<div>
			<h4>
			[대기${OrderList}건]/[취소${OrderCancelList}건]을 제외한 <%=month+1%>월 매출은 총 매출 금액 : ${MonthTotalMoney}원<span style="font-size:11px; font-family:'휴먼가는샘체'">(※ 배송비 미포함)</span>입니다.</h4>
		</div>
		
	
		
	</c:if>
	
	<!-- 2. ************************************************************* -->
	<c:if test="${totalOrderList eq null}">
		<p>아직 주문한 메뉴내역이 없습니다.</p>
	</c:if>
	</section>






</body>
</html>