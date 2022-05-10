<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>[관리자 모드]선수수정 폼</title>
</head>
<body>

<section>
	<header><h2>선수 수정</h2></header>
	<form action="playerUpdate.adm" method="post" name="updateForm" enctype="multipart/form-data">
	
	<table>
		<tr>
			<th colspan="2">선수 수정</th>
		</tr>
		
		<tr>
			<th>등번호</th> 
			<td><input type="text" name="back_no" id="back_no" value="${player_update.back_no }"required="required"></td>
		</tr>
		
		<tr>
			<th>이름</th> 
			<td><input type="text" name="name" id="name" value="${player_update.name }"required="required"></td>
		</tr>
		
		<tr>
			<th>포지션</th>
			<td> 
				<!-- 반드시 선택해서 처리하도록 해야됨 -->
				<c:if test="${player_update.position ne null and player_update.position eq 'FW'}">
					<input type="radio" name="position" value="FW" checked="checked"> 공격수
					<input type="radio" name="position" value="MF"> 미드필더
					<input type="radio" name="position" value="DF"> 수비수
					<input type="radio" name="position" value="GK"> 골키퍼
				</c:if>
				
				<c:if test="${player_update.position ne null and player_update.position eq 'MF'}">
					<input type="radio" name="position" value="FW" > 공격수
					<input type="radio" name="position" value="MF" checked="checked"> 미드필더
					<input type="radio" name="position" value="DF"> 수비수
					<input type="radio" name="position" value="GK"> 골키퍼
				</c:if>
				
				<c:if test="${player_update.position ne null and player_update.position eq 'DF'}">
					<input type="radio" name="position" value="FW"> 공격수
					<input type="radio" name="position" value="MF"> 미드필더
					<input type="radio" name="position" value="DF" checked="checked"> 수비수
					<input type="radio" name="position" value="GK"> 골키퍼
				</c:if>
				
				<c:if test="${player_update.position ne null and player_update.position eq 'GK'}">
					<input type="radio" name="position" value="FW"> 공격수
					<input type="radio" name="position" value="MF"> 미드필더
					<input type="radio" name="position" value="DF"> 수비수
					<input type="radio" name="position" value="GK" checked="checked" > 골키퍼
				</c:if>
			</td>
		</tr>			
		<tr>
			<th>생일</th> 
			<td><input type="text" name="birth" id="birth" value="${player_update.birth }"required="required"></td>
		</tr>
		
		<tr>
			<th>키</th> 
			<td><input type="text" name="height" id="height" value="${player_update.height }"required="required"></td>
		</tr>
		
		<tr>
			<th>몸무게</th> 
			<td><input type="text" name="weight" id="weight" value="${player_update.weight }"required="required"></td>
		</tr>
		
		<tr>
			<th>소속팀</th> 
			<td><input type="text" name="team" id="team" value="${player_update.team }"required="required"></td>
		</tr>
			
		<tr>
			<th>선수 이미지</th>
			<td> 
				<!-- [(menuDAO)방법-1 : 기존의 이미지를 그대로 사용하려면 required="required" 삭제!] -->
					 <input type="file" name="image" id="image"> 
					 
				<!-- [(menuDAO)방법-2] : 이미지를 다시 올리는 경우  -->
				<!-- <input type="file" name="image" id="image" required="required"> -->
			</td>
		</tr>
			
		<tr>
			<td colspan="2">
				<input type="submit" value="선수수정">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
	</form>
</section>
<hr/>
	
</body>
</html>