<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>

	<section>
		<h2>${player.name} 상세정보</h2>
		
		<section>
		
			<section id ="content_left">
				<img src="images/player/${player.image}" width="200" height="200">
			</section>
				
			<section id = "content_right">
				<b>등번호 :</b> ${player.back_no } <br/>
				<b>이름 :</b> ${player.name } <br/>
				<b>포지션 :</b> ${player.position } <br/>
				<b>생일 :</b> ${player.birth} <br/>
				<b>키 :</b> ${player.height } <br/>
				<b>몸무게 :</b> ${player.weight } <br/>
				<b>소속팀 :</b> ${player.team } <br/>
				
			</section>
		</section>
		
		<div style="clear:both"></div>
		
		<nav>
			<a href="playerList.pla">전체 선수 목록보기</a>
		</nav>
		
	</section>
	<hr/>

</body>
</html>