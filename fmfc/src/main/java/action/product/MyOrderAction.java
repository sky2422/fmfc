package action.product;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.product.MyOrderService;
import vo.ActionForward;
import vo.Order;

public class MyOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		 String c_id = (String) session.getAttribute("c_id");
	     String c_email = (String) session.getAttribute("c_email");
	     
	      if(c_id == null) {
				response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
				// ★★ 주의 : jsp가 아니므로 직접 생성해야함
				PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
				out.println("<script>");
				out.println("alert('로그인 후 이용가능한 서비스입니다.')"); // 경고창을 띄우고
				out.println("location.href='customerLogin.cus';"); //다시 로그인 폼보기 요청 
				out.println("</script>");
			}else {
				MyOrderService myOrderService  = new MyOrderService();
				ArrayList<Order> myOrderList = myOrderService.getMyOrderList(c_id, c_email);
				
				
				request.setAttribute("myOrderList", myOrderList);
				request.setAttribute("showPage", "/customer/myOrderView.jsp");
				
				forward = new ActionForward("customerTemplate.jsp", false);
			}
	      
	      
	      
	      
		return forward;
	}

}
