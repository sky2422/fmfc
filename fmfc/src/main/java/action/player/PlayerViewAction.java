package action.player;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.player.PlayerViewService;
import vo.ActionForward;
import vo.Player;

public class PlayerViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int back_no = Integer.parseInt(request.getParameter("back_no"));
		
		PlayerViewService playerViewService = new PlayerViewService();
		Player player = playerViewService.getPlayerView(back_no);
		
		request.setAttribute("player", player);
		
		
		request.setAttribute("player_showMenu", "player/playerView.jsp");
		
		forward = new ActionForward("playerTemplate.jsp", false);//디스패치방식
		
		return forward;
	}

}
