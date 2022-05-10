<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!-- taglib 라이브러리를 사용하겠다. uri:라이브러리의 존재위치, c는 태그앞에 붙는 접두어-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>FMFC 굿즈샵</title>
<style type="text/css">
.table {
	table-layout: fixed;
	width: 100%;
}

.table td, .table th {
	padding: 10px 20px;
	border-top: 1px solid #ccc;
	word-break: break-all
}

@media screen and (max-width:768px) {
	/*block*/
	.table--block thead {
		display: none;
	}
	.table--block tr {
		display: block;
		margin-bottom: 10px;
		border-top: none;
	}
	.table--block th, .table--block td {
		display: block;
		position: relative;
		padding: 10px 0;
		padding-left: 50%;
		border-width: 0 0 1px 0;
	}
	.table--block td:before {
		display: block;
		position: absolute;
		left: 0;
		top: 0;
		width: 50%;
		padding: 10px 0;
		background: #ccc;
	}
}
</style>
</head>
<body>
	<div class="container" id="containermargin">
		<!-- 집 아이콘 -->
		<a href="${pageContext.request.contextPath}/customerMain.cus"> <svg
				xmlns="http://www.w3.org/2000/svg" width="16" height="16"
				fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
         <path fill-rule="evenodd"
					d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z" />
         <path fill-rule="evenodd"
					d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z" />
      </svg>
		</a>

		<!-- 화살표 아이콘 -->
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
			fill="currentColor" class="bi bi-chevron-right" viewBox="0 0 16 16">
         <path fill-rule="evenodd"
				d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z" />
      </svg>

		<!-- 현제 페이지 이름 -->
		굿즈샵
	</div>

	<section id="load">
		<div class="container">
			<div class="album">
				<c:if test="${productList != null}">
					<h2>[굿즈샵] 상품 목록</h2>
					<br>
					<table class="table table--block" cellspacing="0" cellpadding="0">
						<tr>
							<c:forEach var="product" items="${productList}" varStatus="status">
								<td><a href="productView.odr?p_code=${product.p_code}"><img
										src="${pageContext.request.contextPath}/images/product/${product.image}"
										width="200" height="200"></a><br /> 이름 : ${product.p_name}
									<br /> 상품가격: ${product.p_price }원 <br />
									<p style="font-size: 10px; font-family: '휴먼가는샘체'">배송비 ￦2500
										(￦50,000 이상 구매 시 무료)</p></td>
								<c:if test="${((status.index+1) mod 6) == 0 }">
						</tr>
						<tr>
							</c:if>

							</c:forEach>
						</tr>
					</table>
				</c:if>

				<c:if test="${productList == null}">
					<div>상품 목록이 없습니다.</div>
				</c:if>
			</div>
		</div>

	</section>
	<hr />

</body>
</html>