package svc.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;


public class ProductCartQtyUpService {

	public void upCartQty(String p_code, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		//장바구니 목록에서 해당 p_code를 찾아 수량 1 증가
		for(int i=0; i<cartList.size(); i++) {
			if(p_code.equalsIgnoreCase(cartList.get(i).getp_code())) {
				cartList.get(i).setQty(cartList.get(i).getQty()+1);
				break;
			}
		}
	}

}
