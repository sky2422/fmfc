package svc.product;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import vo.Cart;
import vo.Product;

public class ProductCartAddService {

	public Product getProductView(String p_code) {
		//1.커넥션 풀에서 Connection객체 얻어와 
		Connection con = getConnection();//static으로 가져와 JdbcUtil(클래스이름)생략가능
		
		//2.싱글톤 패턴 : Product객체 생성 
		ProductDAO productDAO = ProductDAO.getInstance();//바로 호출해서 사용가능(import static db.JdbcUtil.*)
		
		//3.DB작업에 사용될 Connection객체를 ProductDAO의 멤버변수로 전달하여 DB연결하여 작업하도록 서비스해줌
		productDAO.setConnection(con);
		
		/**---- ProductDAO의 해당 메서드를 호출하여 처리 ----**/
		Product productInfo = productDAO.selectProductInfo(p_code);
		
		/*-(insert,update,delete) 성공하면 commit 
		 *  실패하면 rollback(select 제외)---*/
		
		
		//4.해제 
		close(con);//Connection객체 해제 
		
		
		return productInfo;
	}

	public void addCart(HttpServletRequest request, Product productInfo) {
		// 현재 session영역에 저장되어 있는 장바구니 목록을 얻어와
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		if(cartList == null) {
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}
		
		//지금 장바구니에 담는 항목이 새로 추가되는 항목인지를 저장할 변수
		boolean isNewCart = true;
		for(int i=0;i<cartList.size();i++) {
			if(productInfo.getP_code().equalsIgnoreCase(cartList.get(i).getp_code())) {
				isNewCart = false;
				cartList.get(i).setQty(cartList.get(i).getQty());
				break;
			}
		}
		
		if(isNewCart) {//지금 장바구니에 담는 항목이 새로 추가되는 항목이면
			Cart cart = new Cart();//기본값으로 채워진 Cart객체를 
			//매개값으로 전송된 productInfo값으로 채운 후
			cart.setp_code(productInfo.getP_code());
			cart.setCategory(productInfo.getCategory());
			cart.setName(productInfo.getP_name());
			cart.setPrice(productInfo.getP_price());
			cart.setImage(productInfo.getImage());
			cart.setp_date(productInfo.getP_date());
			cart.setQty(1);//수량은 처음이므로 1으로 셋팅
			
			cartList.add(cart);//cartList에 추가
		}
	}
	
	
	
}





























