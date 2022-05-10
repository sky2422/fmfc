package svc.customer;

import vo.Address;
import vo.CustomerBean;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CustomerDAO;

public class CustomerViewService {

	public CustomerBean getCustomerInfo(String viewId) {
		// 1. 커넥션 풀에서 Connection객체 얻어와
		Connection con =  getConnection(); // 바로 호출해서 사용 가능 (클래스명(JdbcUtil)생략가능) -> 이유: import static db.JdbcUtil.*; 해줬기 때문
		
		// 2. 싱글톤 패턴 CustomerDAO 객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		
		// 3. DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		customerDAO.setConnection(con);
		
		/*--- CustomerDAO의 해당 메서드를 호출하여 처리 ---*/
		CustomerBean customerInfo = customerDAO.selectCustomerInfo(viewId);
		
		/*--- [insert, update, delete 경우] 성공하면 COMMIT 실패하면 ROLLBACK 
                       (select 제외)                               ---*/
		
		
		// 4. 해제
		close(con); // Connection 객체 해제
		
		
	
		return customerInfo;
	}
			
			//address_table안의 회원주소정보를 viewId로 조회
			public Address getAddressInfo(String viewId) {
				// 1. 커넥션 풀에서 Connection객체 얻어와
				Connection con =  getConnection(); // 바로 호출해서 사용 가능 (클래스명(JdbcUtil)생략가능) -> 이유: import static db.JdbcUtil.*; 해줬기 때문
				
				// 2. 싱글톤 패턴 CustomerDAO 객체 생성
				CustomerDAO customerDAO = CustomerDAO.getInstance();
				
				// 3. DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
				customerDAO.setConnection(con);
				
				/*--- CustomerDAO의 해당 메서드를 호출하여 처리 ---*/
				Address customerAddrInfo = customerDAO.selectCustomerAddrInfo(viewId);
				
				/*--- [insert, update, delete 경우] 성공하면 COMMIT 실패하면 ROLLBACK 
		                       (select 제외)                               ---*/
				
				// 4. 해제
				close(con); // Connection 객체 해제
				
				
			
				return customerAddrInfo;
	}

}
