package action.board;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.board.BoardDeleteService;
import svc.board.BoardDetailService;
import svc.board.BoardListService;
import vo.ActionForward;
import vo.Board;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		// 세션에 저장된 값 가져오기
		// 해당 아이디가 아닐시 삭제 수정 불가
		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("c_id");
		
		if(c_id == null || c_id.equals("c_id")) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			
			BoardDeleteService boardDeleteService= new BoardDeleteService();
			boolean isDeleteSuccess = boardDeleteService.removeArticle(board_num);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();				  
				out.println("<script>");//자바 스크립트로
				out.println("alert('삭제실패');");//삭제실패 알림창을 출력 후
				out.println("history.back()");//이전 페이지로 돌아가고
				out.println("</script>");
			}else {
				
				BoardListService boardListService = new BoardListService();
				ArrayList<Board> boardList = boardListService.getBoardList();
				
				request.setAttribute("boardList", boardList);
				request.setAttribute("board_showMenu", "/board/boardList.jsp");
				
				forward = new ActionForward("boardTemplate.jsp", false);//디스패치방식
			
			}
		
		}
		
		
		
		
		
		
		
		return forward;
	}

}
