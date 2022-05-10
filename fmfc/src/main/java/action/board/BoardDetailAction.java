package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.BoardDetailService;
import vo.ActionForward;
import vo.Board;


public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDetailService boardDetailService= new BoardDetailService();
		Board article = boardDetailService.getArticle(board_num);
		
		request.setAttribute("article", article);
		request.setAttribute("board_showMenu", "/board/boardview.jsp");
		
		forward = new ActionForward("boardTemplate.jsp", false);//디스패치방식
		return forward;
	}

}
