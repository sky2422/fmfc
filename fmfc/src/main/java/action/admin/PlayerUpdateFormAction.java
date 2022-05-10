package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.player.PlayerViewService;
import vo.ActionForward;
import vo.Player;

public class PlayerUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int back_no = Integer.parseInt(request.getParameter("back_no"));
		
		PlayerViewService playerViewService = new PlayerViewService();
		Player player_update = playerViewService.getPlayerView(back_no);
		
		request.setAttribute("player_update", player_update);
		
		
		request.setAttribute("admin_showMenu" , "/admin/playerUpdateForm.jsp");
		request.setAttribute("showAdmin", "admin/admin_template2.jsp");
		
		forward = new ActionForward("adminMain.jsp", false);
		
		return forward;
	}

}
