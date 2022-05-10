package svc.customer;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.CustomerDAO;
import vo.CustomerBean;

public class CustomerHashPwService {
			//멤버변수
			//생성자
			//메서드 
		
			//customer_table안의 회원정보(pw)를 조회하여 리턴 
			public CustomerBean findHashPw(String c_id, String c_email) {
			//1.커넥션 풀에서 Connection객체 얻어와 
			Connection con = getConnection();//static으로 가져와 JdbcUtil(클래스이름)생략가능
			
			//2.싱글톤 패턴 : DogDAO객체 생성 
			CustomerDAO customerDAO = CustomerDAO.getInstance();//바로 호출해서 사용가능(import static db.JdbcUtil.*)
			
			//3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 전달하여 DB연결하여 작업하도록 서비스해줌
			customerDAO.setConnection(con);
			
			/**---- DogDAO의 해당 메서드를 호출하여 처리 ----**/
			CustomerBean customerInfo = customerDAO.findHashPw(c_id, c_email);
			
			/*-(insert,update,delete) 성공하면 commit 
			 *  실패하면 rollback(select 제외)---*/
		
			
			//4.해제 
			close(con);//Connection객체 해제 
			
			return customerInfo;
			
		}

			public boolean setHashPw(String c_id, String c_email, String ramdom_password) {
				//1.커넥션 풀에서 Connection객체 얻어와 
				Connection con = getConnection();//static으로 가져와 JdbcUtil(클래스이름)생략가능
				
				//2.싱글톤 패턴 : DogDAO객체 생성 
				CustomerDAO customerDAO = CustomerDAO.getInstance();//바로 호출해서 사용가능(import static db.JdbcUtil.*)
				
				//3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 전달하여 DB연결하여 작업하도록 서비스해줌
				customerDAO.setConnection(con);
				
				/**---- DogDAO의 해당 메서드를 호출하여 처리 ----**/
				int setHashPwCount = customerDAO.setHashPw(c_id, c_email, ramdom_password);
				
				/*-(insert,update,delete) 성공하면 commit 
				 *  실패하면 rollback(select 제외)---*/
				boolean isSetHashPwResult = false;
				if(setHashPwCount > 0) {
					isSetHashPwResult = true;
					commit(con);
				}else {
					rollback(con);
				}
				
				//4.해제 
				close(con);//Connection객체 해제 
				
				return isSetHashPwResult;
		}
}
