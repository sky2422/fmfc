package svc.admin;

import java.sql.Connection;
import static db.JdbcUtil.*;

import dao.PlayerDAO;

public class PlayerDeleteService {

	public boolean deletePlayer(int back_no) {
		// 1. 커넥션 풀에서 Connection객체 얻어와
				Connection con =  getConnection(); // 바로 호출해서 사용 가능 (클래스명(JdbcUtil)생략가능) -> 이유: import static db.JdbcUtil.*; 해줬기 때문
				
				// 2. 싱글톤 패턴 UserDAO 객체 생성
				PlayerDAO playerDAO = PlayerDAO.getInstance();
				
				// 3. DB작업에 사용될 Connection객체를 UserDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
				playerDAO.setConnection(con);
				
				/*--- UserDAO의 해당 메서드를 호출하여 처리 ---*/
				int deletePlayerCount = playerDAO.deletePlayer(back_no);
				
				/*--- [insert, update, delete 경우] 성공하면 COMMIT 실패하면 ROLLBACK 
		                       (select 제외)                               ---*/
				boolean isPlayerDeleteResult = false;
				if(deletePlayerCount > 0) {
					isPlayerDeleteResult = true;
					commit(con);
				}else {
					rollback(con);
				}
				
				
				// 4. 해제
				close(con); // Connection 객체 해제


				return isPlayerDeleteResult;
	}

}
