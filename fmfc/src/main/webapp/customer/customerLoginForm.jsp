<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String cookidId = "";//★★ null로 초기화 하면 안됨

Cookie[] cookies = request.getCookies();
if (cookies != null && cookies.length > 0) {
	for (int i = 0; i < cookies.length; i++) {
		if (cookies[i].getName().equals("c_id")) {
	cookidId = cookies[i].getValue();//쿠키값(=사용자 아이디)을 얻어와
	break;
		}
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- topmenue CSS -->
<link rel="stylesheet" type="text/css" href="../css/topnav.css">
<!-- Bootstrap에 필요한 CSS파일 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
	crossorigin="anonymous">
</script>

<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>

<script>
var frm = document.frm;

function check(){         
   if(document.frm.memberId.value =="")   {
      $("#modaltext").html("아이디를 입력해주세요");
      $("#exampleModal").modal("show");   
      document.getlementById('c_id').focus();
      
   }else if(document.frm.memberPwd.value==""){
      $("#modaltext").html("비밀번호를 입력해주세요");
      $("#exampleModal").modal("show");   
      document.getlementById('c_password').focus();
      
   }else{             
      document.frm.action ="<%=request.getContextPath()%>
	/customer/customerLoginAction.cus";
			document.frm.method = "post";
			document.frm.submit();
			return;
		}
	}

	function enterkey() {
		if (window.event.keyCode == 13) {
			check();
		}
	}
</script>
<style>
.label1 {
	width: 100px;
	justify-content: space-around;
	text-align: center;
}
</style>
<title>FMFC 로그인</title>
</head>
<body>
	<div class="container" id="containermargin">
		<!-- 집 아이콘 -->
		<a href="/customerMain.cus"> <svg
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
		로그인
	</div>

	<!-- 로그인화면 -->
	<div class="container">
		<div style="max-width: 500px;" class="mx-auto">
			<div class="mt-5">
				<p class="fs-5 text-black-50">login</p>
			</div>

			<div id="underline2">
				<p class="fs-1 text-muted" id="FMFC">FMFC</p>
			</div>
			<form action="customerLoginAction.cus" method="post" name="loginform">
				<input type="hidden" name="c_grade">
				<div class="row">
					<div class="col-md-8 pe-0" style="width: 75%">
						<div class="input-group mb-4 w-auto">
							<span class="input-group-text label1"
								id="inputGroup-sizing-default">ID</span> <input type="text"
								class="form-control" aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-default" tabindex=1
								name="c_id" value="<%=cookidId%>" placeholder="아이디입력(필수)">
						</div>

						<div class="input-group mb-3 w-auto">
							<span class="input-group-text label1"
								id="inputGroup-sizing-default">PW</span> <input type="password"
								class="form-control" aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-default" name="c_password"
								onkeyup="enterkey()" tabindex=2 placeholder="비밀번호입력(필수)">
						</div>
					</div>

					<div class="col">
						<div class="d-grid gap-2 mb-3">
							<input class="btn btn-outline-secondary btn-sm" type="submit"
								style="height: 100px; width: 100%; min-width: 60px;"
								onclick="check()" value="로그인" tabindex=3>
						</div>
					</div>
				</div>

				<div class="d-grid gap-2 mt-3">
					<button type="button" class="btn btn-outline-secondary btn-sm"
						onclick="location.href='customerJoin.cus'" tabindex=4>회원가입</button>
					<button type="button" class="btn btn-outline-secondary btn-sm"
						onclick="location.href='customerIdFindForm.cus'" tabindex=5>아이디찾기</button>
					<button type="button" class="btn btn-outline-secondary btn-sm"
						onclick="location.href='customerHashPwFindForm.cus'" tabindex=6>비밀번호찾기</button>
				</div>
			</form>
		</div>
	</div>

	<!-- Bootstrap에 필요한 JS파일 -->
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
		crossorigin="anonymous">
</body>
</html>