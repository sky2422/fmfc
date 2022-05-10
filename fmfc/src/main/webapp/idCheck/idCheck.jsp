<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.* ,javax.sql.*, javax.naming.*"%>

<%
	String openInit = "false";
	if(request.getParameter("openInit") != null){
		openInit = request.getParameter("openInit");//openInit = "true"가 됨
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복확인</title>
</head>

<script type="text/javascript">
//사용자가 회원가입폼(opener인 부모 창)에서 입력한 id가 자동 셋팅됨
function init() {
	if(<%=openInit%>){//openInit가 "true"이면
		//opener인 부모 창의 c_id에 입력된 값을 가져와 아래 idCheck에 그대로 셋팅한다.
		document.getElementById("idCheck") = opener.document.getElementById("c_id").value;
	}
}

function useId(chk_id){
	opener.document.getElementById("c_id").value = chk_id.trim();
	window.close();
}
</script>

<!-- [아이디중복확인] 창이 열리면 onload이벤트에서 의해 init()함수가 호출되어 실행됨-->
<body onload="init()">
	<form action="idCheckProcess.jsp" method="post" name="f">
		<input type="text" name="idCheck" id="idCheck" /> 
		&nbsp;&nbsp;
		<input type="submit" value="중복확인" id="submit">
	</form>
</body>
</html>

<%
request.setCharacterEncoding("utf-8");
String chk_id = request.getParameter("chk_id");

//if(request.getParameter("chk_id")!=null && !request.getParameter("chk_id").trim().equals("")){
if(chk_id != null && !chk_id.trim().equals("")){
	String useable = request.getParameter("useable");
	
	out.println("<hr/>");
	
	if(useable.equals("NO")){
		out.println( "<h4>"+chk_id + "는(은) 사용 불가능한 아이디입니다. 다시 시도해주세요</h4>");
	}else{
		out.println( "<h4>"+chk_id + "는(은) 사용 가능한 아이디입니다.");
		out.println("<a href='javascript:useId(\""+ chk_id + "\")'>사용하기</a> </h4>");
	}
}
%>
















