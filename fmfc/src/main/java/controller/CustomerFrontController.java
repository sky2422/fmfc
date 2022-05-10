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

import vo.ActionForward;

/**
 * Servlet implementation class CustomerFrontController
 */
@WebServlet("*.cus")
public class CustomerFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerFrontController() {
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
			if(command.equals("/customerMain.cus")) { //'index.jsp에서 customerMain.jsp뷰페이지 보기' 요청이면
				forward = new ActionForward("customerMain.jsp", false);
			}
			  /*-----------------------'로그인 폼' 보기-----------------------*/
			else if(command.equals("/customerLogin.cus")) { //'로그인 폼 보기'요청이면
					request.setAttribute("showPage", "customer/customerLoginForm.jsp");
					forward = new ActionForward("customerTemplate.jsp", false);
				
			}else if(command.equals("/customerLoginAction.cus")) { //'로그인 처리'요청이면
				action = new CustomerLoginAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				/*----'id 찾기' 폼 보기 -> 처리---------------------------------*/	
			else if(command.equals("/customerIdFindForm.cus")) { //'아이디 찾기 폼 보기 '요청이면
				request.setAttribute("showPage", "customer/customerIdFind.jsp");
				forward = new ActionForward("customerTemplate.jsp", false);//요청을 그대로 전달하기 위해서 반드시 false
				
			}else if(command.equals("/customerIdFindAction.cus")) {// '아이디 찾기 처리'요청이면
				action = new CustomerIdFindAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*----'암호화된 비밀번호 찾기' 폼 보기 -> 처리---------------------------------*/	
			else if(command.equals("/customerHashPwFindForm.cus")) { //'비밀번호 찾기 폼보기 '요청이면
				request.setAttribute("showPage", "customer/customerHashPwFind.jsp");
				forward = new ActionForward("customerTemplate.jsp", false);//요청을 그대로 전달하기 위해서 반드시 false
				
			}else if(command.equals("/customerHashPwFindAction.cus")) {// '비밀번호 처리'요청이면
				action = new CustomerHashPwFindAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*----'암호화된 비밀번호 변경' 폼 보기 -> 처리---------------------------------*/	
			else if(command.equals("/customerHashPwChangeForm.cus")) { //'비밀번호 변경 폼보기 '요청이면
				request.setAttribute("showPage", "customer/customerHashPwChange.jsp");
				forward = new ActionForward("customerTemplate.jsp", false);//요청을 그대로 전달하기 위해서 반드시 false
				
			}else if(command.equals("/customerHashPwChangeAction.cus")) {// '비밀번호 변경 처리'요청이면
				action = new CustomerHashPwChangeAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*----'회원가입' 폼 보기 -> 처리---------------------------------*/	
			else if(command.equals("/customerJoin.cus")) { //'회원가입 폼보기 '요청이면
				request.setAttribute("showPage", "customer/customerJoinForm.jsp");
				forward = new ActionForward("customerTemplate.jsp", false);//요청을 그대로 전달하기 위해서 반드시 false
				
			}else if(command.equals("/customerJoinAction.cus")) {// '회원가입 처리'요청이면
				action = new CustomerJoinAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*----'로그아웃' -> 처리 후 customerMain.jsp ---------------------------------*/	
			else if(command.equals("/customerLogout.cus")) { //
				//세션 영역세 속성으로 제거하기 위해 Session객체를 얻와 
				HttpSession session = request.getSession();
				//session.invalidate();//세션의 모든 속성을 한번에 제거
				
				//한개씩 제거 
				session.removeAttribute("c_id");
				session.removeAttribute("c_password");
				session.removeAttribute("c_grade");
				session.removeAttribute("c_name");
				session.removeAttribute("c_email");
				
				forward = new ActionForward("customerMain.jsp", true);//요청을 그대로 전달하기 위해서 반드시 false
			}
			
			/*----'사용자 한명 상세 정보 폼 보기' -> 정보 수정 처리 ---------------------------------*/	
			else if(command.equals("/customerView.cus")) { //'회원 상세 정보 폼 보기 '요청이면
				//request.setAttribute("showPage", "customer/customerView.jsp");
				//forward = new ActionForward("customerTemplate.jsp", false);//요청을 그대로 전달하기 위해서 반드시 false
				action = new CustomerViewAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else if(command.equals("/customerUpdate.cus")) {// '사용자 한명 상세 정보 수정 '요청이면
				action = new CustomerUpdateAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*-------------------- 회원탈퇴 처리 -------------------------------*/
			else if(command.equals("/customerDelete.cus")) {// '회원탈퇴 '요청이면
				action = new CustomerDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*-------------------- [구단 소개] -------------------------------*/
			else if(command.equals("/info.cus")) {// '구단 소개'요청이면
				forward = new ActionForward("info.jsp", false);
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*-------------------- [경기 일정] -------------------------------*/
			else if(command.equals("/schedule.cus")) {// '경기 일정'요청이면
				
				request.setAttribute("showPage", "schedule.jsp");
				forward = new ActionForward("customerTemplate.jsp", false);
				
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
		