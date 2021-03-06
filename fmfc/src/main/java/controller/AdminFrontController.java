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
	
	//??? ??????????????? ???????????? ?????? ????????? doProcess()??? ???????????? ?????? 
		protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");//????????? ??????
			
			//'???????????????+????????????' ???)/project/doglist.dog
			String requestURI = request.getRequestURI();
			
			//???)/project
			String contextPath = request.getContextPath();
			
			/*
			 * /project/doglist.dog - /project = /dogList.dog
			 */
			String command = requestURI.substring(contextPath.length());//(index8 ~ ?????????) ??????????????? ??????
			
			/*
			 * ?????????????????? ????????? ???????????? ?????? ????????? ???????????? ??? Action???????????? ???????????? ?????? ?????? 
			 * ??? ????????? ???????????? Action????????? ???????????? ???????????? ???????????? ????????? ????????? Action?????? ???????????? ?????? 
			 */
			Action action = null;//Action?????? ??????????????? = Action??? ????????? ?????? 
			ActionForward forward = null;
			/* 
			 * 
			 */
			
			System.out.println("[Admin]command : " + command);
			
			/*-----------------------'????????? ????????? ???' ??????-----------------------*/
			if(command.equals("/adminLogin.adm")) { //'????????? ??? ??????'????????????
					request.setAttribute("showAdmin", "admin/adminLoginForm.jsp");
					forward = new ActionForward("adminMain.jsp", false);
				
			}else if(command.equals("/adminLoginAction.adm")) { //'????????? ??????'????????????
				action = new AdminLoginAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*----'????????????' ??? ?????? -> ??????---------------------------------*/	
			else if(command.equals("/adminJoin.adm")) { //'??????????????? ????????? '????????????
				request.setAttribute("showAdmin", "admin/adminJoinForm.jsp");
				forward = new ActionForward("adminMain.jsp", false);//????????? ????????? ???????????? ????????? ????????? false
				
			}else if(command.equals("/adminJoinAction.adm")) {// '???????????? ??????'????????????
				action = new AdminJoinAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*-----------------------'?????? ?????? ??????' userMain.jsp ????????? ??????-----------------------*/
		      else if (command.equals("/adminLogout.adm")) { // '???????????? ?????????' ????????????
		         // ????????? ????????? ???????????? ?????? Session ????????? ?????????
		         HttpSession session = request.getSession();
		         
		         //session.invalidate(); // ????????? ?????? ????????? ?????? - ??????(????????? ????????? ????????? ????????? ???????????????)
		         session.removeAttribute("a_id");
		         //session.removeAttribute("a_password");
		         session.removeAttribute("a_grade");
		         session.removeAttribute("a_name");
	
		         forward = new ActionForward("adminMain.jsp", true);
	
		      } 
		
			/* -------------------- '???????????? ??????' ----------------------------------------------- */
		      else if(command.equals("/adminCustomerList.adm")) { //???????????? ????????? ???????????? 
					action = new AdminCustomerListAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			/** [????????? ????????????] - ?????? *********************************************************************************************/
			
			/*--[?????? ??????] ????????? ?????? ?????? ------------------------------- */
				else if(command.equals("/playerManage.adm")) { 
					action = new PlayerManageAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			/* [????????? ???????????? -> ????????????] *************************************************************************/
				else if(command.equals("/playerAdminList.adm")) {// '????????????' ????????????
					/** ???????????? ????????? Admin??? ??? ??? ????????? ??????????????? grade??? ????????? ?????? **/
					action = new PlayerAdminListAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			/*--[?????? ??????] ????????? ?????? ?????? ------------------------------- */
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
			
			/*--[?????? ??????] ????????? ?????? ?????? ------------------------------- */
				else if(command.equals("/playerUpdateForm.adm")) {//[????????????] ??????   => ?????? ??????  => '?????? ???????????? ??? ?????? ??????' ??????
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
			
			/*--[?????? ??????] ????????? ?????? ?????? ------------------------------- */
				else if(command.equals("/playerDelete.adm")) { 
					action = new PlayerDeleteAction();
					
					try {
							forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			
			/*************************************************************************************************/
			
			/** [????????? ???????????? ] *************************************************************************/
			else if(command.equals("/productManage.adm")) {// '????????????' ????????????
				/** ???????????? ????????? Admin??? ??? ??? ????????? ??????????????? grade??? ????????? ?????? **/
				action = new ProductManageAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/** [????????? ???????????? -> ????????????] *************************************************************************/
			else if(command.equals("/productAdminList.adm")) {// '????????????' ????????????
				/** ???????????? ????????? Admin??? ??? ??? ????????? ??????????????? grade??? ????????? ?????? **/
				action = new ProductAdminListAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/** [????????? ???????????? -> ????????????] ??????   => ?????? ?????? ??? ?????? ?????? -> ?????? *************************************************************************/
			else if(command.equals("/productAddForm.adm")) {//[????????????] ??????   => ?????? ?????? ??? ?????? ?????? ??????
				action = new ProductAddFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/productAdd.adm")) {// [??? ?????? ?????? ?????? ??????] ??????
				action = new ProductAddAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/** [????????????]    => ?????? '?????? ??????' ??? ?????? ??????(??? ???, ?????? ????????? ????????? ????????? ?????? ??????) -> ?????? ?????? '??????' ?????? *************************************************************************/
			else if(command.equals("/productUpdateForm.adm")) {//[????????????] ??????   => ?????? ??????  => '?????? ???????????? ??? ?????? ??????' ??????
				action = new ProductUpdateFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/productUpdate.adm")) {// [??? ?????? ?????? ?????? ??????] ??????
				action = new ProductUpdateAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/** [????????????]    => ?????? ?????? ?????? ?????? ??????  *************************************************************************/
			else if(command.equals("/productDelete.adm")) {//[????????????] ??????   => ?????? ??????  => '?????? ??????' ?????? ?????? ??????
				action = new ProductDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/** [????????????] ??? *************************************************************************/
			
			
			/*** [????????? ???????????? - ??????] ****************************************************************************/
			
			/*--[????????? ????????????] ?????? ?????? -------------------------------*/
			else if(command.equals("/dayOrderManage.adm")) {// 
				action = new DayOrderManageAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*--['????????? ????????????, ?????? ?????? ??????'?????? ['????????????]????????? ????????????'] ?????? ?????? -------------------------------*/
			else if(command.equals("/orderDetail.adm")) {//  ['????????????]????????? ????????????']?????? ??????
				action = new OrderDetailAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*--['????????? ????????????'?????? ???????????????(order_status)??? order(????????????)?????? 'get(????????????)'?????? ???????????? ?????????] ?????? ?????? -------------------------------*/
			else if(command.equals("/orderGet.adm")) {// '?????? ??????'?????? ??????
				action = new OrderGetAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*--['????????? ????????????'?????? ???????????????(order_status)??? order(????????????)?????? 'cancel(????????????)'?????? ???????????? ?????????] ?????? ?????? -------------------------------*/
			else if(command.equals("/orderCancel.adm")) {// '?????? ??????'?????? ??????
				action = new OrderCancelAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
					
			/** [????????????] - ??? *************************************************************************/
			
			/* ************************************************************************************************/
			
			/** [????????????/???????????? - ??????] *************************************************************************/
			else if(command.equals("/totalOrderManage.adm")) {//[????????????] ??????   => ?????? ??????  => '?????? ??????' ?????? ?????? ??????
				action = new TotalOrderManageAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			/* ************************************************************************************************/		
			
			
			/************************************************
			 * ????????? 
			 ************************************************/
			if(forward != null) {
				if(forward.isRedirect() == true) {//??????????????? : ?????????, ?????? request ?????? ?????? / isRedirect() == true ??????????????? ??????()
					response.sendRedirect(forward.getPath());//"customerMain.jsp"
				}else {
					//RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
					//"????????????, ????????????" ????????? ????????????  request??????
					//dispatcher.forward(request, response);
					
					request.getRequestDispatcher(forward.getPath()).forward(request, response);
				}
			}
	}
		
}
		