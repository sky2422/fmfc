package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.customer.CustomerIdFindService;
import vo.ActionForward;
import vo.CustomerBean;

public class CustomerIdFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String c_email = request.getParameter("c_email");
		CustomerIdFindService customerIdFindService = new CustomerIdFindService();
		CustomerBean customerInfo = customerIdFindService.findId(c_email);
		
		if(customerInfo == null) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('존재하지 않는 계정입니다..')");
			out.println("location.href='customerLogin.cus'");//다시 '로그인 폼보기' 요청
			out.println("</script>");
		}else {//아이디가 있으면 
			String c_id = customerInfo.getC_id();
			request.setAttribute("c_id", c_id);
			request.setAttribute("showPage", "customer/findIdComplete.jsp");
			
			forward = new ActionForward("customerTemplate.jsp", false);
		}
		
		return forward;
	}

}
