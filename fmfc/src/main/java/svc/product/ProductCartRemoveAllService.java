package svc.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Product;

public class ProductCartRemoveAllService {

	public void cartRemoveAll(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<Product> cartList = (ArrayList<Product>) session.getAttribute("cartList");
		
		session.removeAttribute("cartList");
	}

}
