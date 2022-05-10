package svc.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;
import vo.Product;

public class ProductCartQtyDownService {

	public void downCartQty(String p_code, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		//장바구니 목록에서 해당 p_code를 찾아 수량 1 감소
		for(int i=0; i<cartList.size();i++){
			if(p_code.equalsIgnoreCase(cartList.get(i).getp_code()) && cartList.get(i).getQty() >0) {
				cartList.get(i).setQty(cartList.get(i).getQty()-1);
				break;
			}
		}
		
	}

}
