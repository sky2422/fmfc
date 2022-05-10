package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.admin.AdminLoginService;

import vo.ActionForward;
import vo.CustomerBean;


public class AdminLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionForward forward = null;
		
		
		String a_id = request.getParameter("c_id"); // request로부터 얻어온 c_id값을 저장
		String a_password = request.getParameter("c_password");
		
		String remember = request.getParameter("remember"); // 아이디 저장 체크여부를 확인 -> 쿠키객체 생성 여부를 사용함(체크 : 쿠키객체 생성, 체크X : 쿠키객체 생성X)
		
		// 로그인 폼 페이지에서 파라미터 전송된 id와 비번값을 저장할 CustomerBean객체 생성 (따로따로 보내는 것이 아니라 CustomerBean객체에 담아 한꺼번에 DAO로 전송함)
		CustomerBean admin = new CustomerBean(); // 기본값으로 채워진 CustomerBean객체 생성
		admin.setC_id(a_id);
		admin.setC_password(a_password); // 암호화X(DB안에는 이미 암호화된 상태로 저장되어있기 때문에 암호화를 해야지 DB값과 비교할 수 있기 때문)
		//admin.setC_password(SHA256.encodeSHA256(a_password)); // 암호화 O (사용자가 입력한 암호를 암호화시켜 DB안의 암호화된 비번과 비교) (DB안에는 이미 암호화된 상태로 저장되어있기 때문)
		
		//★★관리자 모드 : 먼저, 관리자정보를 얻어와 c_grade가 Admin인지 확인해야 함 
		// 로그인 처리를 위한 Service객체를 생성하여
		AdminLoginService adminLoginService = new AdminLoginService();
		CustomerBean adminInfo = adminLoginService.getAdminInfo(a_id);
		
		if(adminInfo == null) {
			response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
			
			// ★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)
			
			out.println("<script>");
			out.println("alert('존재하지 않는 계정입니다.')"); // 경고창을 띄우고
			out.println("location.href ='adminLogin.adm'"); // 다시 '로그인 폼보기'요청을 함
			out.println("</script>");
		}else {
			if(adminInfo.getC_grade().equalsIgnoreCase("Admin")) {
				//로그인 요청을 처리하는 login()호출(이 떄, 매개값 : 로그인 정보가 저장된 CustomerBean객체 )
				boolean isloginSuccess = adminLoginService.login(admin); // boolean타입으로 전달 받음
				
				if(isloginSuccess == false) {
					response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
					
					// ★★ 주의 : jsp가 아니므로 직접 생성해야함
					PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)
					
					out.println("<script>");
					out.println("alert('아이디나 비밀번호가 일치하지 않습니다.')"); // 경고창을 띄우고
					out.println("location.href ='customerLogin.cus';"); // 다시 '로그인 폼보기'요청을 함
					out.println("</script>");
				}else {
					Cookie cookie = new Cookie("a_id", a_id);
					System.out.println("관리자 Cookie 객체 생성");
					
					if(remember != null) {//아이디 저장에 체크했으면
				
					response.addCookie(cookie);
				}else {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
					
				HttpSession session = request.getSession();
				session.setAttribute("a_id", a_id);
				//session.setAttribute("a_password", a_password);
				
				session.setAttribute("a_grade", adminInfo.getC_grade());
				session.setAttribute("a_name", adminInfo.getC_name());
				
				session.setMaxInactiveInterval(24*60*60);// 세션 수명시간을 24시간으로 설정(3600초=1시간)
				
				forward = new ActionForward("adminMain.jsp", false);
			}
		
		}else {//등급이 Admin이 아니면 
			response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
			
			// ★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)
			
			out.println("<script>");
			out.println("alert('관리자 권한이 없습니다.')"); // 경고창을 띄우고
			out.println("location.href ='adminLogin.adm'"); // 다시 '로그인 폼보기'요청을 함
			out.println("</script>");
			}
		}
		
		return forward;
	}

}
