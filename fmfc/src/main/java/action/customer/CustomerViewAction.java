package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.customer.CustomerViewService;
import vo.ActionForward;
import vo.Address;
import vo.CustomerBean;

public class CustomerViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forword = null;
		
		/*
		 * 현재 사용자가 로그인된 상태인지 알아보기 위해서 session객체를 얻어옴
		 */
		HttpSession session = request.getSession();
		String viewId= (String) session.getAttribute("c_id");
		
		if(viewId == null) {//현재 로그인된 상태가 아니면 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스입니다.');"); // 경고창을 띄우고
			out.println("location.href ='customerLogin.cus'"); // 다시 '로그인 폼보기'요청을 함
			out.println("</script>");
			
		}else {
			CustomerViewService customerViewService = new CustomerViewService();
			CustomerBean customerInfo = customerViewService.getCustomerInfo(viewId);
			Address customerAddrInfo = customerViewService.getAddressInfo(viewId);
			
			request.setAttribute("customer", customerInfo);
			request.setAttribute("addr", customerAddrInfo);
			request.setAttribute("showPage", "customer/customerView.jsp");
			
			forword = new ActionForward("customerTemplate.jsp", false);//디스패치 방식(기존요청을 그대로 공유할 수 있도록 한다.)
		}
		
		return forword;
	}

}
