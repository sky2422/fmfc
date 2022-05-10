package svc.admin;

import vo.CustomerBean;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;

public class AdminLoginService {

	public CustomerBean getAdminInfo(String a_id) {
		// 1. 커넥션 풀에서 Connection객체 얻어와
		Connection con =  getConnection(); // 바로 호출해서 사용 가능 (클래스명(JdbcUtil)생략가능) -> 이유: import static db.JdbcUtil.*; 해줬기 때문
		
		// 2. 싱글톤 패턴 CustomerDAO 객체 생성
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		// 3. DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		adminDAO.setConnection(con);
		
		/*--- CustomerDAO의 해당 메서드를 호출하여 처리 ---*/
		
		/*--- [insert, update, delete 경우] 성공하면 COMMIT 실패하면 ROLLBACK 
        (select 제외)                               ---*/
		CustomerBean adminInfo = adminDAO.selectAdminInfo(a_id);
		
		// 4. 해제
		close(con); // Connection 객체 해제
		
		return adminInfo;
	}
	
	// 2. 회원여부확인
	public boolean login(CustomerBean admin) {
		// 1. 커넥션 풀에서 Connection객체 얻어와
		Connection con =  getConnection(); // 바로 호출해서 사용 가능 (클래스명(JdbcUtil)생략가능) -> 이유: import static db.JdbcUtil.*; 해줬기 때문
		
		// 2. 싱글톤 패턴 AdminDAO 객체 생성
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		// 3. DB작업에 사용될 Connection객체를 AdminDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		adminDAO.setConnection(con);
		
		/*--- AdminDAO의 해당 메서드를 호출하여 처리 ---*/
		String loginId = adminDAO.selectLoginId(admin);
		
		/*--- [insert, update, delete 경우] 성공하면 COMMIT 실패하면 ROLLBACK 
                       (select 제외)                               ---*/
		
		boolean isloginResult = false;
		
		if(loginId != null) { // 값이 존재하면 (성공했으면)
			isloginResult = true;
		}
		
		// 4. 해제
		close(con); // Connection 객체 해제
		
		return isloginResult;
	}

}
