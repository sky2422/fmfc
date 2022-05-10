<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>FCFM main</title>
<style>
#fixedbutton {
	position: fixed;
	bottom: 10px;
	right: 5px;
}

.box {
	overflow: hidden;
}

.box img {
	object-fit: cover;
	transform: scale(1.0);
	transition: transform .5s;
}

.box img:hover {
	transform: scale(1.3);
	transition: transform .5s;
}
</style>
</head>
<body>
	<jsp:include page="customerHeader.jsp" />
	<div class="bd-example">
		<div id="carouselExampleCaptions" class="carousel slide "
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item">
					<img class="bd-placeholder-img bd-placeholder-img-lg d-block w-100"
						src="images/banner1.png">
				</div>
				<div class="carousel-item">
					<img class="bd-placeholder-img bd-placeholder-img-lg d-block w-100"
						src="images/banner2.png">
				</div>
				<div class="carousel-item active">
					<img class="bd-placeholder-img bd-placeholder-img-lg d-block w-100"
						src="images/banner3.png">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden"><font
					style="vertical-align: inherit;"><font
						style="vertical-align: inherit;">이전</font></font></span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden"><font
					style="vertical-align: inherit;"><font
						style="vertical-align: inherit;">다음</font></font></span>
			</button>
		</div>
	</div>
	<div class="bd-example text-center align-middle">
		<p class="tit">
			<img src="images/image1.png">
		</p>
	</div>
	<!-- 중앙 네비 카테고리 검색창 -->
	<div class="container " id="detailload">
		<div class="container">
			<nav style="max-width: 1300px; margin: 0px auto; margin-top: 50px;"
				class="navbar navbar-expand-lg navbar-light rounded"
				aria-label="Eleventh navbar example">
				<div class="container-fluid">
					<button class="navbar-toggler collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarsExample09"
						aria-controls="navbarsExample09" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="navbar-collapse collapse" id="navbarsExample09">
						<ul class="navbar-nav me-auto mb-lg-0" id="midnav">
							<li class="nav-itemmb-5"><a class="nav-link fw-bolder "
								type="button" onclick="selectAll()">전체</a></li>
							<li class="nav-item"><a class="nav-link fw-bolder"
								type="button" onclick="selectCoffe()">공격수</a></li>
							<li class="nav-item"><a class="nav-link fw-bolder"
								type="button" onclick="selectTea()">수비수</a></li>
							<li class="nav-item"><a class="nav-link fw-bolder"
								type="button" onclick="selectBakery()">골키퍼</a></li>
						</ul>
						<div class="text-center align-middle">
							<div style="display: inline-block;">
								<input class="form-control form-control-sm " id="serchbox"
									type="text" placeholder="Search" aria-label="Search"
									onkeypress="if( event.keyCode == 13 ){searchReview();}">
							</div>
						</div>
					</div>
				</div>
			</nav>
		</div>
		<!-- 메인 선수정보 부분3x1출력 -->
		<section>
			<div class="album bg-light">
				<div class="container">
					<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="자리 표시 자 : 축소판" preserveAspectRatio="xMidYMid slice"
									focusable="false">
							<title>Placeholder</title>
							<rect width="100%" height="100%" fill="#55595c"></rect>
							<text x="50%" y="50%" fill="#eceeef" dy=".3em">이미지</text>
						</svg>
								<div class="card-body">
									<div class="justify-content-between align-items-center">
										<p class="card-header">선수1</p>
										<p class="card-text">★★★★★</p>
										<small class="text-muted">No.10</small>
									</div>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="자리 표시 자 : 축소판" preserveAspectRatio="xMidYMid slice"
									focusable="false">
							<title>Placeholder</title>
							<rect width="100%" height="100%" fill="#55595c"></rect>
						<text x="50%" y="50%" fill="#eceeef" dy=".3em">이미지</text>
						</svg>
								<div class="card-body">
									<div class="justify-content-between align-items-center">
										<p class="card-header">선수2</p>
										<p class="card-text">★★★★★</p>
										<small class="text-muted">No.7</small>
									</div>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="자리 표시 자 : 축소판" preserveAspectRatio="xMidYMid slice"
									focusable="false">
							<title>Placeholder</title>
							<rect width="100%" height="100%" fill="#55595c"></rect>
							<text x="50%" y="50%" fill="#eceeef" dy=".3em">이미지</text>
						</svg>
								<div class="card-body">
									<div class="justify-content-between align-items-center">
										<p class="card-header">선수3</p>
										<p class="card-text">★★★★★</p>
										<small class="text-muted">No.1</small>
									</div>
								</div>
							</div>
						</div>
						<section id="load">
							<%-- 백엔드 : 반복문을 이용하여 검색 조건에 따른 게시물을 표시하도록 한다.  --%>
					</div>
				</div>
			</div>
		</section>
		
		<br><br><Br>
		
		<!-- 메인 굿즈샵 부분 3x1출력 -->
		<section>
			<div class="album bg-light">
				<div class="container">
					<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="자리 표시 자 : 축소판" preserveAspectRatio="xMidYMid slice"
									focusable="false">
							<title>Placeholder</title>
							<rect width="100%" height="100%" fill="#55595c"></rect>
							<text x="50%" y="50%" fill="#eceeef" dy=".3em">이미지</text>
						</svg>
								<div class="card-body">
									<div class="justify-content-between align-items-center">
										<p class="card-header">유니폼</p>
										<p class="card-text">설명</p>
										<small class="text-muted">가격</small>
									</div>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="자리 표시 자 : 축소판" preserveAspectRatio="xMidYMid slice"
									focusable="false">
							<title>Placeholder</title>
							<rect width="100%" height="100%" fill="#55595c"></rect>
						<text x="50%" y="50%" fill="#eceeef" dy=".3em">이미지</text>
						</svg>
								<div class="card-body">
									<div class="justify-content-between align-items-center">
										<p class="card-header">축구공</p>
										<p class="card-text">설명</p>
										<small class="text-muted">가격</small>
									</div>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="100%"
									height="225" xmlns="http://www.w3.org/2000/svg" role="img"
									aria-label="자리 표시 자 : 축소판" preserveAspectRatio="xMidYMid slice"
									focusable="false">
							<title>Placeholder</title>
							<rect width="100%" height="100%" fill="#55595c"></rect>
							<text x="50%" y="50%" fill="#eceeef" dy=".3em">이미지</text>
						</svg>
								<div class="card-body">
									<div class="justify-content-between align-items-center">
										<p class="card-header">축구화</p>
										<p class="card-text">설명</p>
										<small class="text-muted">가격</small>
									</div>
								</div>
							</div>
						</div>
						<section id="load">
							<%-- 백엔드 : 반복문을 이용하여 검색 조건에 따른 게시물을 표시하도록 한다.  --%>
					</div>
				</div>
			</div>
		</section>

		<br><br><br>
		
		<!-- 메인 공지사항 4개 출력 -->
		<div class="container bg-white">최근에 등록된 게시판을 표시합니다.</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">제목</th>
					<th scope="col">카테고리</th>
					<th scope="col">작성일</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>공지사항1</td>
					<td>공지사항</td>
					<td>sysdate</td>
				</tr>
				<tr>
					<td>공지사항1</td>
					<td>공지사항</td>
					<td>sysdate</td>
				</tr>
				<tr>
					<td>공지사항1</td>
					<td>공지사항</td>
					<td>sysdate</td>
				</tr>
				<tr>
					<td>공지사항1</td>
					<td>공지사항</td>
					<td>sysdate</td>
				</tr>
			</tbody>
		</table>

		<!-- 화면구석 장바구니 버튼 -->
		<div id="fixedbutton" class="w-25"
			style="max-width: 130px; display: block;">
			<div class="rounded-3 border border-3 border-warning">
				<div class="bg-warning bg-gradient">
					<button type="button" class="btn-close" aria-label="Close"
						onclick="closeFn()"></button>
					<div class="text-center fw-bold fs-6">
						장바구니<br>바로가기
					</div>
					<button type="button"
						class="bg-light rounded-3 border border-1 border-secondary text-center ms-1 me-1 pb-1 text-center"
						onclick="askFn()">
						<a href="${pageContext.request.contextPath}/productCartList.odr"><img src="images/icon_cart.png" class="mt-1 mb-1 w-75" /></a><br>
						<div class="fw-bold">Click</div>
					</button>
				</div>
			</div>
		</div>
		
		<jsp:include page="customerFooter.jsp" />
</body>
</html>