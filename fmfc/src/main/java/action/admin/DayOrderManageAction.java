package action.admin;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.admin.DayOrderManageService;
import vo.ActionForward;
import vo.Order;

public class DayOrderManageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		Date today = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// MM:월, mm:시간의 분
		String simpleDate_today = simpleDateFormat.format(today);
		int saleTotalMoney = 2500;
		HttpSession session = request.getSession();
		String a_grade = (String) session.getAttribute("a_grade");
		
		if(a_grade == null) {
			response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
			// ★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
			out.println("<script>");
			out.println("alert('관리자로 로그인 해주세요.')"); // 경고창을 띄우고
			out.println("location.href='adminLogin.adm'");
			out.println("</script>");
		}else {
			 DayOrderManageService  dayOrderManageService = new DayOrderManageService();
			 ArrayList<Order> dayOrderList = dayOrderManageService.getDayOrderList(simpleDate_today);//오늘 날짜로 주문한 리스트를 얻어와
			 
			 request.setAttribute("saleTotalMoney", saleTotalMoney);
			 request.setAttribute("dayOrderList", dayOrderList);
			 session.setMaxInactiveInterval(60*60*12);//12시간으로 시간 설정함 
			 
			 request.setAttribute("showAdmin", "/admin/dayOrderList.jsp");
			 forward = new ActionForward("adminMain.jsp", false);
			 
			 
		}
		
		
		
		return forward;
	}

}
