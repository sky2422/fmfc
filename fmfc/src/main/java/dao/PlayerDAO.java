package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Player;

public class PlayerDAO {
	//멤버변수 (전역변수 : 전체 메서드에서 사용이 가능)
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/*** 싱글톤 패턴 : CustomerDAO객체 단1개만 생성 ***
	 * 이유? 외부클래스에서 "처음 생성된 CustomerDAO객체를 공유해서 사용하도록 하기 위해"
	 */
	
	//1.생성자는 무조건 private : 외부 클래스에서 생성자를 호출 불가하여 직접적으로 객체 생성 불가능 
	private PlayerDAO() {}
	
	private static PlayerDAO playerDAO;//2.반드시 static이여야 한다.
	
	//2.메서드가 static인 이유? 객체 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 CustomerDAO객체를 1개만 만들수 있도록 하기 위해서
	public static PlayerDAO getInstance(){//1.static이면
		if(playerDAO == null) {//CustomerDAO 객체가 없으면 / Dog객체가 만들어져 있는지 없는지 확인 
			playerDAO = new PlayerDAO();//객체생성
		}
		
		return playerDAO;//기존 CustomerDAO객체의 주소를 리턴
	}
	/***************** 싱글톤 만드는 방법 끝 *****************/
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public ArrayList<Player> selectPlayerList() {
		ArrayList<Player> playerList = null;
		
		String sql = "select * from player";
			
		try {
			   pstmt = con.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   
			   if(rs.next()) {
				   playerList = new ArrayList<Player>();
				   
				   do {
					   Player player = new Player(rs.getInt("back_no"),
							   
							   					  rs.getString("name"),
							   					  rs.getString("position"),
							   					  rs.getString("birth"),
							   					  
							   					  rs.getInt("height"),
							   					  rs.getInt("weight"),
							   					  
							   					  rs.getString("team"),
							   					  rs.getString("image"));
					   playerList.add(player);
					   
				   }while(rs.next());
				   
			   }//if문 끝
			   
		}catch(Exception e) {
			System.out.println("selectPlayerList 에러 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return playerList;
	}
	
	//선수 상세 정보 조회
	public Player selectPlayerInfo(int back_no) {
		Player playerInfo = null;
		
		String sql = "select * from player where back_no=?";
		
		try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, back_no);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					playerInfo = new Player(rs.getInt("back_no"),
							
											rs.getString("name"),
											rs.getString("position"),
											rs.getString("birth"),
											
											rs.getInt("height"),
											rs.getInt("weight"),
											rs.getString("team"),
						   					rs.getString("image"));
				}
			
		}catch(Exception e){
			 System.out.println("selectPlayerInfo 에러 : " + e );//e:예외종류+예외메세지
			}finally {
				//JdbcUtil.close(rs);
				//JdbcUtil.close(pstmt);
				close(rs);
				close(pstmt);
			}
		return playerInfo;
	}
	
	//새로운 선수 추가
	public int insertNewPlayer(Player newPlayer) {
		int insertNewPlayerCount = 0;
		
		String sql = "insert into player values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, newPlayer.getBack_no());
				
				pstmt.setString(2, newPlayer.getName());
				pstmt.setString(3, newPlayer.getPosition());
				pstmt.setString(4, newPlayer.getBirth());
				
				pstmt.setInt(5, newPlayer.getHeight());
				pstmt.setInt(6, newPlayer.getWeight());
				
				pstmt.setString(7, newPlayer.getTeam());
				pstmt.setString(8, newPlayer.getImage());
				
				insertNewPlayerCount = pstmt.executeUpdate();
				
		}catch (Exception e) {
			System.out.println("insertNewPlayer 에러 : " + e );//e:예외종류+예외메세지
		}finally {
			//JdbcUtil.close(rs);
			//JdbcUtil.close(pstmt);
			//close(rs);
			close(pstmt);
		}
		
		return insertNewPlayerCount;
	}
	
	//선수 수정
	public int updatePlayer(Player player) {
		int updatePlayerCount = 0;
		
		String sql = "update player set name=?, position=?, birth=?, height=?, weight=?, team=?";
		
		if(player.getImage() != null) {
			sql += ", image=? '" + player.getImage() + "'";
		}		
		System.out.println("[update] 올리는 이미지 파일명 : " + player.getImage() );
		sql += " where back_no=?";
		//[방법-2] : 이미지를 다시 올리는 경우 
		//String sql = "update player set name=?, position=?, birth=?, height=?, weight=?, team=?, image=? where back_no=?";
		
		try {
			pstmt = con.prepareStatement(sql); // prepareStatement 객체생성 
			
			//[방법-1] : 기존의 이미지를 그대로 사용하려면
			pstmt.setString(1, player.getName());
			pstmt.setString(2, player.getPosition());
			pstmt.setString(3, player.getBirth());
		
			pstmt.setInt(4, player.getHeight());
			pstmt.setInt(5, player.getWeight());
			
			pstmt.setString(6, player.getTeam());
			//pstmt.setString(7, player.getImage());
			pstmt.setInt(7, player.getBack_no());
			
			
			//[방법-2] : 이미지를 다시 올리는 경우
			/*
			pstmt.setString(1, player.getName());
			pstmt.setString(2, player.getPosition());
			pstmt.setString(3, player.getBirth());
		
			pstmt.setInt(4, player.getHeight());
			pstmt.setInt(5, player.getWeight());
			
			pstmt.setString(6, player.getTeam());
			pstmt.setString(7, player.getImage());
			
			pstmt.setInt(8, player.getBack_no());
			*/
			
			updatePlayerCount = pstmt.executeUpdate();
			
			System.out.println("updatePlayerCount : " + updatePlayerCount);
			
			
			}catch (Exception e) {
				System.out.println(" updatePlayer : " + e);
			}finally {
				//close(rs);
				close(pstmt);
			}
			
			return updatePlayerCount;
	}
	
	//선수 삭제
	public int deletePlayer(int back_no) {
		int deletePlayerCount = 0;
		
		String sql = "delete from player where back_no=?";
		
		try {
				pstmt = con.prepareStatement(sql); // prepareStatement 객체생성 
				pstmt.setInt(1, back_no);
				
				deletePlayerCount = pstmt.executeUpdate();
				
				System.out.println("deletePlayerCount : " + deletePlayerCount);
				
				
		}catch (Exception e) {
			System.out.println(" deletePlayer : " + e);
		}finally {
			//close(rs);
			close(pstmt);
		}
		
		return deletePlayerCount;
	}
	

}
