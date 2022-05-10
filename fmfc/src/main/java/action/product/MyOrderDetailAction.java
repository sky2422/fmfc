package action.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.product.MyOrderDetailService;
import vo.ActionForward;
import vo.OrderDetail;

public class MyOrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int order_num = Integer.parseInt(request.getParameter("order_num"));

		int totalMoney = Integer.parseInt(request.getParameter("totalMoney"));
		
		int saleTotalMoney =2500;
		
		MyOrderDetailService myOrderDetailService = new MyOrderDetailService();
		ArrayList<OrderDetail> myOrderDetailList = myOrderDetailService.getMyOrderDetailList(order_num);
		
		int productTotalMoney = 0;
		for(OrderDetail orderDetail:myOrderDetailList) {
			productTotalMoney += orderDetail.getP_price()*orderDetail.getQuantity();
		}
		
		request.setAttribute("saleTotalMoney", saleTotalMoney);
				
		request.setAttribute("myOrderDetailList", myOrderDetailList);
		request.setAttribute("totalMoney", totalMoney);

		request.setAttribute("productTotalMoney", productTotalMoney);
		
		request.setAttribute("showPage", "/customer/myOrderDetailView.jsp");
		
		forward = new ActionForward("customerTemplate.jsp", false);
		
		return forward;
	
	}

}
