//DB로 SQL구문을 전송하는 클래스
package dao;

import static db.JdbcUtil.*;//static:모든 메서드들 미리 메모리에 올림

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Board;

import vo.Player;

public class BoardDAO {
	Connection con = null;//멤버변수(전역변수 : 전체 메서드에서 사용 가능)
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/*** 싱글톤 패턴 : BoardDAO객체 단 1개만 생성**************************
	 * 이유? 외부 클래스에서 "처음 생성된 BoardDAO객체를 공유해서 사용하도록 하기 위해" 
	 */
	private BoardDAO(){}
	
	private static BoardDAO boardDAO;
	//static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 BoardDAO객체를 1개만 만들도록 하기 위해
	public static BoardDAO getInstance(){
		if(boardDAO == null) {//객체가 없으면
			boardDAO = new BoardDAO();//객체 생성
		}
		
		return boardDAO;//기존 객체의 주소 리턴
	}
	/************************************************************/
	
	public void setConnection(Connection con){//Connection객체를 받아 DB 연결
		this.con=con;
	}
	
	//처음 자유게시판눌렀을 때 글 조회 
	public ArrayList<Board> selectboardList() {
		ArrayList<Board> boardList = null;
		
		String sql = "select * from board";
		try {
			   pstmt = con.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   
			   if(rs.next()) {
				   boardList = new ArrayList<Board>();
				   
				   do {
					   Board board = new Board(rs.getInt("board_num"),
							   				   rs.getString("c_id"),
								   			   rs.getString("board_subject"),
								   			   rs.getInt("board_count"),
								   			   rs.getDate("board_date"));
					   boardList.add(board);
					   
				   }while(rs.next());
				   
			   }//if문 끝
			   
		}catch(Exception e) {
			System.out.println("selectboardList 에러 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return boardList;
	}
	
	//1.글 등록 
	public int insertArticle(Board board) {
		int insertCount=0;
		
		String sql="";
		int num = 0;
		try {			
		
			pstmt=con.prepareStatement("select IFNULL(MAX(board_num),0)+1 from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) num=rs.getInt(1);//수정
			
			sql = "insert into board values(?,?,?,?,?,?,now())";//now()=오라클 sysdate (★주의:()없음)
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getC_id());//글쓴이
			pstmt.setString(3, board.getBoard_subject());//제목
			pstmt.setString(4, board.getBoard_content());//내용
			pstmt.setString(5, board.getBoard_file());//첨부 파일
			pstmt.setInt(6, 0);//조회수(0으로 초기화)
			//7 : now()오늘날짜
			
			insertCount = pstmt.executeUpdate();//업데이트가 성공하면 1을 리턴받음
			
		} catch (Exception e) {			
			//e.printStackTrace();
			System.out.println("insertArticle 에러 :" + e);//e:예외종류+예외메세지
		} finally {
			close(rs);
			close(pstmt);			
		}
				
		return insertCount;
	}
	
	//2.글번호로 글 하나의 정보를 조회하여 반환
	public Board selectArticle(int board_num) {
		Board boardBean = null;
		
		String sql = "select * from board where board_num = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardBean = new Board();//기본값으로 채워진 Board객체를 
				//조회한 결과값으로 채움
				
				boardBean.setBoard_num(rs.getInt("board_num"));
				
				boardBean.setC_id(rs.getString("c_id"));
				boardBean.setBoard_subject(rs.getString("board_subject"));
				boardBean.setBoard_content(rs.getString("board_content"));
				boardBean.setBoard_file(rs.getString("board_file"));
				
				boardBean.setBoard_count(rs.getInt("board_count"));
				
				boardBean.setBoard_date(rs.getDate("board_date"));
				
			}
			
		} catch (Exception e) {					
			System.out.println("selectArticle 에러 :" + e);//e:예외종류+예외메세지
		} finally {
			close(rs);
			close(pstmt);			
		}
		
		return boardBean;
	}
	
	//3.'글번호'로  해당글을 찾아서 '조회수1 증가'
	public int updateReadCount(int board_num) {
		int updateReadCount = 0;//지역변수 초기화
		
		
		String sql="update board set board_count=board_count+1 where board_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			updateReadCount = pstmt.executeUpdate();
		} catch (Exception e) {					
			System.out.println("updateReadCount 에러 :" + e);//e:예외종류+예외메세지
		} finally {
			//close(rs);
			close(pstmt);			
		}	
		
		return updateReadCount;
	}
	
	//4. 글 수정
	public int updateArticle(Board article) {
		int updateCount = 0;
		
		String sql="update board SET c_id=?, board_subject=?, board_content=?, board_file=? where board_num=?";//이름도 수정가능함
		//String sql="update board SET board_subject=?, board_content=?, board_file=? where board_num=?";//이름 수정 불가능함
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, article.getC_id());
			pstmt.setString(2, article.getBoard_subject());
			pstmt.setString(3, article.getBoard_content());
			pstmt.setString(4, article.getBoard_file());
			
			pstmt.setInt(5, article.getBoard_num());
			
			updateCount = pstmt.executeUpdate();
			
			
		} catch (Exception e) {					
			System.out.println("updateArticle 에러 :" + e);//e:예외종류+예외메세지
		} finally {
			//close(rs);
			close(pstmt);			
		}	
		
		return updateCount;
	}
	
	//5. 글삭제
	public int deleteArticle(int board_num) {
		int deleteCount = 0;
		
		String sql="delete from board where board_num=?";
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, board_num);
			
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {					
			System.out.println("deleteArticle 에러 :" + e);//e:예외종류+예외메세지
		} finally {
			//close(rs);
			close(pstmt);			
		}	
		
		return deleteCount;
	}
	
	
	
	
}











