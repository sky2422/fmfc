<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>게시판 글 수정</title>
</head>
<script type="text/javascript">
	function modifyboard(){
		modifyform.submit();
	}
</script>

<body>
<div class="container">
	<div class="bd-example">
	<h2>게시판 글 수정</h2>
	<form action="boardModify.bo" method="post" name="modifyform">
		<input type = "hidden" name = "board_num" value ="${article.board_num}" />
		<input type = "hidden" name = "c_id" value="${c_id}" />
	
	<table>
				<tr>
					<th>글 작성자</th>
					<td>
						<input type="text" name="c_id" value="${article.c_id}" id="c_id" required="required">
					</td>
				</tr>
				
				<tr>
					<th>글 제목</th>
					<td>
						<input type="text" name="board_subject" value="${article.board_subject}" id="board_subject" required="required"> 					
					</td>
				</tr>
				
				<tr>
					<th>글 내용</th>
					<td>
						<textarea name="board_content" id="board_content" cols="40" rows="15" required="required">${article.board_content}</textarea>
					</td>
				</tr>
				
				<tr>
					<th>파일 </th>
						<td>
							<input type="file" name="board_file" value="${article.board_file}" id="board_file" required="required">
						</td>
					</tr>			
			</table>
			
			
				
				<a href="javascript:modifyform.submit()">[수정]</a> &nbsp;&nbsp;
				<a href="javascript:history.back()">[뒤로]</a> 
				</div>
</div>
 <link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
		crossorigin="anonymous">
				
	</form>
</body>
</html>