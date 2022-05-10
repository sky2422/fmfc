package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.customer.CustomerDeleteAction;
import action.customer.CustomerHashPwChangeAction;
import action.customer.CustomerHashPwFindAction;
import action.customer.CustomerIdFindAction;
import action.customer.CustomerJoinAction;
import action.customer.CustomerLoginAction;
import action.customer.CustomerUpdateAction;
import action.customer.CustomerViewAction;
import action.player.PlayerListAction;
import action.player.PlayerViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class CustomerFrontController
 */
@WebServlet("*.pla")
public class PlayerFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);

    	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doProcess(request,response);
    }
	
	//이 서블릿으로 들어오는 모든 요청은 doProcess()를 호출하여 처리 
		protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");//반드시 첫줄
			
			//'프로젝트명+파일경로' 예)/project/doglist.dog
			String requestURI = request.getRequestURI();
			
			//예)/project
			String contextPath = request.getContextPath();
			
			/*
			 * /project/doglist.dog - /project = /dogList.dog
			 */
			String command = requestURI.substring(contextPath.length());//(index8 ~ 끝까지) 부분문자열 반환
			
			/*
			 * 컨트롤러에서 요청이 파악되면 해당 요청을 처리하는 각 Action클래스를 사용해서 요청 처리 
			 * 각 요청에 해당하는 Action클래스 객체들을 다형성을 이용해서 동일한 타입인 Action으로 참조하기 위해 
			 */
			Action action = null;//Action부모 인터페이스 = Action을 구현한 객체 
			ActionForward forward = null;
			/* 
			 * 
			 */
			
			System.out.println("[player] comman " + command);
			
			/*--[선수단] 클릭시------------------------------- */
			if(command.equals("/playerList.pla")) { 
				action = new PlayerListAction();
							
				try {
						forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*--[선수사진] 클릭시 선수정보 ------------------------------- */
			else if(command.equals("/playerView.pla")) { 
				action = new PlayerViewAction();
				
				try {
						forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
			/************************************************
			 * 포워딩 
			 ************************************************/
			if(forward != null) {
				if(forward.isRedirect() == true) {//리다이렉트 : 새요청, 기존 request 공유 못함 / isRedirect() == true 리다이렉트 방식()
					response.sendRedirect(forward.getPath());//"customerMain.jsp"
				}else {
					//RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
					//"기존요청, 기존응답" 그대로 보내므로  request공유
					//dispatcher.forward(request, response);
					
					request.getRequestDispatcher(forward.getPath()).forward(request, response);
				}
			}
	}
		
}
		