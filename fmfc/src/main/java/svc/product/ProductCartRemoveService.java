package svc.product;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;



public class ProductCartRemoveService {

	public void cartRemove(String p_code, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		for(int i=0;i<cartList.size();i++) {
			if(cartList.get(i).getp_code().equalsIgnoreCase(p_code)) {
				cartList.remove(cartList.get(i));//장바구니 목록에서 삭제
				break;//삭제시킨 후 반복문 빠져나감
			}
		}
		
	}	

}
