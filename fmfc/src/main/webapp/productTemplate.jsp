<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title></title>
</head>
<body>
	<div><jsp:include page="customerHeader.jsp" /></div>
	<article>
		<div class="container">
			<div class="bd-example">
				<table>
					<c:if test="${showProduct ne null}">
						<tr>
							<td><jsp:include page="${showProduct}" /></td>
						</tr>
					</c:if>

					<tr>
						<!-- 장바구니 목록 : 처음 [주문하기] 요청하면 '장바구니 비어있음'이 출력됨 -->
						<td><jsp:include page="order/productCartList.jsp" /></td>
					</tr>
				</table>
			</div>
		</div>
	</article>

	<hr />
	<div><jsp:include page="customerFooter.jsp" /></div>

</body>
</html>