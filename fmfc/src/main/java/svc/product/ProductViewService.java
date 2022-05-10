package svc.product;
/*
 * [Product 패키지에 넣는 이유?] 
 * => 사용자와 관리자 둘다 사용 하므로 
 */
import vo.Product;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.ProductDAO;

public class ProductViewService {

	public Product getProductView(String p_code) {
		// 1. 커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();// static으로 가져와 JdbcUtil(클래스이름)생략가능 

		// 2. 싱글톤 패턴 ProductDAO 객체 생성
		ProductDAO productDAO = ProductDAO.getInstance();// 바로 호출해서 사용가능(import static db.JdbcUtil.;*)

		// 3. DB작업에 사용될 Connection객체를 ProductDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		productDAO.setConnection(con);

		/** ---- productDAO의 해당 메서드를 호출하여 처리 ---- **/
		Product productInfo = productDAO.selectProductInfo(p_code);
		
		/*-(insert,update,delete) 성공하면 commit 
		 *  실패하면 rollback(select 제외)---*/
		
		// 4. 해제
		close(con); // Connection 객체 해제

		return productInfo;
	}

}
