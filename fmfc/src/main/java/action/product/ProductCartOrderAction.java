package action.product;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;

import svc.product.ProductCartOrderService;
import vo.ActionForward;
import vo.Cart;
import vo.Order;
import vo.Product;

public class ProductCartOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//파라미터로 전송된 totalMoney를 아래에서 세일된 값을 계산하기 위해
		int totalMoney = Integer.parseInt(request.getParameter("totalMoney"));
		
		//session영역에 공유한 '가장 마지막 주문내역'을 삭제하고 (아래에서 같은 속성명("latestOrder")으로 덮어씌우므로...)
		HttpSession session = request.getSession();
		//session.removeAttribute("latestOrder");//생략가능
		
		//cartList(장바구니 목록)객체를 얻어옴
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		if(cartList == null) {
			response.setContentType("text/html; charset=utf-8");//응답할 타입
			//★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter();//화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함
			out.println("<script>");
			out.println("alert('주문할 상품을 선택해주세요.')");//경고창을 띄우고
			out.println("history.back()");
			out.println("</script>");
			
		}else {//cartList(장바구니 목록) 있으면
			
			//cartList(장바구니 목록)에서 항목 하나씩 가져와서 Product객체를 생성 후
			ArrayList<Product> productList = new ArrayList<Product>();
			for(int i=0 ; i<cartList.size(); i++) {
				Cart cart = cartList.get(i);
				Product product = new Product(cart.getp_code(), cart.getCategory(), cart.getName(), cart.getPrice(), cart.getQty());
				productList.add(product);
			}
			
			/*
			 * session영역에 공유한 c_id와 c_email을 얻어와
			 * ★★ c_email을 얻어오는 이유?
			 * 향후 회원탈퇴 후 같은 id를 다른 사람이 사용할 수 있으므로 c_email을 포함시켜
			 * 아이디는 같아도 서로 다른 고객임을 구분하기 위해
			 */
				
			String c_id = (String) session.getAttribute("c_id");
			String c_email = (String) session.getAttribute("c_email");
			
			if(c_id == null) {
				response.setContentType("text/html; charset=utf-8");//응답할 타입
				//★★ 주의 : jsp가 아니므로 직접 생성해야함
				PrintWriter out = response.getWriter();//화면에 출력(자바이기 때문에 직접 출력 스트림을 생성해줘야함)
				out.println("<script>");
				out.println("alert('로그인 해주세요.')");//경고창을 띄우고
				out.println("location.href=customerLogin.cus';");
				out.println("</script>");
			}else {
				
				int saleTotalMoney = 2500;
				
				System.out.println("totalMoney : " + totalMoney + ", saleTotalMoney : " + saleTotalMoney);
				
				ProductCartOrderService productCartOrderService = new ProductCartOrderService();
				boolean isOrderProductSuccess = productCartOrderService.orderProduct(c_id, c_email, productList, totalMoney);
				
				if(!isOrderProductSuccess) {//주문 실패하면
					response.setContentType("text/html; charset=utf-8");//응답할 타dlq
					//★★주의 : jsp가 아니므로 직접 생성해야함
					PrintWriter out = response.getWriter();//화면에 출력(자바이기 때문에 직접 출력 스트림을 생성해줘야함)
					out.println("<script>");
					out.println("alert('주문실패')");//경고창 띄우고
					out.println("history.back();");//이전 상태로 돌아가고
					out.println("</script>");
					
				}else {//주문을 성공하면
					//session영역에 주문한 상세 내역(productList)을 추가하고
					session.setAttribute("productList", productList);
					session.setAttribute("totalMoney", totalMoney);
					session.setAttribute("saleTotalMoney", saleTotalMoney);
					
					//[구매하기]가 끝나면 session영역에 '장바구니 목록(cartList)'은 삭제해야 함
					session.removeAttribute("cartList");
					
					//c_id로 해당 사용자의 '가장 마지막 주문내역'을 조회한 결과를 반환
					Order latestOrder = productCartOrderService.customerLastOrder(c_id);
					//같은 속성명인 latestOrder로 덮어씌워 새로운 값으로 변경됨
					session.setAttribute("latestOrder", latestOrder);
					
					forward = new ActionForward("successOrder.jsp", true);//새요청
					
				}
			}
			
		}
		
		
		return forward;
	}

}

































