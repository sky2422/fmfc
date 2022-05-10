package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.customer.CustomerHashPwService;
import util.SHA256;
import vo.ActionForward;
import vo.CustomerBean;

public class CustomerHashPwFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		 
		String c_id = request.getParameter("c_id");
		String c_email = request.getParameter("c_email");
		
		CustomerHashPwService customerHashPwService = new CustomerHashPwService();
		CustomerBean customerInfo = customerHashPwService.findHashPw(c_id, c_email);
		
		if(customerInfo == null) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('아이디 또는 이메일 정보가 일치하지 않습니다..')");
			out.println("history.back()");//다시 '로그인 폼보기' 요청
			out.println("</script>");
		}else {
			String ramdom_password = SHA256.getRamdomPassword(8);//8자리 랜덤비번을 생성하여 
			System.out.println("ramdom_password : " + ramdom_password);
			
			boolean isSetHashPwSuccess = customerHashPwService.setHashPw(c_id, c_email, ramdom_password);
			
			if(isSetHashPwSuccess == false) {//if(!isSetHashPwSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				
				PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
				out.println("<script>");
				out.println("alert('아이디 또는 이메일 정보가 일치하지 않습니다..')");
				out.println("history.back()");//다시 '로그인 폼보기' 요청
				out.println("</script>");
			}else {
				request.setAttribute("c_id", c_id);
				request.setAttribute("ramdom_password", ramdom_password);
				
				request.setAttribute("showPage", "customer/findHashPwComplete.jsp");
				
				forward = new ActionForward("customerTemplate.jsp", false);//디스패치 방식으로 포워딩 
			}
			
		}

		
		return forward;
	}

}
