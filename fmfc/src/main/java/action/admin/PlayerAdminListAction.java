package action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.player.PlayerListService;
import vo.ActionForward;
import vo.Player;

public class PlayerAdminListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		PlayerListService playerListService = new PlayerListService();
		ArrayList<Player> playerList = playerListService.getPlayerList();
		
		request.setAttribute("playerList", playerList);
		
		request.setAttribute("admin_showMenu" , "/admin/playerAdminList.jsp");
		request.setAttribute("showAdmin", "admin/admin_template2.jsp");
		forward = new ActionForward("adminMain.jsp", false);
		
		return forward;
	}

}
