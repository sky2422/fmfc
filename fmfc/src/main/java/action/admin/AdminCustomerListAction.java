package action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.AdminCustomerListService;
import svc.customer.CustomerViewService;
import vo.ActionForward;
import vo.Address;
import vo.CustomerBean;

public class AdminCustomerListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		AdminCustomerListService adminCustomerListService = new AdminCustomerListService();
		
		List<CustomerBean> customerListInfo = adminCustomerListService.getCustomerListInfo();//회원 정보
		List<Address> customerAddrListInfo = adminCustomerListService.getCustomerAddrListInfo();//회원주소 정보 
				
		request.setAttribute("customerListInfo", customerListInfo);
		request.setAttribute("customerAddrListInfo", customerAddrListInfo);
		
		request.setAttribute("showAdmin", "/admin/adminCustomerList.jsp");
		forward = new ActionForward("adminMain.jsp", false);
		
		return forward;
	}

}
