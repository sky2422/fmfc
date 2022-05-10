package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.customer.CustomerDeleteService;
import vo.ActionForward;

public class CustomerDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
ActionForward forward = null;
		
		//[디자인-1] [회원탈퇴]메뉴가 따로 존재할 때
		//HttpSession session = request.getSession();
		//String c_id = (String)session.getAttribute("c_id");
		
		//[디자인-2] [회원탈퇴]메뉴가 따로 '회원정보관리'안에서 링크(get방식) - 파라미터로 전송 
		String c_id = request.getParameter("c_id");
		
		CustomerDeleteService customerDeleteService = new CustomerDeleteService();
		boolean customerDeleteSuccess = customerDeleteService.customerDelete(c_id);
		
		if(customerDeleteSuccess == false) {
			response.setContentType("text/html;charset=utf-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>");
	         out.println("alert('회원 탈되가 실패 되었습니다.');");
	         out.println("history.back();");
	         out.println("</script>");
			
		}else {//회원탈퇴에 성공하면 
			HttpSession session = request.getSession();
			
			//★★주의 : 세션의 모든 속성을 삭제  : 관리자 모드의 session도 함께 삭제되므로 금지함 
			//session.invalidate(); => 사용하면 안됨!
			
			//하나하나씩 속성을 삭제
			session.removeAttribute("c_id");
			session.removeAttribute("c_password");
			session.removeAttribute("c_grade");
			session.removeAttribute("c_name");
			
			session.removeAttribute("totalMoney");
			
			session.removeAttribute("cartList");
			session.removeAttribute("saleTotalMoney");
			session.removeAttribute("latestOrder");
			
			String cookieId = "";//★★ null로 초기화하면 안됨  
			Cookie cookie = new Cookie("c_id", cookieId);
			cookie.setMaxAge(0);//쿠키즉시삭제(쿠키는 삭제메서드를 제공하지 않음)
			//cookie.setMaxAge(-1);//세션이 끝나면 삭제(즉, 세션 유효범위인 "브라우저 종료할 때 삭제됨")
			response.addCookie(cookie);//★★반드시 클라이언트측으로 보내줘야한다!!
			
			response.setContentType("text/html;charset=utf-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>");
	         out.println("alert('회원 탈되에 성공했습니다. 그동안 감사했습니다.');");
	         out.println("location.href ='customerMain.jsp';");
	         out.println("</script>");
			
			
		}
		
	
		
		
		return forward;
	}

}
