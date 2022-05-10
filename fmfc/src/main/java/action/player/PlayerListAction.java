package action.player;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.player.PlayerListService;
import vo.ActionForward;
import vo.Player;

public class PlayerListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
	
		PlayerListService playerListService = new PlayerListService();
		ArrayList<Player> playerList = playerListService.getPlayerList();
		
		request.setAttribute("playerList", playerList);
		
	
		request.setAttribute("player_showMenu", "player/playerList.jsp");
		
		forward = new ActionForward("playerTemplate.jsp", false);//디스패치방식
		
		return forward;
	}

}
