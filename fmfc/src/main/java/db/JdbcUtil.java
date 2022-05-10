package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	//★★먼저, context.xml에서 "MySQL로 설정"
	//커넥션 풀에서 Connection객체를 얻어와 반환하는 메서드
	public static Connection getConnection(){
		Connection con = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			
			//name으로 커넥션 풀인 DataSource객체를 얻어와 
			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQLDB");
			
			con = ds.getConnection();//커넥션 풀(Data Source)에서 Connection객체를 얻어와
			
			con.setAutoCommit(false);//★<리턴 하기전에>Connection객체의 트랙잭션을 자동커밋되지 않도록 하기 위해 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/*
	 * Connection객체를 닫아주는 메서드 
	 */
		public static void close(Connection con){
		
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	/*
	 * Statement객체를 닫아주는 메서드 
	 */
		
		public static void close(Statement stmt){
			
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	/*
	 * PreparedStatement객체를 닫아주는 메서드 
	 */
			
		
		public static void close(PreparedStatement pstmt){
			
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	/*
	 * 	ResultSet객체를 닫아주는 메서드 
	 */
		public static void close(ResultSet rs){
			
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		/****************************************************/
		
		/*
		 * 트랜잭션 중에 실행된 작업들을 '완료'시켜주는 메서드 
		 * insert, update, delete 성공하면 commit함
		 */
		public static void commit(Connection con){
			
			try {
				con.commit();
				System.out.println("commit success");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		/*
		 * 트랜잭션 중에 실행된 작업들을 '완료'시켜주는 메서드 
		 * insert, update, delete 실패하면 rollback함
		 */
		public static void rollback(Connection con){
			
			try {
				con.rollback();
				System.out.println("rollback success");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
}
