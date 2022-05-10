package action.admin;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.admin.MonthSalesManageService;
import svc.admin.TotalOrderManageService;
import vo.ActionForward;
import vo.Order;

public class TotalOrderManageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		/* 전체 주문관리 요청은 Admin만 할 수 있도록 */
		HttpSession session = request.getSession();
		String a_grade = (String) session.getAttribute("a_grade");
		int saleTotalMoney = 2500;
		
		
		
		
		if(a_grade == null) {//로그인이 안되있음
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('관리자로 로그인해주세요');");
			out.println("location.href ='adminLogin.adm'");//다시 '로그인 폼보기' 요청
			out.println("</script>");
		}else if(!a_grade.equalsIgnoreCase("Admin")){//로그인은 되었는데 Admin이 아니면 
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('관리자만 사용가능합니다.');");
			out.println("location.href ='adminLogin.adm'");//다시 '로그인 폼보기' 요청
			out.println("</script>");
		}else {
	         TotalOrderManageService totalOrderManageService = new TotalOrderManageService();
	         ArrayList<Order> totalOrderList = totalOrderManageService.getTotalOrderList();// 전체 주문한 리스트를 얻어와
	         
	         int OrderList = totalOrderManageService.getOrderList();//관리자 모드에서 [전체주문/매출조회]에서 전체 Order(주문대기) 개수 구하기
	         int OrderCancelList = totalOrderManageService.getOrderCancelList();//관리자 모드에서 [전체주문/매출조회]에서 전체 OrderCancel(주문 취소) 개수 구하기
	         
			
			 MonthSalesManageService monthSalesManageService = new MonthSalesManageService();
			 int MonthTotalMoney = monthSalesManageService.getMonthTotalMoney();//승인된 한달 총매출 조회
	         
			 request.setAttribute("MonthTotalMoney", MonthTotalMoney);
			 
	         request.setAttribute("OrderList", OrderList);
	         request.setAttribute("OrderCancelList", OrderCancelList);
	         
	   
	         request.setAttribute("saleTotalMoney", saleTotalMoney);
	         request.setAttribute("totalOrderList", totalOrderList);
	         request.setAttribute("showAdmin", "/admin/totalOrderList.jsp");
	         
	         forward = new ActionForward("adminMain.jsp", false);
		}
		
	
		
		return forward;
	}

}
