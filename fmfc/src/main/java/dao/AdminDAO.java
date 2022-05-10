package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Address;
import vo.CustomerBean;

public class AdminDAO {
	//멤버변수 (전역변수 : 전체 메서드에서 사용이 가능)
			private Connection con = null;
			private PreparedStatement pstmt = null;
			private ResultSet rs = null;
			
			/*** 싱글톤 패턴 : AdminDAO객체 단1개만 생성 ***
			 * 이유? 외부클래스에서 "처음 생성된 AdminDAO객체를 공유해서 사용하도록 하기 위해"
			 */
			
			//1.생성자는 무조건 private : 외부 클래스에서 생성자를 호출 불가하여 직접적으로 객체 생성 불가능 
			private AdminDAO() {}
			
			private static AdminDAO adminDAO;//2.반드시 static이여야 한다.
			
			//2.메서드가 static인 이유? 객체 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 AdminDAO객체를 1개만 만들수 있도록 하기 위해서
			public static AdminDAO getInstance(){//1.static이면
				if(adminDAO == null) {//AdminDAO 객체가 없으면 
					adminDAO = new AdminDAO();//객체생성
				}
				
				return adminDAO;//기존 AdminDAO객체의 주소를 리턴
			}
			/***************** 싱글톤 만드는 방법 끝 *****************/
			
			public void setConnection(Connection con){
				this.con = con;
			}
			
			// 1. 로그인폼에서 입력한 id와 pw가 담긴 customerBean객체로 회원인지 조회한 후 회원이면 그 id를 반환
			public String selectLoginId(CustomerBean admin){
				String loginId = null;
				
				String sql = "select c_id, c_password FROM customer WHERE c_id = ? AND c_password=?";
				
				try {
					pstmt = con.prepareStatement(sql); // prepareStatement 객체생성 
					
					pstmt.setString(1, admin.getC_id()); // ? 값 설정
					pstmt.setString(2, admin.getC_password());
					
					rs = pstmt.executeQuery(); // 실행
					
					if(rs.next()) { // sql문에서 insert한 값이 1개라도 있으면
						
						/* [ 방법 1 ]
						 * 다시한번 확인하는 작업 할필요 없음 -> 이유 ? SQL문에서 데이터 존재한다는 것이 아이디와 비밀번호가 일치한다는 의미이기때문
						 
						String db_Password = rs.getString("c_password"); // DB에 저장된 비번
						String hash_Password = admin.getC_password(); // 사용자가 입력한 비번을 이미 암호화시켜 전송된 비번
						
						System.out.println("[selectLoginId]");
						System.out.println("db_Password : " + db_Password);
						System.out.println("hash_Password : " + hash_Password);
						
						if(db_Password.equals(hash_Password)) {
							loginId = admin.getC_id();
						}else {
							loginId = null;
						}
						*/
						
						// loginId = customer.getC_id(); // [방법 2]
						loginId = rs.getString("c_id"); // [방법3] (결과가 참이기 때문에 사용가능)
					
					} // if문 끝
					
				} catch (Exception e) {
					System.out.println("selectLoginId 에러 : " + e); // e: 예외종류 + 예외 메세지
				}finally {
					close(rs);
					close(pstmt);
				}
				
				return loginId;
			}
			//id로 조회한 회원정보 조회
			public CustomerBean selectAdminInfo(String a_id) {
				CustomerBean adminInfo = null;
				
				// String sql = "SELECT * FROM USER_TABLE WHERE C_ID = ?"; // [방법 1]
				String sql = "SELECT * FROM customer WHERE C_ID = '" + a_id +"'"; // [방법 2]
				
				try {
					pstmt = con.prepareStatement(sql);
					//pstmt.setString(1, c_id);// [방법 1] 
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						adminInfo = new CustomerBean();//기본값으로 채워짐
						
						adminInfo.setC_id(rs.getString("c_id"));
						adminInfo.setC_grade(rs.getString("c_grade"));
						adminInfo.setC_name(rs.getString("c_name"));
						adminInfo.setC_email(rs.getString("c_email"));
						adminInfo.setC_call(rs.getString("c_call"));
						
					}//if문 끝
					
				}catch(Exception e){
				 System.out.println("getAdminInfo 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(rs);
					close(pstmt);
				}
				
				
				return adminInfo;
			}
			
			//관리자 등록 
			public int insertAdmin(CustomerBean admin) {
					int insertAdminCount = 0;
					
					String sql = "insert into customer(c_id,c_grade,c_password,c_name,c_email,c_call,c_joindate) "
							+ "values(?,?,?,?,?,?,now())";
			
					try {
						
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, admin.getC_id());
						pstmt.setString(2, admin.getC_grade());
						pstmt.setString(3, admin.getC_password());
						//pstmt.setString(3, SHA256.encodeSHA256(admin.getC_password()));
						pstmt.setString(4, admin.getC_name());
						pstmt.setString(5, admin.getC_email());
						pstmt.setString(6, admin.getC_call());
						//pstmt.setString(7, admin.getC_joindate());
						
						insertAdminCount = pstmt.executeUpdate();//업데이트에 성공하면 1을 리턴받음
						System.out.println("insertAdminCount : " + insertAdminCount);
						
					}catch (Exception e) {
						System.out.println("insertAdmin 에러 : " + e );//e:예외종류+예외메세지
					}finally {
						//JdbcUtil.close(rs);
						//JdbcUtil.close(pstmt);
						close(pstmt);
					}
					
					return insertAdminCount;
				}
			

			//관리자 주소 등록 
			public int insertAddr(Address addr) {
				int insertAddrCount = 0;
				
				String sql = "insert into address(c_id, postcode, address1, address2) "
						+ "values(?,?,?,?)";
				
				
				try {
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, addr.getC_id());
					pstmt.setInt(2, addr.getPostcode());
					pstmt.setString(3, addr.getAddress1());
					pstmt.setString(4, addr.getAddress2());

					
					insertAddrCount = pstmt.executeUpdate();//업데이트에 성공하면 1을 리턴받음
					
					System.out.println("insertAddrCount : " + insertAddrCount);
					
				}catch (Exception e) {
					System.out.println("insertAddr 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(pstmt);
				}
				
				return insertAddrCount;
			}
			
			/*------------------------------------------------------------*/
			
			//승인된 한달 총매출 조회
			public int selectMonthTotalMoney() {
				int monthTotalMoney = 0;
				
				String sql = "select sum(totalMoney) as monthTotalMoney from order_table where order_status='get'";
				
				try {
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
							monthTotalMoney = rs.getInt(1);
					}
				}catch (Exception e) {
					System.out.println(" selectMonthTotalMoney : " + e);
				}finally {
					close(rs);
					close(pstmt);
				}
					
					return monthTotalMoney;
				}
			
			
			
			
			
			
			
			
			
			
			
}
