package action.admin;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.admin.PlayerRegistService;
import svc.admin.PlayerUpdateService;
import svc.player.PlayerViewService;
import vo.ActionForward;
import vo.Player;

public class PlayerUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int maxSize = 1024*1024*5;
		
		String uploadFlder="/images";
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(uploadFlder);
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		//등록시에 기존 있는선수인지 아닌지는 등번호(back_no)로 판별한다.
		int back_no = Integer.parseInt(multi.getParameter("back_no"));
		//PlayerViewService PlayerviewService = new PlayerViewService();
		//Player checkPlayer = PlayerviewService.getPlayerView(back_no);
		
		String name = multi.getParameter("name");
		String position = multi.getParameter("position");
		String birth = multi.getParameter("birth");
				
		int height = Integer.parseInt(multi.getParameter("height"));
		int weight = Integer.parseInt(multi.getParameter("weight"));
		
		String team = multi.getParameter("team");
		
		String image = multi.getOriginalFileName("image");
		
		//if(checkPlayer == null) {//새로운 선수의 등번호 이면
			Player newPlayer = new Player(back_no, name, position, birth, height, weight, team, image);
			
			PlayerUpdateService playerUpdateService = new PlayerUpdateService();
			boolean isUpdatePlayerSuccess = playerUpdateService.updatePlayer(newPlayer);
			
		if(isUpdatePlayerSuccess) {	
			forward = new ActionForward("playerManage.adm", true);
		}else {
			response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
			// ★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
			out.println("<script>");
			out.println("alert('선수 수정이 실패했습니다.')"); // 경고창을 띄우고
			out.println("history.back();"); //
			out.println("</script>");
			}
		/*
		}else {//기존에 등록된 등번호의 선수이면 
			response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>");
	        out.println("alert('등록하실려는 등번호가 이미 존재합니다.')");
	        out.println("history.back();");
	        out.println("</script>");
		}
		*/
		
		return forward;	
		}

}
