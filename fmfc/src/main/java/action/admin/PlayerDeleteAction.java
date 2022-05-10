package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.PlayerDeleteService;
import vo.ActionForward;

public class PlayerDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int back_no = Integer.parseInt(request.getParameter("back_no"));
		
		
		if(back_no >= 0) {// 등번호가 0번호보다 작은 선수들만 해당 
			PlayerDeleteService playerDeleteService = new PlayerDeleteService();
			boolean isPlayerDeleteSuccess = playerDeleteService.deletePlayer(back_no);
			
			if(isPlayerDeleteSuccess) {
				forward = new ActionForward("playerManage.adm", true);
			}else {
				response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
				// ★★ 주의 : jsp가 아니므로 직접 생성해야함
				PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
				out.println("<script>");
				out.println("alert('선수 삭제가 실패했습니다.')"); // 경고창을 띄우고
				out.println("history.back();"); //
				out.println("</script>");
			}
		}else {
			response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
			// ★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
			out.println("<script>");
			out.println("alert('삭제하려는 선수가 존재하지 않습니다.')"); // 경고창을 띄우고
			out.println("history.back();"); //
			out.println("</script>");
		}
		
		return forward;
	}

}
