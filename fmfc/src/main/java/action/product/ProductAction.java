package action.product;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class ProductAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String c_id = (String)session.getAttribute("c_id");
		
		if(c_id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('로그인 후 이용해 주세요.');");
			out.println("location.href = 'customerLogin.cus';");//'로그인 폼보기' 요청
			out.println("</script>");
		}else {
			forward = new ActionForward("productTemplate.jsp", false);
		}
		
		
		
		
		
		
		
		return forward;
	}

}
