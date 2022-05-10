package action.board;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.board.BoardWriteService;
import vo.ActionForward;
import vo.Board;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("c_id");
		
		//먼저, boardUpload 폴더 만들기
		//파일이 업로드 될 서버상의 실제 디렉토리 경로
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath("/boardUpload");
		
		int fileSize = 5*1024*1024;//한 번에 업로드할 수 있는 파일크기 5M
		
		MultipartRequest multi=new MultipartRequest(request, realFolder,fileSize,"UTF-8", new DefaultFileRenamePolicy());
		
		//새로 등록할 글 정보를 저장한 BoardBean 객체 생성
		Board board = new Board(multi.getParameter("c_id"),
								multi.getParameter("board_subject"),
								multi.getParameter("board_content"),
								multi.getOriginalFileName((String)multi.getFileNames().nextElement()
								));	
		
		BoardWriteService boardWriteService = new BoardWriteService();
		boolean isWriteSuccess = boardWriteService.registArticle(board);
		
		
		if(!isWriteSuccess) {//실패
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
		}else {//성공
			//'글 전체 목록보기' 요청하면 다시 프론트컨트롤러로 이동하여 처리함
			forward = new ActionForward("boardList.bo", true);//새요청
		}

				
		
		return forward;
	}

}
