package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.Board;

public class BoardWriteService {

	public boolean registArticle(Board board) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:BoardDAO객체 생성
		BoardDAO boardDAO = BoardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 BoardDAO의 멤버변수로 삽입하여 DB 연결
		boardDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		int insertCount = boardDAO.insertArticle(board);		
		
		/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
		 * (select제외)----*/
		boolean isWriteResult = false;		
		if(insertCount>0) {//글등록 성공하면
			isWriteResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return isWriteResult;
	}

}
