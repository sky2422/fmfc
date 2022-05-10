package svc.product;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import dao.ProductDAO;
import vo.Order;
import vo.Product;

public class ProductCartOrderService {

	public boolean orderProduct(String c_id, String c_email, ArrayList<Product> productList, int totalMoney) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();// static으로 가져와 JdbcUtil(클래스이름)생략가능
		
		//2.싱글톤 패턴 ProductDAO객체 생성
		ProductDAO productDAO = ProductDAO.getInstance();// 바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		
		//3.DB작업에 사용될 Connection객체를 ProductDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		productDAO.setConnection(con);
		
		/*--DAO의 해당 메서드를 호출하여 처리--*/
		int insertOrderProductCount = productDAO.insertOrderProduct(c_id, c_email, productList, totalMoney);
		
		/*-(insert,update,delete) 성공하면 commit
		 * 실패하면 rollback(select 제외)-*/
		boolean isOrderProductResult = false;
		
		if(insertOrderProductCount>0) {
			isOrderProductResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection 객체 해제
				
		return isOrderProductResult;
	}
	
	
	//마지막 주문
	public Order customerLastOrder(String c_id) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();// static으로 가져와 JdbcUtil(클래스이름)생략가능
		
		//2.싱글톤 패턴 OrderDAO객체 생성
		OrderDAO orderDAO = OrderDAO.getInstance();// 바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		
		//3.DB작업에 사용될 Connection객체를 ProductDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		orderDAO.setConnection(con);
		
		/*--DAO의 해당 메서드를 호출하여 처리--*/
		Order latestOrder = orderDAO.selectLastestOrder(c_id);
		
		
		//4.해제
		close(con);//Connection 객체 해제
				
		return latestOrder;
	}
	
	
	

}


























