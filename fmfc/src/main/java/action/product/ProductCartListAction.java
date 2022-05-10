package action.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.product.ProductCartListService;
import vo.ActionForward;
import vo.Cart;
import vo.Product;

public class ProductCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ProductCartListService productCartListService = new ProductCartListService();
		ArrayList<Cart> cartList = productCartListService.getCartList(request);
		
		/*--[지불할 총 금액 계산]-------------------------------*/
		 int totalMoney = 0;
		 
		 if(cartList != null) {
				for(int i=0; i<cartList.size(); i++) {
					totalMoney += cartList.get(i).getPrice() * cartList.get(i).getQty();
				}
			}
		 
		//로그인된 상태동안 공유할 수 있도록 request에 담지않고 session영역에 저장함.
		HttpSession session = request.getSession();
		session.setAttribute("totalMoney", totalMoney);
		session.setAttribute("cartList", cartList);
		
		request.setAttribute("showCart", "/order/productCartList.jsp");
		
		forward = new ActionForward("productTemplate.jsp", false);
		
		return forward;
	}

}
