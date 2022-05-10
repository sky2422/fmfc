<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호 변경</title>

<!-- 유효성 체크 -->
<script type="text/javascript">
function check() {
	// 비밀번호 값 데이터 정규화 공식
	const regPass = /^[a-zA-Z0-9]{8,12}$/;
	
	//let Pre_password = document.f.pre_password;
	//let new_password = document.f.new_password;
	//let confirm_password = document.f.confirm_password;
	
	// '이전 비밀번호' 유효성 검사 - 정규화 공식 이용 
	if(document.f.pre_password.value.trim() == false){
		alert("이전 비밀번호를 입력하세요.");
		document.f.pre.password.focus();//커서를 깜박거림
		return false;
	}else if( !regPass.test(document.f.pre_password.value.trim()) ){
		alert("비밀번호는 8~12자 영어와 대소문자와 숫자로만 입력가능합니다.");
		return document.f.pre_password.select();// 입력한 부분에 블록 설정되어 바로 수정 가능한 상태로 만듦
	}else
	
	// '새 비밀번호' 유효성 검사 - 정규화 공식 이용
	if(document.f.new_password.value.trim() == false){
		alert("새 비밀번호를 입력하세요.");
		document.f.new_password.focus();//커서를 깜박거림
		return false;
	}else if( !regPass.test(document.f.new_password.value.trim()) ){
		alert("비밀번호는 8~12자 영어와 대소문자와 숫자로만 입력가능합니다.");
		return document.f.new_password.select();// 입력한 부분에 블록 설정되어 바로 수정 가능한 상태로 만듦
	}else	
	
	// '새로운 비밀번호 확인' 유효성 검사 - 정규화 공식 이용
	if(document.f.confirm_password.value.trim() == false){
		alert("새 비밀번호와 동일하게 다시 한번 더 입력해주세요.");
		document.f.confirm_password.focus();//커서를 깜박거림
		return false;
	}else if( !regPass.test(document.f.confirm_password.value.trim()) ){
		alert("비밀번호는 8~12자 영어와 대소문자와 숫자로만 입력가능합니다.");
		return document.f.confirm_password.select();// 입력한 부분에 블록 설정되어 바로 수정 가능한 상태로 만듦
	}else	
	
	//'이전 비밀번호' 와  '새 비밀번호'가 일치하는지 확인하여 다르게 입력하도록 유도
	if(document.f.pre_password.value.trim() == document.f.new_password.value.trim()) {
	alert("새 비밀번호가 이전 비밀번호와 일치합니다. 다시 입력해주세요.");
	return document.f.new_password.select();// 입력한 부분에 블록 설정되어 바로 수정 가능한 상태로 만듦
	
	}else	
		
	//'새 비밀번호' 와  '새 비밀번호 확인'가 일치하는지 확인하여 같게 입력하도록 유도
	if(document.f.new_password.value.trim() != document.f.confirm_password.value.trim()) {
	alert("새 비밀번호 확인이 새 비밀번호와 일치하지 않습니다. 다시 입력해주세요.");
	return document.f.confirm_password.select();// 입력한 부분에 블록 설정되어 바로 수정 가능한 상태로 만듦
	}
	
	
	// 위의 조건이 모두 거짓이면 = 유효성 검사를 만족하면
	document.f.submit();
	
}

</script>



</head>
<body>
	<form action="customerHashPwChangeAction.cus" method="post" name="f">
	<h2>비밀번호 변경</h2>
		<table>
			<!-- ★★ 주의 : 파라미터로 전송된 값은 반드시 param.으로 시작.  -->
			<%-- <input type="hidden" name="c_id" value="${param.c_id}" /> --%>
			
			<input type="hidden" name="c_id" value="<%=request.getParameter("c_id") %>" />
			<tr>
				<th>이전 비밀번호 입력</th>
				<td>
					<input type="password" name="pre_password" maxlength="12" placeholder="8~12자 영문과 숫자조합을 입력하세요." required="required" />					
				</td>
			</tr>
			
			<tr>
				<th>새로운 비밀번호 입력</th>
				<td>
					<input type="password" name="new_password" maxlength="12" placeholder="8~12자 영문과 숫자조합을 입력하세요." required="required" />					
				</td>
			</tr>
			
			<tr>
				<th>새로운 비밀번호 확인</th>
				<td>
					<input type="password" name="confirm_password" maxlength="12" placeholder="새로운 비밀번호와 동일하게 입력해주세요." required="required" />					
				</td>
			</tr>
			
			<tr>
				<th colspan="2">
					<input type="submit" value="변경" onclick="check(); return false;"/>
				</th>
			</tr>
			
		</table>
	</form>
</body>
</html>