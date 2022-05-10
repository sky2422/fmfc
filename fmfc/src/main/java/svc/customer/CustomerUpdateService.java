package svc.customer;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.CustomerDAO;
import vo.Address;
import vo.CustomerBean;

public class CustomerUpdateService {

	public boolean customerUpdate(CustomerBean customer, Address addr) {
		//멤버변수
				//생성자
				//메서드 
				
				//1.커넥션 풀에서 Connection객체 얻어와 
				Connection con = getConnection();//static으로 가져와 JdbcUtil(클래스이름)생략가능
				
				//2.싱글톤 패턴 : DogDAO객체 생성 
				CustomerDAO customerDAO = CustomerDAO.getInstance();//바로 호출해서 사용가능(import static db.JdbcUtil.*)
				
				//3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 전달하여 DB연결하여 작업하도록 서비스해줌
				customerDAO.setConnection(con);
				
				/**---- DogDAO의 해당 메서드를 호출하여 처리 ----**/
				int updateCustomerCount = customerDAO.updateCustomer(customer);
				int updateAddrCount = customerDAO.updateAddr(addr);
					
				/*-(insert,update,delete) 성공하면 commit 
				 *  실패하면 rollback(select 제외)---*/
				boolean isCustomerUpdateResult = false;
				
				if(updateCustomerCount > 0 && updateAddrCount>0) {
					isCustomerUpdateResult = true;
					commit(con);
				}else {
					rollback(con);
				}
				//4.해제 
				close(con);//Connection객체 해제 
				
				return isCustomerUpdateResult;
	}

}
