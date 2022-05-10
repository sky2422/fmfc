<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
<section>
   <c:if test="${playerList != null && playerList.size() > 0 }"><!-- 1. 상품목록이 있으면  -->
      <table>
         <tr>
         <c:forEach var="player" items="${playerList }" varStatus="status"><!-- 향샹된 for문 -->
            <td>
               <img src="${pageContext.request.contextPath}/images/player/${player.image }" id="playerImage" width="200" height="200"><br>
               선수 등번호: ${player.back_no }<br>
               선수 이름: ${player.name }<br>
               
              선수 포지션: <c:if test="${player.position eq 'FW'}"> 공격수</c:if> 
                <c:if test="${player.position eq 'MF'}"> 미드필더</c:if> 
                <c:if test="${player.position eq 'DF'}"> 수비수</c:if>
                <c:if test="${player.position eq 'GK'}"> 골키퍼</c:if> <br/>        
               
               선수 생일 ${player.birth }<br>
               선수 키: ${player.height }<br>          
              선수 몸무게: ${player.weight }<br>                 
               선수 소속팀: ${player.team }<br>
               선수 이미지: ${player.image }<br>
               
               <a href="playerUpdateForm.adm?back_no=${player.back_no }"><b>[선수 수정]</b></a>
               &nbsp;&nbsp;&nbsp;
               <a href="playerDelete.adm?back_no=${player.back_no }"><b>[선수 삭제]</b></a>
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
   
   <c:if test="${playerList == null }"><!-- 2. 상품목록이 없으면  -->
      <div>등록된 선수가 없습니다. 선수를 등록해주세요.</div>
   </c:if>
</section>
</body>
</html>