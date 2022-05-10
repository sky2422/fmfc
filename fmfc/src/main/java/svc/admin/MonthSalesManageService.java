package svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;

public class MonthSalesManageService {
	//승인된 한달 총매출 조회
	public int getMonthTotalMoney() {
	// 1. 커넥션 풀에서 Connection객체 얻어와
	Connection con = getConnection();// static으로 가져와 JdbcUtil(클래스이름)생략가능 
	
	// 2. 싱글톤 패턴 AdminDAO 객체 생성
	AdminDAO adminDAO = AdminDAO.getInstance();// 바로 호출해서 사용가능(import static db.JdbcUtil.;*)
	
	// 3. DB작업에 사용될 Connection객체를 AdminDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
	adminDAO.setConnection(con);
	
	/** ---- adminDAO의 해당 메서드를 호출하여 처리 ---- **/
	int monthTotalMoney = adminDAO.selectMonthTotalMoney();
	
	/*-(insert,update,delete) 성공하면 commit 
	 *  실패하면 rollback(select 제외)---*/
	
	// 4. 해제
	close(con); // Connection 객체 해제
	
	return monthTotalMoney;
	}

}
