package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.product.MyOrderAction;
import action.product.MyOrderDetailAction;
import action.product.ProductCartAddAction;
import action.product.ProductCartListAction;
import action.product.ProductCartOrderAction;
import action.product.ProductCartQtyDownAction;
import action.product.ProductCartQtyUpAction;
import action.product.ProductCartRemoveAction;
import action.product.ProductCartRemoveAllAction;
import action.product.ProductListAction;
import action.product.ProductViewAction;
import action.product.RealtimeOrderAction;
import action.product.ProductAction;
import vo.ActionForward;

/**
 * Servlet implementation class CustomerFrontController
 */
@WebServlet("*.odr")
public class ProductFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductFrontController() {
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
			
			System.out.println("[Product] command " + command);
			

			  /*---[사용자 모드] [굿즈샵 클릭 시] 요청 -----------------------*/
			if(command.equals("/product.odr")) { //'굿즈샵  클릭'요청이면
				action = new ProductAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			else if(command.equals("/productList.odr")) { //'굿즈샵 상품 리스트' 요청이면
				action = new ProductListAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			/*---[사용자 모드] 굿즈샵에서 이미지 클릭시 '해당 이미지 상세보기'------------------*/
			else if(command.equals("/productView.odr")) { //사용자모드: 굿즈샵에서 이미지 클릭시 '해당 이미지 상세보기' 요청이면
				action = new ProductViewAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
	
			/*---[사용자 모드] 뷰페이지(productView.jsp)에서 '장바구니 담기' 클릭하면 -> ------------------*/
			else if(command.equals("/productCartAdd.odr")) { //사용자모드: '장바구니 담기'요청이면
				action = new ProductCartAddAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			/*---[사용자 모드] '장바구니 목록보기' 클릭하면 -> ------------------*/
			else if(command.equals("/productCartList.odr")) { //사용자모드: '장바구니 담기'요청이면
				action = new ProductCartListAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			/*---[사용자 모드] 'ProductCartList.jsp'에서 '▲수량 1증가' 요청하면 -> ------------------*/
			else if(command.equals("/productCartQtyUp.odr")) { //사용자모드: '▲ 수량 1증가 요청이면'
				action = new ProductCartQtyUpAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			/*---[사용자 모드] 'ProductCartList.jsp'에서 '▼ 수량 1감소' 요청하면 -> ------------------*/
			else if(command.equals("/productCartQtyDown.odr")) { //사용자모드: '▼ 수량 1감소 요청이면'
				action = new ProductCartQtyDownAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			/*---[사용자 모드] 'ProductCartList.jsp'에서 '전체 삭제' 요청하면 -> ------------------*/
			else if(command.equals("/productCartRemoveAll.odr")) { //사용자모드: '전체 삭제'
				action = new ProductCartRemoveAllAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			/*---[사용자 모드] 'ProductCartList.jsp'에서 '해당 상품 삭제' 요청하면 -> ------------------*/
			else if(command.equals("/productCartRemove.odr")) { //사용자모드: '해당 상품 삭제'
				action = new ProductCartRemoveAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
	
		
			/*---[사용자 모드] 'ProductCartList.jsp'에서 [구매하기] 요청하면 -> ------------------*/
			else if(command.equals("/productCartOrder.odr")) { //사용자모드: [구매하기] 요청이면
				action = new ProductCartOrderAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			/*---[사용자 모드] 'orderStatus.jsp'에서 '실시간 주문' 요청하면 -> ------------------*/
			else if(command.equals("/realtimeOrder.odr")) { //사용자모드: '실시간 주문' 요청이면
				action = new RealtimeOrderAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			/*---[사용자 모드] 'customerHeader.jsp'에서 '주문내역보기' 요청하면 -> ------------------*/
			else if(command.equals("/myOrder.odr")) { //사용자모드: '주문내역보기' 요청이면
				action = new MyOrderAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			/*---[사용자 모드] 'myOrderView.jsp'에서 '주문번호 클릭하면' '주문 상세정보보기'요청하면 -> ------------------*/
			else if(command.equals("/myOrderDetail.odr")) { //사용자모드: '주문내역보기' 요청이면
				action = new MyOrderDetailAction();
					
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
		