<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
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
<div class="container">
			<div class="bd-example">
				<c:if test="${boardList != null}">
					 <h2>자유게시판</h2>
					 <br>
					 <table>
					 	<tr>
						 	<th>글 번호</th>
							<th>글 제목</th>
							<th>작성자</th>
							<th>작성 날짜</th>
							<th>조회수</th>
					 	</tr>
					 
						
						<c:forEach var="board" items="${boardList}" varStatus="status">
						<tr align="center">
							<td><a href="boardDetail.bo?board_num=${board.board_num}">${board.board_num}</a></td>
							<td>${board.board_subject}</td>
							<td>${board.c_id}</td>
							<td>${board.board_date}</td>
							<td>${board.board_count}</td>
						</tr>	
						</c:forEach>
						
					</table>
	    	<div align="center">
	            <a href="boardWriteForm.bo">[글쓰기]</a>
	        </div>
       
       
       
       
       			</c:if>
       
       <c:if test="${boardList == null}">
					<div>등록된 게시물이 없습니다.</div>
			<div align="center">
	            <a href="boardWriteForm.bo">[글쓰기]</a>
	        </div>
		</c:if>
	</div>
</div>
       <link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
		crossorigin="anonymous">
</body>
</html>