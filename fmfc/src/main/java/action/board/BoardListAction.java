package action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.BoardListService;
import vo.ActionForward;
import vo.Board;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		BoardListService boardListService = new BoardListService();
		ArrayList<Board> boardList = boardListService.getBoardList();
		
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("board_showMenu", "/board/boardList.jsp");
		
		forward = new ActionForward("boardTemplate.jsp", false);//디스패치방식
		
		return forward;
	}

}
