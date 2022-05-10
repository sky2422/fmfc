<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.* ,javax.sql.*, javax.naming.*"%>

<%
request.setCharacterEncoding("utf-8");
String idCheck = request.getParameter("idCheck");

if(idCheck != null && !idCheck.trim().equals("")){//항상 먼저, null여부 확인
	//--1. DB 연결-----------------------------------------------
	Connection con = null;
	PreparedStatement pstmt = null;
    ResultSet rs = null;
    
	String sql="select * from customer where c_id=?";
		
	try {
		Context initCtx=new InitialContext();//톰캣 자체의 Context객체 얻어와
		//Resource에 정의된 Context를 Object 타입으로 얻어와 Context타입으로 강제형변환(=다운캐스팅)
		Context envCtx=(Context)initCtx.lookup("java:comp/env");
		
		//name으로 커넥션 풀인 DataSource객체를 얻어와 
		DataSource ds=(DataSource)envCtx.lookup("jdbc/MySQLDB");
		
		con=ds.getConnection();//커넥션 풀(=DataSource)에서 Connection객체를 얻어와 
	
		//con.setAutoCommit(false);//★Connection객체의 트랜잭션을 자동커밋되지 않도록 하기 위해 
	
	//--2. sql문 실행 후 결과 ---------------------------------------
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, idCheck);
		rs = pstmt.executeQuery();
		
		if(rs.next()){ //같은 id가 있으면 리다이렉트 방식으로 요청함
			response.sendRedirect("idCheck.jsp?chk_id="+idCheck+"&useable=NO");
		}else{//같은 id가 없으면
			response.sendRedirect("idCheck.jsp?chk_id="+idCheck+"&useable=YES");			
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally{
	//--3. DB 해제 ----------------------------------------------
		try{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();			
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}	
	
}else{
	out.println("<script>");
	out.println("alert('중복을 확인할 아이디를 입력하세요.');");
	out.println("location.href='idCheck.jsp';");
	out.println("</script>");
}

%>





