package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class PlayerManageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//메뉴관리 요청은 Admin만 할 수 있도록 세션영역의 grade를 얻와 확인 
		HttpSession session = request.getSession();
		String a_grade = (String)session.getAttribute("a_grade");
		if(a_grade == null || !a_grade.equalsIgnoreCase("Admin")) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('관리자로 로그인해주세요');");
			out.println("location.href = 'adminMain.jsp '");//다시 '로그인 폼보기' 요청
			out.println("</script>");
		}else {
			request.setAttribute("showAdmin", "admin/admin_template2.jsp");
			forward = new ActionForward("adminMain.jsp", false);
		}
		
		return forward;
	}

}
