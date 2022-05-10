<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap에 필요한 CSS파일 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>

<!-- topmenue CSS -->
<link rel="stylesheet" type="text/css" href="../css/topnav.css">
<!-- subnav CSS -->
<link rel="stylesheet" type="text/css" href="../css/subnav.css">
<!-- 메인페이지 행사리뷰 CSS -->
<link rel="stylesheet" type="text/css" href="../css/mainpagereview.css">
<!-- 메인페이지 하단 게시판 CSS -->
<link rel="stylesheet" type="text/css" href="../css/mainpageboard.css">
<!-- Review CSS -->
<link rel="stylesheet" type="text/css" href="../css/review.css">

<title>FMFC Header</title>
</head>
<body>
	<!-- 상단 네비 부분 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-white" id="topnav">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/customerMain.jsp"><img
				src="${pageContext.request.contextPath}/images/logo/fmfc.png" alt=""
				width="200" class="d-inline-block align-text-top"> </a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- 헤더 메뉴 -->
			<c:choose>
				<c:when test="${c_id eq null}">
					<div class="collapse navbar-collapse w-60" id="navbarNav">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0" id="navbar-nav">
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/info.cus">구단소개</a></li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/playerList.pla">선수단</a>
							</li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/productList.odr">굿즈샵</a>
							</li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/schedule.cus">경기일정</a></li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5" 
								href="${pageContext.request.contextPath}/boardList.bo">자유게시판</a></li>
						</ul>
						<ul class="navbar-nav">
							<li class="nav-item mx-auto" id="liright1"><a
								class="nav-link"
								href="${pageContext.request.contextPath}/customerLogin.cus">로그인</a></li>
							<li class="nav-item mx-auto" id="liright2"><a
								class="nav-link"
								href="${pageContext.request.contextPath}/customerJoin.cus">회원가입</a></li>
						</ul>
					</div>
				</c:when>

				<c:when test="${c_grade eq 'Admin'}">
					<div class="collapse navbar-collapse w-50" id="navbarNav">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0" id="navbar-nav">
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/dayOrderManage.adm">실시간
									주문관리</a></li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/totalOrderManage.adm">전체주문/매출관리</a>
							</li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/productManage.adm">굿즈샵
									상품관리</a></li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/playerManage.adm">선수단
									선수관리</a></li>
						</ul>
						<ul class="navbar-nav">
							<li class="nav-item mx-auto" id="liright1"><a
								class="nav-link">${a_name} 관리자님 환영합니다.</a></li>
							<li class="nav-item mx-auto" id="liright1"><a
								class="nav-link"
								href="${pageContext.request.contextPath}/adminLogout.adm">로그아웃</a></li>
							<li class="nav-item mx-auto" id="liright2"><a
								class="nav-link"
								href="${pageContext.request.contextPath}/adminCustomerList.adm">회원정보관리</a></li>
						</ul>
					</div>
				</c:when>

				<c:otherwise>
					<div class="collapse navbar-collapse w-50" id="navbarNav">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0" id="navbar-nav">
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/info.cus">구단소개</a></li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/playerList.pla">선수단</a>
							</li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/productList.odr">굿즈샵</a>
							</li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5"
								href="${pageContext.request.contextPath}/schedule.cus">경기일정</a></li>
							<li class="nav-item px-5 mx-auto"><a
								class="nav-link fw-bolder text-dark fs-5" 
								href="${pageContext.request.contextPath}/boardList.bo">자유게시판</a></li>
						</ul>
						<ul class="navbar-nav">
							<li class="nav-item mx-auto" id="liright1"><a
								class="nav-link">${c_name}님 환영합니다.</a></li>
							<li class="nav-item mx-auto" id="liright1"><a
								class="nav-link"
								href="${pageContext.request.contextPath}/customerLogout.cus">로그아웃</a></li>
							<li class="nav-item mx-auto" id="liright2"><a
								class="nav-link"
								href="${pageContext.request.contextPath}/myOrder.odr">주문내역보기</a></li>
							<li class="nav-item mx-auto" id="liright3"><a
								class="nav-link"
								href="${pageContext.request.contextPath}/customerView.cus">회원정보관리</a></li>
							<li class="nav-item mx-auto" id="liright3"><a
								class="nav-link" href="#">고객문의</a></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
		crossorigin="anonymous">
</body>
</html>