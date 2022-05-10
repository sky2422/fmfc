<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>[관리자모드]상품수정 폼</title>
</head>
<body>

<section>
	<header><h2>상품 수정</h2></header>
	<form action="productUpdate.adm" method="post" name="updateForm" enctype="multipart/form-data">
		<table>
			<tr>
				<th colspan="2">상품 수정</th>
			</tr>
			<tr>
				<th>상품 코드</th>
				<td> <!-- 장바구니에서 상품ID로 구분하여 사용 -->
					<input type="text" name="p_code" id="p_code" value="${product_update.p_code}" required="required">
				</td>
			</tr>
			
			<tr>
				<th>상품 카테고리</th>
				<td> 
					<input type="text" name="category" id="category" value="${product_update.category}" required="required">
				</td>
			</tr>
			
			<tr>
				<th>상품명</th>
				<td> 
					<input type="text" name="p_name" id="p_name" value="${product_update.p_name}" required="required">
				</td>
			</tr>
			
			<tr>
				<th>상품 가격</th>
				<td> 
					<input type="number" name="p_price" id="p_price" value="${product_update.p_price}"  step="100" min="0" max="100000" required="required">
				</td>
			</tr>
			
			<tr>
				<th>상품 설명</th>
				<td> 
					<!-- wrap="off"(자동 줄바꿈 방지) , textarea의 시작과 끝 태그는 반드시 붙어있어햠 : 이유?커서를 제일 처음에 위치시키기 위해. 떨어진 만큼 커서도 떨어져 있음  -->
					<textarea name="p_detail" id="p_detail" rows="13" cols="40" wrap="off" required="required">${product_update.p_detail}</textarea>
				</td>
			</tr>
			
			<tr>
				<th>판매가능여부</th>
				<td>  
					<c:if test="${product_update.p_status ne null and product_update.p_status eq 'y'}">
					<input type="radio" name="p_status" id="p_status" value="y" checked="checked"> 판매 가능
					<input type="radio" name="p_status" id="p_status" value="n" > 판매 불가
					</c:if>
				
					<c:if test="${product_update.p_status ne null and product_update.p_status eq 'n'}">
					<input type="radio" name="p_status" id="p_status" value="y" > 판매 가능
					<input type="radio" name="p_status" id="p_status" value="n" checked="checked"> 판매 불가
					</c:if>
				</td>
			</tr>
			
			<tr>
				<th>상품 이미지</th>
				<td> 
					<!-- [(productDAO)방법-1 : 기존의 이미지를 그대로 사용하려면 required="required" 삭제!] -->
						 <input type="file" name="image" id="image"> 
						 
					<!-- [(productDAO)방법-2] : 이미지를 다시 올리는 경우  -->
					<!-- <input type="file" name="image" id="image" required="required"> -->
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="상품수정">
					<input type="reset" value="다시작성">
				</td>
			</tr>
			
		</table>
	
	</form>

</section>

</body>
</html>