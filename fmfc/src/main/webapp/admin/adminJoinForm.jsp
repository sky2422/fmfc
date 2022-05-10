<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>관리자가입등록</title>
<style>
	#joinformArea{
		width : 50%;
		margin : auto;
		border : 1px solid gray;
	}
	table{
		width : 100%;
		margin :  auto;
		border : 1px solid gray;
		/* text-align: center; */
	}	
</style>

<!-- 아이디 중복확인 창 열기 -->
<!-- <script>
var chkId = false;
var idcheck;
function chkForm(f){
   alert(chkId);
   if(!chkId || idcheck != f.c_id.value.trim() ){
      alert("아이디 중복을 확인하세요 !");
      return false;
   }
}
</script> -->


<!-- DAUM API 적용(DAUM에서 제공하는 주소 검색을 사용하기 위해 반드시 포함) -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
function findAddr(){   
    //카카오 지도 발생->주소 입력 후 [검색]->찾는 주소 [선택]
    new daum.Postcode({
        oncomplete: function(data) { //[선택]시 입력값 세팅
         console.log(data);
         
         document.getElementById("postcode").value = data.zonecode;//우편번호
         
         var roadAddr = data.roadAddress;//도로명 주소
         var jibunAddr = data.jibunAddress;//지번 주소
         
         if(roadAddr != ''){//도로명 주소가 있으면 도로명 주소가 등록되고
            document.getElementById("address1").value = roadAddr;
         }else if(jibunAddr != ''){//도로명주소가 없고 지번주소만 있으면 지번주소를 등록함
            document.getElementById("address1").value = jibunAddr;            
         }
         
         //만약 지번주소 대신 무조건 도로명 주소만 입력하고 싶다면 위의 if~else 대신
         //document.getElementById("address1").value = data.roadAddress;
         //document.getElementById("address1").value = roadAddr;
         
         //상세주소 필드에 커서를 두어 바로 입력가능한 상태로 만듦
         document.getElementById("address2").focus();
        }
    }).open();  
}


</script>


<!-- 유효성 검사 -->
<script type="text/javascript">
function check(){
   //아이디와 비밀번호 값 데이터 정규화 공식
   const regIdPass = /^[a-zA-Z0-9]{8,12}$/;
   
   //이름 정규화 공식
   const regName = /^[가-힣a-zA-Z]{2,}$/;
   
   /*
      / / 안에 있는 내용은 정규표현식 검증에 사용되는 패턴이 이 안에 위치함
      / /i 정규표현식에 사용된 패턴이 대소문자를 구분하지 않도록 i를 사용함
      ^ 표시는 처음시작하는 부분부터 일치한다는 표시임
      [0-9a-zA-Z] 하나의 문자가 []안에 위치한 규칙을 따른다는 것으로 숫자와 알파벳 소문자 대문자인 경우를 뜻 함
      이 기호는 0또는 그 이상의 문자가 연속될 수 있음을 말함   
   */
   const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
   
   //휴대번호 정규화 공식
   const regCall = /^\d{3}\d{3,4}\d{4}$/; //-제외
   //consst regCall = /^\d{3}-\d{3,4}-\d{4}$/; //-포함
   
   //아이디 유효성 검사 - 정규화 공식 이용
   if( !document.f.c_id.value.trim() ){//if(document.f.c_id.value==false){
      alert("아이디를 입력하세요.");
      document.f.c_id.focus();//커서를 깜박거림
      return false; // submit()이 안됨 (true를 받아야지 submit()됨)
   }else if( !regIdPass.test(document.f.c_id.value.trim()) ){
      alert("아이디는 8~12자의 영어 대소문자와 숫자로만 입력가능합니다.");      
      return document.f.c_id.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
   }else
      
   //비밀번호 유효성 검사 - 정규화 공식 이용
   if( !document.f.c_password.value.trim() ){//if(document.f.c_password.value==false){
      alert("비밀번호를 입력하세요.");
      document.f.c_password.focus();//커서를 깜박거림
      return false;
   }else if( !regIdPass.test(document.f.c_password.value.trim()) ){
      alert("비밀번호는 8~12자의 영어 대소문자와 숫자로만 입력가능합니다.");      
      return document.f.c_password.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
   }else
      
   //이름 유효성 검사 - 정규화 공식 이용
   if( !document.f.c_name.value.trim() ){//if(document.f.c_name.value==false){
      alert("이름을 입력하세요.");
      document.f.c_name.focus();//이름 필드에 커서를 둠
      return false;
   }else if( !regName.test(document.f.c_name.value.trim()) ){//이름에 특수문자가 들어간 경우
      alert("이름이 잘못 입력되었습니다.(영어 대소문자와 한글만 입력가능합니다.)");   
      return document.f.c_name.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
   }else
      
   //이메일 유효성 검사 - 정규화 공식 이용
   if( !document.f.c_email.value.trim() ){//if(document.f.c_email.value==false){
      alert("이메일을 입력하세요.");
      document.f.c_email.focus();//커서를 깜박거림
      return false;
   }else if( !regEmail.test(document.f.c_email.value.trim()) ){
      alert("이메일 형식이 올바르지 않습니다.");   
      return document.f.c_email.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
   }else
      
   //휴대번호 유효성 검사 - 정규화 공식 이용
   if( !document.f.c_call.value.trim() ){//if(document.f.c_call.value==false){
      alert("휴대폰번호를 입력하세요.");
      document.f.c_call.focus();//커서를 깜박거림
      return false;
   }else if( !regCall.test(document.f.c_call.value.trim()) ){
      alert("휴대폰번호가 잘못 입력되었습니다. 숫자로만 입력해주세요.");   
      return document.f.c_call.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
   }else
      
   /*
   postcode(우편번호)와 address1은 "DAUM API"로 사용하여 검색한 내용을 바로 셋팅하므로
   유효성 검사가 필요없음
   */
   //address2 (상세주소) 검사 - 정규화 공식 이용X
   if(!document.f.address2.value.trim()){
      alert("상세주소를 입력하세요.");
      document.f.address2.focus();//커서를 깜박거림
      return false;
   }
   
   //위의 조건이 모두 거짓이면 = 유효성 검사를 모두 만족하면
   document.f.submit();// document 생략가능
}

</script>

</head>
<body>

<section id = "joinformArea">
	<form action="adminJoinAction.adm" method="post" name="f" >
	<!-- 처음 [관리자가입]하면 무조건  Admin등급 -->
	<input type="hidden" name="c_grade" value="Admin"/>
		<table>
			<tr>
				<th colspan="2">
					<h3>관리자등록</h3>
				</th>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="c_id" id="c_id" size=40 placeholder="8~12자 영문과 숫자조합을 입력하세요." required="required"/>
					<!-- window.open('팝업 주소', '팝업창 이름', '팝업창 설정') -->
				    <!--  <input type="button" name="c_idck" id="c_idck" value="아이디 중복 확인" onclick="window.open('idCheck/idCheck.jsp?openInit=true','아이디중복확인','top=10, left=10, width=500, height=300')"/> -->
				    <input type="button" name="c_idck" id="c_idck" value="아이디 중복 확인" onclick="window.open('${pageContext.request.contextPath}/idCheck/idCheck.jsp?openInit=true', '아이디중복확인', 'top=10, left=10, width=500, height=300')"/>
				</td>
			</tr>
			<tr>
			 	<th>비밀번호</th>
			 	<td><input type="password" name="c_password" size=41 placeholder="8~12자 영문과 숫자조합을 입력하세요." required="required" /></td>
			</tr>
			<tr>
			 	<th>지점명</th>
			 	<td><input type="text" name="c_name" size=40 placeholder="한글 또는 영문만 입력하세요.(특수문자 제외)" required="required" /></td>
			</tr>
			<tr>
			 	<th>이메일</th>
			 	<td><input type="text" name="c_email" size=40 placeholder="(ex)aaa.naver.com" required="required" /></td>
			</tr>
			<tr>
			 	<th>지점 연락번호</th>
			 	<td><input type="text" name="c_call" size=40 placeholder="(-)없이 숫자만 입력하세요." required="required" /></td>			 	
			</tr>
			<tr>
			 	<th>주소</th>
			 	<td>
				 	<input type="text" name="postcode" id="postcode" size=24 placeholder="우편번호만입력" required="required" />
				 	<input type="button" value="우편번호 찾기" onclick="findAddr();" required="required" /><br>
					
			 		<input type="text" name="address1" id="address1" size=40 placeholder="주소" required="required" /><br>
					
			 		<input type="text" name="address2" id="address2" size=40 placeholder="상세주소" required="required" />
			 	</td>
			</tr>
			
			<tr>
				<th colspan="2">
					<input type="submit" value="가입하기" onclick="check(); return false;">
				</th>
			</tr>
		</table>
	</form>

</section>

</body>
</html>