package action.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;

import svc.board.BoardModifyService;
import vo.ActionForward;
import vo.Board;


public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;	
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		String c_id = request.getParameter("c_id");		
		String board_subject = request.getParameter("board_subject");
		String board_content = request.getParameter("board_content");
		String board_file = request.getParameter("board_file");
		
		Board article = new Board(board_num, c_id, board_subject, board_content, board_file);
		
		BoardModifyService boardModifyService = new BoardModifyService();
		boolean isModifySuccess = boardModifyService.modifyArticle(article);
	
		if(!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();				  
			out.println("<script>");//자바 스크립트로
			out.println("alert('수정실패');");//수정실패 알림창을 출력 후
			out.println("history.back()");//이전 페이지로 돌아가고
			out.println("</script>");
		}else {
			
			request.setAttribute("article", article);
			request.setAttribute("board_showMenu", "/board/boardview.jsp");
			
			forward = new ActionForward("boardTemplate.jsp", false);//디스패치방식
		}

	
		
		
		
		return forward;
	}

}
