<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FMFC info</title>
</head>
<body>
	<jsp:include page="/customerHeader.jsp" />

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
		구단 소개
	</div>

	<article>
		<!-- 회사 소개 이미지 -->
		<div class="container">
			<div class="bd-example">
				<img class="bd-placeholder-img card-img-top stretched-link imgbox"
					src="${pageContext.request.contextPath}/images/info/slogan.jpg">
			</div>
		</div>

		<!-- 지도 API-->
		<div class="container">
			
			<iframe id="map" style="border: 0; width: 100%; height: 500px;"
         	src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3233.8605749118633!2d128.5076967609231!3d35.85242218707872!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3565e539748d77cf%3A0xc9a0370fd6c120fa!2z7JiB64Ko6riw7Iig7KeB7JeF7KCE66y47ZWZ6rWQ!5e0!3m2!1sko!2sbg!4v1649641866611!5m2!1sko!2sbg"
        	frameborder="0" allowfullscreen></iframe>
			<div class="d-grid gap-2">
				<div id="subCon">
					<div class="subTop">
						<div class="subTit">
							<h3>오시는길</h3>
						</div>
					</div>
					<div class="substance2">
						<table class="location">
							<tr>
								<th class="first" width="150px">도로명 주소</th>
								<td class="first" width="400px">대구광역시 달서구 달구벌대로251안길 11 3층</td>
							</tr>
							<tr>
								<th>지 번 주 소</th>
								<td>대구광역시 달서구 이곡동 1224-2 3층</td>
							</tr>
							<tr>
								<th>전 화 번 호</th>
								<td>053-000-0000</td>
							</tr>
							<tr>
								<th>대 중 교 통</th>
								<td>지하철 2호선 성서산업단지역 8번 출구 도보 1분</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		
	</article>

	<jsp:include page="/customerFooter.jsp" />
</body>
</html>