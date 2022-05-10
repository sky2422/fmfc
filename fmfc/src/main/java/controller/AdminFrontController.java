package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.admin.AdminCustomerListAction;
import action.admin.AdminJoinAction;
import action.admin.AdminLoginAction;
import action.admin.DayOrderManageAction;

import action.admin.OrderCancelAction;
import action.admin.OrderDetailAction;
import action.admin.OrderGetAction;
import action.admin.PlayerAdminListAction;
import action.admin.PlayerDeleteAction;
import action.admin.PlayerManageAction;
import action.admin.PlayerRegistAction;
import action.admin.PlayerRegistFormAction;
import action.admin.PlayerUpdateAction;
import action.admin.PlayerUpdateFormAction;
import action.admin.ProductAddAction;
import action.admin.ProductAddFormAction;
import action.admin.ProductAdminListAction;
import action.admin.ProductDeleteAction;
import action.admin.ProductManageAction;
import action.admin.ProductUpdateAction;
import action.admin.ProductUpdateFormAction;
import action.admin.TotalOrderManageAction;

import vo.ActionForward;

/**
 * Servlet implementation class CustomerFrontController
 */
@WebServlet("*.adm")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFrontController() {
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
			
			System.out.println("[Admin]command : " + command);
			
			/*-----------------------'관리자 로그인 폼' 보기-----------------------*/
			if(command.equals("/adminLogin.adm")) { //'로그인 폼 보기'요청이면
					request.setAttribute("showAdmin", "admin/adminLoginForm.jsp");
					forward = new ActionForward("adminMain.jsp", false);
				
			}else if(command.equals("/adminLoginAction.adm")) { //'로그인 처리'요청이면
				action = new AdminLoginAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*----'회원가입' 폼 보기 -> 처리---------------------------------*/	
			else if(command.equals("/adminJoin.adm")) { //'관리자가입 폼보기 '요청이면
				request.setAttribute("showAdmin", "admin/adminJoinForm.jsp");
				forward = new ActionForward("adminMain.jsp", false);//요청을 그대로 전달하기 위해서 반드시 false
				
			}else if(command.equals("/adminJoinAction.adm")) {// '회원가입 처리'요청이면
				action = new AdminJoinAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*-----------------------'로그 아웃 처리' userMain.jsp 폼보기 처리-----------------------*/
		      else if (command.equals("/adminLogout.adm")) { // '로그아웃 폼보기' 요청이면
		         // 저장된 속성을 제거하기 위해 Session 객체를 얻어와
		         HttpSession session = request.getSession();
		         
		         //session.invalidate(); // 세션의 모든 속성을 제거 - 금지(세션에 저장된 사용자 속성도 제거되므로)
		         session.removeAttribute("a_id");
		         //session.removeAttribute("a_password");
		         session.removeAttribute("a_grade");
		         session.removeAttribute("a_name");
	
		         forward = new ActionForward("adminMain.jsp", true);
	
		      } 
		
			/* -------------------- '회원정보 관리' ----------------------------------------------- */
		      else if(command.equals("/adminCustomerList.adm")) { //회원들의 정보를 불러오기 
					action = new AdminCustomerListAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			/** [선수단 선수관리] - 시작 *********************************************************************************************/
			
			/*--[선수 관리] 클릭시 선수 추가 ------------------------------- */
				else if(command.equals("/playerManage.adm")) { 
					action = new PlayerManageAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			/* [선수단 선수관리 -> 선수관리] *************************************************************************/
				else if(command.equals("/playerAdminList.adm")) {// '선수관리' 요청이면
					/** 상품관리 요청은 Admin만 할 수 있도록 세션영역의 grade를 얻어와 확인 **/
					action = new PlayerAdminListAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			/*--[선수 관리] 클릭시 선수 추가 ------------------------------- */
				else if(command.equals("/playerRegistForm.adm")) { 
					action = new PlayerRegistFormAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
				else if(command.equals("/playerRegist.adm")) { 
					action = new PlayerRegistAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			/*--[선수 관리] 클릭시 선수 수정 ------------------------------- */
				else if(command.equals("/playerUpdateForm.adm")) {//[상품관리] 안에   => 상품 추가  => '상품 수정요청 폼 보기 요청' 이면
					action = new PlayerUpdateFormAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
				else if(command.equals("/playerUpdate.adm")) { 
					action = new PlayerUpdateAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			/*--[선수 관리] 클릭시 선수 삭제 ------------------------------- */
				else if(command.equals("/playerDelete.adm")) { 
					action = new PlayerDeleteAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			
			/*************************************************************************************************/
			
			/** [굿즈샵 상품관리 ] *************************************************************************/
			else if(command.equals("/productManage.adm")) {// '상품관리' 요청이면
				/** 상품관리 요청은 Admin만 할 수 있도록 세션영역의 grade를 얻어와 확인 **/
				action = new ProductManageAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/** [굿즈샵 상품관리 -> 상품관리] *************************************************************************/
			else if(command.equals("/productAdminList.adm")) {// '상품관리' 요청이면
				/** 상품관리 요청은 Admin만 할 수 있도록 세션영역의 grade를 얻어와 확인 **/
				action = new ProductAdminListAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/** [굿즈샵 상품관리 -> 상품추가] 안에   => 상품 추가 폼 보기 요청 -> 처리 *************************************************************************/
			else if(command.equals("/productAddForm.adm")) {//[상품관리] 안에   => 상품 추가 폼 보기 요청 이면
				action = new ProductAddFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/productAdd.adm")) {// [새 상품 추가 처리 요청] 이면
				action = new ProductAddAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/** [상품수정]    => 해당 '상품 수정' 폼 보기 요청(이 때, 해당 상품의 정보를 얻어와 폼에 셋팅) -> 상품 수정 '처리' 요청 *************************************************************************/
			else if(command.equals("/productUpdateForm.adm")) {//[상품관리] 안에   => 상품 추가  => '상품 수정요청 폼 보기 요청' 이면
				action = new ProductUpdateFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/productUpdate.adm")) {// [새 상품 추가 처리 요청] 이면
				action = new ProductUpdateAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/** [상품삭제]    => 상품 삭제 요청 처리 요청  *************************************************************************/
			else if(command.equals("/productDelete.adm")) {//[상품관리] 안에   => 상품 추가  => '상품 삭제' 처리 요청 이면
				action = new ProductDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/** [상품관리] 끝 *************************************************************************/
			
			
			/*** [실시간 주문관리 - 시작] ****************************************************************************/
			
			/*--[실시간 주문관리] 처리 요청 -------------------------------*/
			else if(command.equals("/dayOrderManage.adm")) {// 
				action = new DayOrderManageAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*--['실시간 주문관리, 전체 주문 관리'에서 ['주문번호]클릭시 상세정보'] 처리 요청 -------------------------------*/
			else if(command.equals("/orderDetail.adm")) {//  ['주문번호]클릭시 상세정보']요청 이면
				action = new OrderDetailAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*--['실시간 주문관리'에서 주문상태를(order_status)를 order(주문대기)에서 'get(주문승인)'으로 변경하기 위해서] 처리 요청 -------------------------------*/
			else if(command.equals("/orderGet.adm")) {// '주문 승인'요청 이면
				action = new OrderGetAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*--['실시간 주문관리'에서 주문상태를(order_status)를 order(주문대기)에서 'cancel(주문실패)'으로 변경하기 위해서] 처리 요청 -------------------------------*/
			else if(command.equals("/orderCancel.adm")) {// '주문 실패'요청 이면
				action = new OrderCancelAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
					
			/** [메뉴관리] - 끝 *************************************************************************/
			
			/* ************************************************************************************************/
			
			/** [전체주문/매출관리 - 시작] *************************************************************************/
			else if(command.equals("/totalOrderManage.adm")) {//[메뉴관리] 안에   => 메뉴 추가  => '메뉴 삭제' 처리 요청 이면
				action = new TotalOrderManageAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			/* ************************************************************************************************/		
			
			
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
		