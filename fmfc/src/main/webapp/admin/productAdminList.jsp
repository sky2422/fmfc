<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"name="viewport"
   content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
<section id="listForm">
   <c:if test="${productList != null && productList.size() > 0 }"><!-- 1. 상품목록이 있으면  -->
      <table>
         <tr>
         <c:forEach var="product" items="${productList }" varStatus="status"><!-- 향샹된 for문 -->
            <td>
               <img src="${pageContext.request.contextPath}/images/product/${product.image }" id="productImage" width ="200" height="200"><br>
               상품코드: ${product.p_code }<br>
               상품카테고리: ${product.category }<br>
               상품명: ${product.p_name }<br>
               상품가격: ${product.p_price }원<br>
               상품설명: ${product.p_detail }<br>
               
               판매가능여부: <c:if test="${product.p_status eq 'y' }"> 판매가능</c:if> 
                        <c:if test="${product.p_status eq 'n'}"> 판매불가</c:if> <br/>
               상품등록날짜: ${product.p_date }<br>
               상품이미지: ${product.image }<br>
               
               <a href="productUpdateForm.adm?p_code=${product.p_code }"><b>[상품 수정]</b></a>
               &nbsp;&nbsp;&nbsp;
               <a href="productDelete.adm?p_code=${product.p_code }"><b>[상품 삭제]</b></a>
               <br><br><br>
            </td>
            
            <!-- 상품 목록을 출력할 때 1중에 4개씩만 나란히 출력되도록 하기 위해-->
            <c:if test="${((status.index+1) mod 4) == 0}">
               </tr>
               <tr>
               <!-- 
               1 mod 4 == 1 (거짓) </tr><tr> 실행안되고
               2 mod 4 == 2 (거짓) </tr><tr> 실행안되고
               3 mod 4 == 1 (거짓) </tr><tr> 실행안되고
               4 mod 4 == 0 (참)  </tr> <tr> 실행
               -->
            </c:if>
         </c:forEach>
         
         </tr>
         
      </table>
   </c:if>
   
   <c:if test="${productList == null }"><!-- 2. 상품목록이 없으면  -->
      <div>등록된 상품가 없습니다. 상품를 등록해주세요.</div>
   </c:if>
</section>
</body>
</html>