<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>[관리자 모드 ]선수 추가 폼</title>
</head>
<body>

	<section>
		<header><h2>선수 등록</h2></header>
		<form action="playerRegist.adm" method="post" name="registForm" enctype="multipart/form-data">
			<table>
				<tr>
					<th colspan="2">선수 등록</th>
				</tr>
				<tr>
					<th>등번호</th> 
					<td>
					<input type="text" name="back_no" id="back_no"required="required">
					</td>
				</tr>
				<tr>
					<th>이름</th> 
					<td><input type="text" name="name" id="name"required="required"></td>
				</tr>
				<tr>
					<th>포지션</th> 
					<td>
					<input type="radio" name="position" value="FW">공격수
					<input type="radio" name="position" value="MF">미드필더
					<input type="radio" name="position" value="DF">수비수
					<input type="radio" name="position" value="GK">골키퍼
					</td>
				</tr>
				<tr>
					<th>생일</th> 
					<td><input type="text" name="birth" id="birth" required="required"></td>
				</tr>
				<tr>
					<th>키</th> 
					<td><input type="text" name="height" id="height" required="required"></td>
				</tr>
				<tr>
					<th>몸무게</th> 
					<td><input type="text" name="weight"id="weight"  required="required"></td>
				</tr>
				<tr>
					<th>소속팀</th> 
					<td><input type="text" name="team" id="team" required="required"></td>
				</tr>
				<tr>
					<th>선수이미지</th> 
					<td><input type="file" name="image" id="image" required="required"></td>
				</tr>
				
				<tr>
			      <td colspan="2"> 
			         <input type = "submit" value = "선수등록" />      
			         <input type = "reset" value = "다시작성" />          
			      </td>
	  			 </tr>   
   
			</table>
		</form>
	</section>
	<hr/>
	
</body>
</html>