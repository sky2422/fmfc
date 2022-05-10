package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;

import svc.customer.CustomerLoginService;
import vo.ActionForward;
import vo.CustomerBean;

public class CustomerLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		
		String c_id = request.getParameter("c_id"); // request로부터 얻어온 c_id값을 저장
		String c_password = request.getParameter("c_password");
		
		String remember = request.getParameter("remember"); // 아이디 저장 체크여부를 확인 -> 쿠키객체 생성 여부를 사용함(체크 : 쿠키객체 생성, 체크X : 쿠키객체 생성X)
		
		// 로그인 폼 페이지에서 파라미터 전송된 id와 비번값을 저장할 CustomerBean객체 생성 (따로따로 보내는 것이 아니라 CustomerBean객체에 담아 한꺼번에 DAO로 전송함)
		CustomerBean customer = new CustomerBean(); // 기본값으로 채워진 CustomerBean객체 생성
		customer.setC_id(c_id);
		 customer.setC_password(c_password); // 암호화X(DB안에는 이미 암호화된 상태로 저장되어있기 때문에 암호화를 해야지 DB값과 비교할 수 있기 때문)
		//customer.setC_password(SHA256.encodeSHA256(c_password)); // 암호화 O (사용자가 입력한 암호를 암호화시켜 DB안의 암호화된 비번과 비교) (DB안에는 이미 암호화된 상태로 저장되어있기 때문)
		
		// 로그인 처리를 위한 Service객체를 생성하여
		CustomerLoginService customerLoginService = new CustomerLoginService();
		boolean isloginSuccess = customerLoginService.login(customer); // boolean타입으로 전달 받음
		
		CustomerBean customerInfo = null;
		
		// if(!isloginSuccess){
		if(isloginSuccess == false) { // 로그인에 실패하면
			response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
			
			// ★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)
			
			out.println("<script>");
			out.println("alert('아이디나 비밀번호가 일치하지 않습니다.')"); // 경고창을 띄우고
			out.println("location.href ='customerLogin.cus';"); // 다시 '로그인 폼보기'요청을 함
			out.println("</script>");
			
		}else { // 로그인에 성공하면 
			// 회원의 정보를 가져옴
			customerInfo = customerLoginService.getCustomerInfo(c_id);
			
			if(customerInfo == null) {
				response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
				
				// ★★ 주의 : jsp가 아니므로 직접 생성해야함
				PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)
				
				out.println("<script>");
				out.println("alert('존재하지 않는 계정입니다.')"); // 경고창을 띄우고
				out.println("location.href ='customerLogin.cus'"); // 다시 '로그인 폼보기'요청을 함
				out.println("</script>");
			}else { // 회원이면
				Cookie cookie = new Cookie("c_id", c_id);
				System.out.println("Cookie객체 생성");
				
				if(remember != null) { // '아이디 저장'에 체크했으면
					response.addCookie(cookie); // 응답할때 응답객체에 쿠키와 함께 보냄
					
				}else { // '아이디 저장'에 체크 안했으면
					cookie.setMaxAge(0); // 쿠키는 삭제 메서드가 없기때문에 유효시간을 0으로 셋팅해줌 (쿠키를 삭제한 효과)
					// cookie.setMaxAge(-1); // 세션이 끝나면 쿠키가 삭제됨 (세션유지시간 1시간으로 설정했음 (WEB-INF의 web.xml에 코드를 추가했음))
					response.addCookie(cookie); // 응답할때 응답객체에 쿠키와 함께 보냄
				}
				
				/* ------------------------------------------------------ */
			
				
				//CustomerGradeService customerGradeService = new CustomerGradeService();
				//customerInfo = customerGradeService.updateGrade(customerInfo); // 경고창 -> 이유? 객체를 생성하여 호출할 필요 없음 (static이기 때문에 바로 사용 가능)
				

				
				//세션 영역세 속성으로 저장하기 위해 Session객체를 생성 
				HttpSession session = request.getSession();
				session.setAttribute("c_id", c_id);
				session.setAttribute("c_password", c_password);
				
				session.setAttribute("c_grade", customerInfo.getC_grade());
				session.setAttribute("c_name", customerInfo.getC_name());
				session.setAttribute("c_email", customerInfo.getC_email());
				
				// WEB-INF > web.xml 에 설정가능 (이미했음)
				session.setMaxInactiveInterval(1*60*60);// 세션 수명시간을 1시간으로 설정(3600초=1시간)
				
				// 필요한 정보들을 session에 저장했으므로 리다이렉트(true)방식으로 포워딩해도 무방하다.
				forward = new ActionForward("customerMain.jsp", true);
				
			}
		}
		
		return forward;
	}

}
