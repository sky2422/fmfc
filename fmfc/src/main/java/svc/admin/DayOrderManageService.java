package svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderDAO;
import vo.Order;

public class DayOrderManageService {

	public ArrayList<Order> getDayOrderList(String simpleDate_today) {
		// 1. 커넥션 풀에서 Connection객체 얻어와
		Connection con =  getConnection(); // 바로 호출해서 사용 가능 (클래스명(JdbcUtil)생략가능) -> 이유: import static db.JdbcUtil.*; 해줬기 때문
		
		// 2. 싱글톤 패턴 UserDAO 객체 생성
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		// 3. DB작업에 사용될 Connection객체를 UserDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		orderDAO.setConnection(con);
		
		/*--- UserDAO의 해당 메서드를 호출하여 처리 ---*/
		ArrayList<Order> dayOrderList = orderDAO.selectDayOrderList(simpleDate_today);
		
		/*--- [insert, update, delete 경우] 성공하면 COMMIT 실패하면 ROLLBACK 
                       (select 제외)                               ---*/
		
		
		// 4. 해제
		close(con); // Connection 객체 해제


		return dayOrderList;
	}

}
