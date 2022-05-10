package action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.OrderDetailService;
import svc.customer.CustomerViewService;
import vo.ActionForward;
import vo.Address;
import vo.OrderDetail;
import vo.CustomerBean;

public class OrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//주문 번호 '주문 상세 정보 리스트'를 얻어와 request에 속성값으로 공유 
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		OrderDetailService orderDetailService = new OrderDetailService();
		ArrayList<OrderDetail> orderDetailList = orderDetailService.getOrderDetailList(order_num);
		
		//request.setAttribute("orderDetailList", orderDetailList);
		
		//c_id로 '회원정보와 주소정보'를 얻어와 request에 속성값으로 공유
		
		String c_id = request.getParameter("c_id");
		CustomerViewService customerViewService = new CustomerViewService();
		CustomerBean customerInfo = customerViewService.getCustomerInfo(c_id);//회원정보
		
		Address customerAddrInfo = customerViewService.getAddressInfo(c_id);//회원주소정보
		
		//request에 속성값으로 공유
		request.setAttribute("orderDetailList", orderDetailList);
		
		request.setAttribute("customerInfo", customerInfo);
		request.setAttribute("customerAddrInfo", customerAddrInfo);
		
		request.setAttribute("showAdmin", "/admin/orderDetailList.jsp");
		
		forward = new ActionForward("adminMain.jsp", false);
		
		
		
		
		return forward;
	}

}
