<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>상품 정보 상세보기</title>
</head>
<body>
	<section>
		<!-- ----------- 메뉴 상세 정보보기 ---------------------------------->
		<table>
			<tr> <!-- [관리자 모드] : 업로드하는 폴더이름 images -->
				<img src="${pageContext.request.contextPath}/images/product/${productInfo.image}" />
			</tr>
			<tr>
				<td><h3>상품명 : ${productInfo.p_name}</h3></td>
			</tr>
			
			<tr>
				<td><h4>카테고리:${productInfo.category}</h4></td>
			</tr>
			<tr>
				<td>
					<h4>상세 정보:${productInfo.p_detail}</h4>
					
				</td>
			</tr>
			<tr>
				<td>
					<h4>가격 : ${productInfo.p_price}원</h4><p style="font-size:15px; font-family:'휴먼가는샘체'">배송비 ￦2500 (￦50,000 이상 구매 시 무료)</p>
				</td>
			</tr>
		
			<tr>
				<td>
					<a href="productCartAdd.odr?p_code=${productInfo.p_code}&p_name=${productInfo.p_name}">
					<h3>[장바구니 담기]</h3>
					</a>
				</td>
			</tr>
		</table>
		<br/>
		
	</section>
</body>
</html>