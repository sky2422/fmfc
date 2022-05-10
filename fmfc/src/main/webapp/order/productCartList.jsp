<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
   content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>장바구니 목록</title>
</head>

<script type="text/javascript">
/*
 * [전체 체크박스] 
 */
function checkAll(theForm) {//장바구니항목을 선택하는 체크박스가 1개일 때(즉, 개상품이 1개만 담겼을 때) length는 undefined가 된다.(이유?length는 배열에만 존재한다.)
   if(theForm.remove.length == undefined){//체크하면 true, 체크해제하면false
      theForm.remove.checked = theForm.allCheck.checked;
   }else{//장바구니 항목을 선택하는 체크박스가 여러 개일 때(=배열객체(remove가 배열이 됨)일 때. 즉, 개상품이 2개이상 담겼을 때)
      for(var i=0; i<theForm.remove.length; i++){
         theForm.remove[i].checked = theForm.allCheck.checked;
      }
   }
}
/*
 * [장바구니 항목 수량 증가/감소 요청]
 */
function checkQtyUp(p_code){
      location.href="productCartQtyUp.odr?p_code=" + encodeURIComponent(p_code);
}

function checkQtyDown(p_code, qty){
	if(qty != 1){//현재 수량이 1보다 클 때만 수량을 감소시킴
		location.href="productCartQtyDown.odr?p_code=" + encodeURIComponent(p_code);
	}
}
/*
 * [삭제]-----------------------------------------------------------------------------
 */
function removeCartAll() {
   if(confirm("상품을 모두 삭제하시겠습니까?\n삭제 후 다시 복원되지 않습니다.") == true){
      location.href="productCartRemoveAll.odr";
   }else{
      return false;
   }
}

function removeCart(p_code){
   if(confirm("상품을 삭제하시겠습니까?\n삭제 후 다시 복원되지 않습니다.") == true){
      location.href="productCartRemove.odr?p_code=" + encodeURIComponent(p_code);//모든 문자를 "utf-8"인코딩하는 함수
   }else{
      return false;
   }
}
</script>

<body>
   <section>
      <c:if test="${cartList != null && cartList.size()>0 }">
         <h2>장바구니 목록</h2>
         <form method="post" name="f">
            <table>
               <tr>
                  <td>
                     <input type="checkbox" name="allCheck" onclick="checkAll(this.form)" /><!-- 전체 체크 박스 --> 
                  </td>
                  <td>상품 번호</td>
                  <td>상품 이미지</td>
                  <td>상품명</td>
                  <td>상품 가격</td>
                  <td>상품 수량</td>
                  <td><input type="button" value="전체삭제" onclick="removeCartAll();" /></td>
               </tr>
               <!-- 향상된 for문 시작 -->
               <c:forEach var="cart" items="${cartList}" varStatus="status">
                  <tr>
                     <td><input type="checkbox" name="remove" value="${cart.encoding_p_code}" /></td>
                     <td>${status.count}</td><%-- <td>${status.index+1}</td> --%>
                     
                     <td><img src="${pageContext.request.contextPath}/images/product/${cart.image}" width="100" height="100"></td>
                     <td>${cart.name}</td>
                     <td>
                        ${cart.price}원 <input type="hidden" name="priceList" value="${cart.price}">
                     </td>
                     <td>
                     <a href="javascript:checkQtyUp('${cart.p_code}')"> <!-- ★★ 함수 호출시 주의 사항 : 함수('숫자 타입이 아닌 경우'), 함수(숫자 타입) --> 
                        <img src="${pageContext.request.contextPath}/images/button/up.PNG" id="upImage" border=0 alt="수량1씩증가" width="22" height="22">
                     </a> 
                     <br> 
                        ${cart.qty} <!-- 현재 수량 --> 
                     <input type="hidden" name="qtyList" value="${cart.qty}"> 
                     <br> 
                     <!-- ★★ 함수 호출시 주의 사항 : 함수('숫자 타입이 아닌 경우'), 함수(숫자 타입) -->
							<a href="javascript:checkQtyDown('${cart.p_code}', ${cart.qty})">
								<img src="${pageContext.request.contextPath}/images/button/down.PNG" id="downImage" border=0 alt="수량1씩감소" width="20" height="20">
							</a>
						</td>

                     <td><input type="button" value="삭제" onclick="removeCart('${cart.p_code}')"></td>

                  </tr>
               </c:forEach>
               <!-- 향상된 for문 끝 -->
               <tr>
                  <td colspan="7" align="center">장바구니 금액(※ 50000원 이상 주문시 배송비 무료) : ${totalMoney}원 
                  <input type="hidden" name="totalMoney" value="${totalMoney}">
                  </td>
               </tr>

               <tr>
                  <td colspan="7" align="center">
                  <a href="productCartOrder.odr?totalMoney=${totalMoney}">[구매하기]</a></td>
               </tr>
            </table>
         </form>
      </c:if>
      <!-- --------------------------------------------------------- -->
      <c:if test="${cartList == null}">
         <section>장바구니가 비어있습니다.</section>
      </c:if>
   </section>
</body>
</html>