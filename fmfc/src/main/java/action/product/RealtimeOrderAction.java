package action.product;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.product.ProductCartOrderService;
import vo.ActionForward;
import vo.Order;

public class RealtimeOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//session 영역에 공유한 '가장 마지막 주문내역' 삭제(생략? 아래 43라인에서 같은 속성명으로 덮어씌워 새로운 값으로 변경됨)
		
		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("c_id");
		
		if(c_id == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 해주세요.');");
			out.println("location.href='customerLogin.cus';");
			out.println("</script>");
		}else {
			/*
			 * 사용자가 가장 마지막 주문내역(latestOrder)을 조회한 결과를 반환받아
			 * (5초 사이에 [관리자모드]의 '실시간주문관리'메뉴에서 order_status를 수정한 결과를 반환)
			 */
			
			ProductCartOrderService productCartOrderService = new ProductCartOrderService();
			Order latestOrder = productCartOrderService.customerLastOrder(c_id);
			
			//다시 session영역에 같은 속성명인 latestOrder
			session.setAttribute("latestOrder", latestOrder);
			//successOrder.jsp로 돌아가 5초간격으로 계속 실행
			forward = new ActionForward("successOrder.jsp", true);
		}
		
		return forward;
	}

}
