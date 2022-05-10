package action.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.board.BoardDetailService;
import vo.ActionForward;
import vo.Board;


public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		// 세션에 저장된 값 가져오기
		// 해당 아이디가 아닐시 글 수정 불가
		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("c_id");
		
		if(c_id == null || c_id.equals("c_id")) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			
			BoardDetailService boardDetailService = new BoardDetailService();
			Board article = boardDetailService.getArticle(board_num);

			request.setAttribute("article", article);
			request.setAttribute("board_showMenu", "/board/boardmodify.jsp");
			
			forward = new ActionForward("boardTemplate.jsp", false);//디스패치방식
		}
		
		
		return forward;
	}

}
