package action.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class BoardWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("c_id");
		
		if(c_id == null) {
			response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
			// ★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
			out.println("<script>");
			out.println("alert('로그인 후 이용가능한 서비스입니다.')"); // 경고창을 띄우고
			out.println("location.href='customerLogin.cus';"); //다시 로그인 폼보기 요청 
			out.println("</script>");
		}else {
			
			request.setAttribute("board_showMenu", "board/boardWrite.jsp");
			forward = new ActionForward("boardTemplate.jsp", false);//디스패치방식
			
		
		}
		
		
		return forward;
	}

}
