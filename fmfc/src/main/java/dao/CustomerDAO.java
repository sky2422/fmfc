package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.SHA256;
import vo.Address;
import vo.CustomerBean;
import vo.CustomerPwChange;

public class CustomerDAO {
	//멤버변수 (전역변수 : 전체 메서드에서 사용이 가능)
			private Connection con = null;
			private PreparedStatement pstmt = null;
			private ResultSet rs = null;
			
			/*** 싱글톤 패턴 : CustomerDAO객체 단1개만 생성 ***
			 * 이유? 외부클래스에서 "처음 생성된 CustomerDAO객체를 공유해서 사용하도록 하기 위해"
			 */
			
			//1.생성자는 무조건 private : 외부 클래스에서 생성자를 호출 불가하여 직접적으로 객체 생성 불가능 
			private CustomerDAO() {}
			
			private static CustomerDAO customerDAO;//2.반드시 static이여야 한다.
			
			//2.메서드가 static인 이유? 객체 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 CustomerDAO객체를 1개만 만들수 있도록 하기 위해서
			public static CustomerDAO getInstance(){//1.static이면
				if(customerDAO == null) {//CustomerDAO 객체가 없으면 / Dog객체가 만들어져 있는지 없는지 확인 
					customerDAO = new CustomerDAO();//객체생성
				}
				
				return customerDAO;//기존 CustomerDAO객체의 주소를 리턴
			}
			/***************** 싱글톤 만드는 방법 끝 *****************/
			
			public void setConnection(Connection con){
				this.con = con;
			}
			
			// 1. 로그인폼에서 입력한 id와 pw가 담긴 customerBean객체로 회원인지 조회한 후 회원이면 그 id를 반환
			public String selectLoginId(CustomerBean customer){
				String loginId = null;
				String sql = "select c_id, c_password FROM customer WHERE c_id = ? AND c_password=?";
				
				try {
					pstmt = con.prepareStatement(sql); // prepareStatement 객체생성 
					
					pstmt.setString(1, customer.getC_id()); // ? 값 설정
					pstmt.setString(2, customer.getC_password());
					
					rs = pstmt.executeQuery(); // 실행
					
					if(rs.next()) { // sql문에서 insert한 값이 1개라도 있으면
						
						/* [ 방법 1 ]
						 * 다시한번 확인하는 작업 할필요 없음 -> 이유 ? SQL문에서 데이터 존재한다는 것이 아이디와 비밀번호가 일치한다는 의미이기때문
						 
						String db_Password = rs.getString("c_password"); // DB에 저장된 비번
						String hash_Password = customer.getC_password(); // 사용자가 입력한 비번을 이미 암호화시켜 전송된 비번
						
						System.out.println("[selectLoginId]");
						System.out.println("db_Password : " + db_Password);
						System.out.println("hash_Password : " + hash_Password);
						
						if(db_Password.equals(hash_Password)) {
							loginId = customer.getC_id();
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
			public CustomerBean getCustomerInfo(String c_id) {
				CustomerBean customerInfo = null;
				
				// String sql = "SELECT * FROM customer WHERE C_ID = ?"; // [방법 1] 순서1
				String sql = "SELECT * FROM customer WHERE C_ID = '" + c_id +"'"; // [방법 2]
				
				try {
					pstmt = con.prepareStatement(sql);
					//pstmt.setString(1, c_id);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						customerInfo = new CustomerBean();//기본값으로 채워짐
						
						customerInfo.setC_id(rs.getString("c_id"));
						customerInfo.setC_grade(rs.getString("c_grade"));
						customerInfo.setC_name(rs.getString("c_name"));
						customerInfo.setC_email(rs.getString("c_email"));
						customerInfo.setC_call(rs.getString("c_call"));
						
					}//if문 끝
					
				}catch(Exception e){
				 System.out.println("getCustomerInfo 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(rs);
					close(pstmt);
				}
				
				
				return customerInfo;
			}
			
			//로그인한 사용자의 id로 지난달 구매한 금액 조회(이 때, status='get'(주문승인))
			//로그인한 사용자의 id로 지난달 구매한 금액 조회(이 때, order_status='get'(주문승인))	
			public int getLastMonthMoney(String c_id) {
				int lastMonthMoney = 0;
				/* "MONTH(order_date)"=MONTH(2021-09-17)=9
				 * "MONTH(order_date)"=MONTH(2021-08-27)=8
				 * 
				 * "MONTH(now())-1"=MONTH(2021-10-1)-1=10-1=9월(지난달)
				 * 즉, =(같다) "월이 같은 것을 찾아 totalmoney를 SUM함"			
				 */
				
				String sql = "select SUM(totalmoney) from order_table";		
				sql += " where c_id=?"; /*사용자가 주문한 메뉴 중*/
				sql += " AND order_status ='get'"; /*'주문완료'한 것 중 */
				/*'날짜 조건1' OR '날짜 조건2'를 만족하는 것만 검색함*/
				sql += " AND ( ";
				/*'날짜 조건1':(예)예전 주문완료한 날짜('2022-03-15','2022-03-20')의 년(2022)과 오늘날짜(2022-04-29)의 년(2022)를 비교하여 같고
				                                  예전 주문완료한 날짜('2022-03-15','2022-03-20')의 달이 오늘날짜('2022-04-29')의 달(4)-1인 3과 같거나*/
				sql += " (YEAR(order_date)=YEAR(now())  AND MONTH(order_date)=MONTH(now())-1 AND MONTH(order_date)!=12)";
				/*'날짜 조건2':새해가 되면 년도가 바뀜
				            (예)예전 주문완료한 날짜('2021-12-15','2021-12-20')의 년(2021)과 오늘날짜('2022-1-4')의 년(2022)-1을 비교하여 같고
				                                  예전 주문완료한 날짜('2021-12-15','2021-12-20')의 달이 12*/
				sql += " OR (YEAR(order_date)=YEAR(now())-1 AND MONTH(now())=1 AND MONTH(order_date)=12)";
				sql += " )";
						
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, c_id);				
					rs = pstmt.executeQuery();
					
					if(rs.next()) {//해당 id에 대한 정보가 있으면
						lastMonthMoney = rs.getInt("SUM(totalmoney)");
					}
				} catch (Exception e) {			
					System.out.println(" getLastMonthMoney 에러:"+ e);
				} finally {
					close(rs);
					close(pstmt);
				}			
				
				return lastMonthMoney;
			}
		
			//아이디 찾기
			public CustomerBean findId(String c_email) {
				CustomerBean customerInfo =null;
				
				String sql = "SELECT * FROM customer WHERE C_EMAIL = '" + c_email+"'";
				//String sql = "SELECT * FROM customer WHERE C_EMAIL = ?"
				try {
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						customerInfo = new CustomerBean(); // 기본값으로 채워진 CustomerDAO객체에
						customerInfo.setC_id(rs.getString("c_id")); // 조회한 id값 셋팅
						customerInfo.setC_email(rs.getString("c_email"));
					}
					
				}catch(Exception e){
				 System.out.println("findId 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(rs);
					close(pstmt);
				}
				
				
				return customerInfo;
			}
			
			//비밀번호 찾기 
			public CustomerBean findHashPw(String c_id, String c_email) {
				
				CustomerBean customerInfo = null;
				
				String sql = "select * from customer where c_id=? and c_email=?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, c_id);
					pstmt.setString(2, c_email);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						customerInfo = new CustomerBean();//기본값으로 채워진 CustomerDAO객체에
						
						customerInfo.setC_id(c_id);
						customerInfo.setC_email(c_email);
						customerInfo.setC_name(rs.getString("c_name"));//조회한 c_name값을 셋팅
						
					}//if문 끝
					
				}catch(Exception e){
				 System.out.println("findHashPw 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(rs);
					close(pstmt);
				}
				
				
				return customerInfo;
			}
			
			//발급받은 임시비번(랜덤비번)을 다시 DB의 해당 사용자의 비번으로 수정하여 임시비번()으로 로그인할 수 있도록 함
			public int setHashPw(String c_id, String c_email, String ramdom_password) {
				int setHashPwCount = 0;//업데이트 성공 여부  
				
				String sql = "UPDATE customer SET C_PASSWORD = ? WHERE C_ID = ? AND C_EMAIL = ?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, ramdom_password);
					pstmt.setString(2, c_id);
					pstmt.setString(3, c_email);
					
					setHashPwCount = pstmt.executeUpdate();//업데이트에 성공하면 1을 리턴받음
					
				}catch(Exception e){
				 System.out.println("setHashPw 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					//close(rs); // update는 rs를 사용안했기때문에 close()해줄 필요 없음 (select에서는 해줘야함)
					close(pstmt);
				}
					
				return setHashPwCount;
				
				
			}
			//[방법-1]미리 암호하된 비번을 전송받았으므로 그대로 비밀번호 저장 
			/*
			public int changePw(CustomerPwChange customerPwChange) {
				int ChangePwCount = 0;
				
				String sql = "update customer set c_password=? where c_id=? and c_password=? ";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, customerPwChange.getNew_password());//그대로 저장 
					pstmt.setString(2, customerPwChange.getC_id());
					pstmt.setString(2, customerPwChange.getPre_password());
					
					ChangePwCount = pstmt.executeUpdate();//업데이트에 성공하면 1을 리턴받음
					System.out.println("ChangePwCount : " + ChangePwCount);
					
				}catch(Exception e){
				 System.out.println("changePw 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(pstmt);
				}

				return ChangePwCount;
			}
			*/
			//[방법-2]미리 암호하되지 않은 비번을 암호화시켜 저장  
			public int changeHashPw(CustomerPwChange customerPwChange) {
				int ChangePwCount = 0;
				
				String sql = "UPDATE customer SET C_PASSWORD = ? WHERE C_ID = ? AND C_PASSWORD = ?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, customerPwChange.getNew_password());//비번 암호화시켜 저장    
					pstmt.setString(2, customerPwChange.getC_id());
					pstmt.setString(3, customerPwChange.getPre_password());
					
					ChangePwCount = pstmt.executeUpdate();//업데이트에 성공하면 1을 리턴받음
					System.out.println("ChangePwCount : " + ChangePwCount);
					
				}catch(Exception e){
				 System.out.println("changeHashPw 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(pstmt);
				}

				return ChangePwCount;
			}
			
			//회원가입 - customer
			public  int insertCustomer(CustomerBean customer) {
				int insertCustomerCount = 0;
				
				String sql = "insert into customer(c_id,c_grade,c_password,c_name,c_email,c_call,c_joindate) "
						+ "values(?,?,?,?,?,?,now())";
				
				
				try {
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, customer.getC_id());
					pstmt.setString(2, customer.getC_grade());
					pstmt.setString(3, customer.getC_password());
					//pstmt.setString(3, SHA256.encodeSHA256(customer.getC_password()));
					pstmt.setString(4, customer.getC_name());
					pstmt.setString(5, customer.getC_email());
					pstmt.setString(6, customer.getC_call());
					//pstmt.setString(7, customer.getC_joindate());
					
					insertCustomerCount = pstmt.executeUpdate();//업데이트에 성공하면 1을 리턴받음
					System.out.println("insertCustomerCount : " + insertCustomerCount);
					
				}catch (Exception e) {
					System.out.println("insertCustomer 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(pstmt);
				}
				
				return insertCustomerCount;
			}
			
			//회원가입 - address
			public  int insertAddr(Address addr) {
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
			
			
			//회원 수정 - customer
			public int updateCustomer(CustomerBean customer) {
				int updateCustomerCount = 0;
				
				String sql = "update customer set c_name=?, c_email=?, c_call=? "
						+ "where c_id=?";
				
				try {
					//구문객체에 넣어 전달
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, customer.getC_name());
					pstmt.setString(2, customer.getC_email());
					
					pstmt.setString(3, customer.getC_call());
					pstmt.setString(4, customer.getC_id());
					
					updateCustomerCount = pstmt.executeUpdate();//업데이트에 성공하면 1을 리턴받음
					System.out.println("updateCustomerCount : " + updateCustomerCount);
					
				}catch (Exception e) {
					System.out.println("insertCustomer 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(pstmt);
				}
				
				return updateCustomerCount;
				
			}
			
			//회원 수정 - address
			public int updateAddr(Address addr) {
				int updateAddrCount = 0;
				
			String sql = "update address set postcode=? , address1=? , address2=? where c_id=?";
				
			try {
				
				pstmt = con.prepareStatement(sql);
				
				
				pstmt.setInt(1, addr.getPostcode());
				pstmt.setString(2, addr.getAddress1());
				pstmt.setString(3, addr.getAddress2());
				pstmt.setString(4, addr.getC_id());
				
				updateAddrCount = pstmt.executeUpdate();//업데이트에 성공하면 1을 리턴받음
				
				
			}catch (Exception e) {
				System.out.println("updateAddr 에러 : " + e );//e:예외종류+예외메세지
			}finally {
				//JdbcUtil.close(rs);
				//JdbcUtil.close(pstmt);
				close(pstmt);
			}
			
			return updateAddrCount;
			
		}
			//회원탈퇴 
			public int deleteCustomer(String c_id) {
				int deleteCustomerCount = 0;
				
				String sql = "delete from customer where c_id=?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, c_id);
					
					deleteCustomerCount = pstmt.executeUpdate();//업데이트에 성공하면 1을 리턴받음
					System.out.println("deleteCustomerCount : " + deleteCustomerCount);
					
				}catch (Exception e) {
					System.out.println("deleteCustomer 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(pstmt);
				}
				
				return deleteCustomerCount;
			}
			
			//customer안의 회원정보를 viewId로 조회하여 반환
			public CustomerBean selectCustomerInfo(String viewId) {
				CustomerBean customerInfo =null;
					
				String sql = "SELECT * FROM customer WHERE c_id ='" + viewId + "'" ;
				//String sql = "SELECT * FROM customer WHERE c_id =?" ;
					try {
						pstmt = con.prepareStatement(sql);

						
						rs = pstmt.executeQuery();
						
						if(rs.next()) {
							customerInfo = new CustomerBean(); // 기본값으로 채워진 CustomerDAO객체에
							
							
							customerInfo.setC_id(rs.getString("c_id"));
							customerInfo.setC_grade(rs.getString("c_grade"));
							customerInfo.setC_name(rs.getString("c_name"));
							customerInfo.setC_email(rs.getString("c_email"));
							customerInfo.setC_call(rs.getString("c_call"));
						}
						
					}catch(Exception e){
					 System.out.println("selectCustomerInfo 에러 : " + e );//e:예외종류+예외메세지
					}finally {
						//JdbcUtil.close(rs);
						//JdbcUtil.close(pstmt);
						close(rs);
						close(pstmt);
					}
					
					
					return customerInfo;
				
			}
			
		//address안의 회원정보를 viewId로 조회하여 반환
		public Address selectCustomerAddrInfo(String viewId) {
			Address customerAddrInfo =null;
				
			String sql = "SELECT * FROM address WHERE c_id ='" + viewId + "'" ;
				try {
					pstmt = con.prepareStatement(sql);

					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						customerAddrInfo = new Address(); // 기본값으로 채워진 CustomerDAO객체에
						
						
						customerAddrInfo.setC_id(rs.getString("c_id"));
						customerAddrInfo.setPostcode(rs.getInt("postcode"));
						customerAddrInfo.setAddress1(rs.getString("address1"));
						customerAddrInfo.setAddress2(rs.getString("address2"));

					}
					
				}catch(Exception e){
				 System.out.println("selectCustomerAddrInfo 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(rs);
					close(pstmt);
				}
				
				
				return customerAddrInfo;
			}
		
		//Admin에서 회원정보 반환
		public List<CustomerBean> selectCustomerListInfo() {
			List<CustomerBean> customerListInfo = new ArrayList<CustomerBean>();
			
			String sql = "SELECT * FROM customer WHERE c_grade = 'Normal'";
			
			try {
				pstmt = con.prepareStatement(sql);

				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					CustomerBean customer = new CustomerBean();//기본값으로 채워진 CustomerDAO객체에
					
					customer.setC_id(rs.getString("c_id"));
					customer.setC_name(rs.getString("c_name"));
					customer.setC_email(rs.getString("c_email"));
					customer.setC_call(rs.getString("c_call"));
					
					customerListInfo.add(customer);
				}//if문 끝
					
				}catch(Exception e){
				 System.out.println("selectCustomerListInfo 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(rs);
					close(pstmt);
				}
				
			return customerListInfo;
		}
		
		
		
		//Admin에서 회원주소정보 반환
		public List<Address> selectCustomerAddrListInfo() {
			List<Address> customerAddrListInfo = new ArrayList<Address>();
			
			String sql = "SELECT * FROM address";
			try {
					pstmt = con.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						Address customerAddr = new Address(); // 기본값으로 채워진 CustomerDAO객체에
						
						customerAddr.setC_id(rs.getString("c_id"));
						customerAddr.setPostcode(rs.getInt("postcode"));
						customerAddr.setAddress1(rs.getString("address1"));
						customerAddr.setAddress2(rs.getString("address2"));
	
						customerAddrListInfo.add(customerAddr);
					}
			}catch(Exception e){
				 System.out.println("selectCustomerListInfo 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					close(rs);
					close(pstmt);
				}
			return customerAddrListInfo;
		}
			
	}
