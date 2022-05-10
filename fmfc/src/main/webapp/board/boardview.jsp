<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>글 상세 보기</title>
</head>
<body>
<div class="container">
	<div class="bd-example">
	<h2>글 상세보기</h2>
	
	제목 : ${article.board_subject} <br>
	글 내용 : ${article.board_content}<br>
	파일 첨부 : ${article.board_file}<br>
	
	<a href="boardModifyForm.bo?board_num=${article.board_num}&c_id=${c_id}">[수정]</a>
	<a href="boardDelete.bo?board_num=${article.board_num}&c_id=${c_id}">[삭제]</a>
	
	<a href="boardList.bo">[목록]</a>
	</div>
</div>
<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
		crossorigin="anonymous">
</body>
</html>