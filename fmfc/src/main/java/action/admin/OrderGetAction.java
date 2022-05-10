package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.OrderGetService;
import vo.ActionForward;

public class OrderGetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//주문번호로 해당주문을 찾아서 order -> get 상태로 변경 
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		OrderGetService orderGetService = new OrderGetService();
		boolean isOrderGetSuccess = orderGetService.orderGet(order_num);
		
		if(!isOrderGetSuccess) {
			response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
			// ★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
			out.println("<script>");
			out.println("alert('주문승인 실패');"); // 경고창을 띄우고
			out.println("history.back()"); 
			out.println("</script>");
		}else {//get(주문승인)상태로 변경 성공하면
			forward = new ActionForward("dayOrderManage.adm", true);//리다이렉트 방식으로 다시 '실시간 주문 관리'요청
		}
	
		return forward;
	}

}
