package dao;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.ArrayList;

import vo.Order;
import vo.OrderDetail;

public class OrderDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/*** 싱글톤 패턴 : OrderDAO객체 단1개만 생성
	 * 이유? 외부클래스에서 "처음 생성된 OrderDAO객체를 공유해서 사용하도록 하기 위해"
	 */
	
	//1.생성자는 무조건 private : 외부 클래스에서 생성자를 호출 불가하여 직접적으로 객체 생성 불가능
	private OrderDAO() {}
	
	private static OrderDAO orderDAO;//2.반드시 static이여야 한다.
	
	//2.메서드가 static인 이유? 객체 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 OrderDAO개체를 1개만 만들수 있도록 하기 위해서
	public static OrderDAO getInstance() {//1.static이면
		if(orderDAO == null) {//OrderDAO 객체가 없으면
			orderDAO = new OrderDAO();//객체생성
		}
		
		return orderDAO;//기존 OrderDAO객체의 주소를 리턴
		
	}	
	/******* 싱글톤 만드는 방법 끝 *******/

	public void setConnection(Connection con) {
		this.con = con;		
	}
	
	/*------- [사용자 모드] -------*/
	public Order selectLastestOrder(String c_id) {
		Order lastestOrder = null;
		//'가장 최근 날짜에 대한 주문내역' : 주문한 내역 중 날짜로 내림차순 정렬 후 첫번째 줄만 가져옴
		String sql = "select * from order_table where c_id=? order by order_date DESC limit 1";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				lastestOrder = new Order(rs.getInt("order_num"),
										 rs.getString("c_id"),
										 rs.getString("c_email"),
										 rs.getTimestamp("order_date"),//sql문에서 order_date를 date타입 대신 timestamp로
										 rs.getString("order_status"),
										 rs.getInt("totalMoney")
										);
			}
			
		}catch(Exception e) {
			System.out.println("selectLastestOrder : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
						
		return lastestOrder;
	}
	
	public ArrayList<Order> selectMyOrderList(String c_id, String c_email) {
		ArrayList<Order> myOrderList = null;
		
		String sql = "select * from order_table where c_id=? and c_email=? order by order_date desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_id);
			pstmt.setString(2, c_email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				myOrderList = new ArrayList<Order>();//기본값으로 채워진
				
			do{ 
				 //조회한 각 주문내역 결과로 채운 Order개체를 추가함
				 myOrderList.add(new Order(rs.getInt("order_num"), 
										   rs.getString("c_id"), 
										   rs.getString("c_email"),
										   rs.getTimestamp("order_date"), //sql문에서 order_date를 date타입 대신 timestamp로
										   rs.getString("order_status"),
										   rs.getInt("totalmoney"))
								);
			 
			}while(rs.next());//주문내역이 없을 때까지 반복함 
		}
	}catch (Exception e) {
		System.out.println(" selectMyOrderList : " + e);
	}finally {
		close(rs);
		close(pstmt);
	}
		
		return myOrderList;
	}
	
	//[사용자 모드]주문번호(order_num)에 대한 '주문 상세 정보 리스트'를 반환
	public ArrayList<OrderDetail> selectMyOrderDetailList(int order_num) {
		ArrayList<OrderDetail> myorderDetailList = null;
		
		String sql = "select * from orderDetail_table where order_num=?";
		
		try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, order_num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					myorderDetailList = new ArrayList<OrderDetail>();//기본값으로 채워진
				
				do {
					myorderDetailList.add(new OrderDetail(rs.getInt("datail_index"),
														rs.getString("p_code"), 
														rs.getInt("order_num"),
														rs.getInt("quantity"), 
														rs.getString("p_name"),
														rs.getInt("p_price")
														)
										);
				   }while(rs.next());
			}
		}catch (Exception e) {
			System.out.println(" selectMyOrderDetailList : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return myorderDetailList;
	}
	
		/** -- [관리자 모드] ----------------------------------------**/
		//실시간 주문관리 - 오늘 날짜로 주문한 List를 조회해서 반환 
		
		public ArrayList<Order> selectDayOrderList(String simpleDate_today) {
			 ArrayList<Order> dayOrderList = null;
				
			 String sql = "select * from order_table where DATE(order_date)=? order by order_date DESC";
				
				try {
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, simpleDate_today);
						rs = pstmt.executeQuery();
						
						if(rs.next()) {
						dayOrderList = new ArrayList<Order>();//기본값으로 채워진
						
						do {
							dayOrderList.add(new Order(rs.getInt("order_num"), 
													   rs.getString("c_id"), 
													   rs.getString("c_email"),
													   rs.getTimestamp("order_date"), //sql문에서 order_date를 date타입 대신 timestamp로
													   rs.getString("order_status"),
													   rs.getInt("totalmoney")
													   )
												);
						   }while(rs.next());
					}
				}catch (Exception e) {
					System.out.println(" selectDayOrderList : " + e);
				}finally {
					close(rs);
					close(pstmt);
				}
				
			
			return dayOrderList;
	}
		public ArrayList<OrderDetail> selectOrderDetailList(int order_num) {
			ArrayList<OrderDetail> orderDetailList = null;
			
			String sql = "select * from orderDetail_table where order_num=?";
			
			try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, order_num);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						orderDetailList = new ArrayList<OrderDetail>();//기본값으로 채워진
					
					do {
						orderDetailList.add(new OrderDetail(rs.getInt("datail_index"),
															rs.getString("p_code"), 
															rs.getInt("order_num"),
															rs.getInt("quantity"), 
															rs.getString("p_name"),
															rs.getInt("p_price")
															)
											);
					   }while(rs.next());
				}
			}catch (Exception e) {
				System.out.println(" selectOrderDetailList : " + e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return orderDetailList;
		}
		
		//주문번호(order_num)로 해당주문을 찾아서 order(주문대기) -> get(주문승인) 상태로 변경 
		public int updateOrderGet(int order_num) {
			int updateOrderGetCount = 0;
			
			String sql = "update order_table set order_status='get' where order_num=?";
			
			try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, order_num);
					updateOrderGetCount = pstmt.executeUpdate();//업데이트 성공하면 1리턴
					
			}catch (Exception e) {
				System.out.println(" updateOrderGet : " + e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return updateOrderGetCount;
		}
		
		//주문번호(order_num)로 해당주문을 찾아서 order(주문대기) -> Cancel(주문취소) 상태로 변경 
		public int updateOrderCancel(int order_num) {
			int updateOrderCancelCount = 0;
			
			String sql = "update order_table set order_status='cancel' where order_num=?";
			
			try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, order_num);
					updateOrderCancelCount = pstmt.executeUpdate();//업데이트 성공하면 1리턴
					
			}catch (Exception e) {
				System.out.println(" updateOrderCancel : " + e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return updateOrderCancelCount;
		}
		
		//[관리자모드]전체주문관리 - 고객들이 주문한 전체 주문 리스트를 조회하여 반환 
		public ArrayList<Order> selectTotalOrderList() {
			 ArrayList<Order> totalOrderList = null;
			 
			 //가장 최근 주문한 것을 제일 위에 표시
			 String sql = "select * from order_table order by order_date DESC";
			 
			 try {
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						totalOrderList = new ArrayList<Order>();//기본값으로 채워진
					
					do {
						totalOrderList.add(new Order(rs.getInt("order_num"), 
												     rs.getString("c_id"), 
												     rs.getString("c_email"),
												     rs.getTimestamp("order_date"), //sql문에서 order_date를 date타입 대신 timestamp로
												     rs.getString("order_status"),
												     rs.getInt("totalmoney")
												     )
											);
					   }while(rs.next());//주문정보가 없을 때까지 반복함
				}
			}catch (Exception e) {
				System.out.println(" selectTotalOrderList : " + e);
			}finally {
				close(rs);
				close(pstmt);
			}

			return totalOrderList;
		}
		
		//관리자 모드에서 [전체주문/매출조회]에서 전체 Order(주문대기) 개수 구하기
		public int selectOrderList() {
			int OrderList = 0;
			
			String sql = "select count(*) from order_table where order_status='order'";
			
				try {
						pstmt = con.prepareStatement(sql);
						rs = pstmt.executeQuery();
						
						if(rs.next()) {
							
							OrderList = rs.getInt(1);
					}
				}catch (Exception e) {
					System.out.println(" selectOrderList : " + e);
				}finally {
					close(rs);
					close(pstmt);
				}
			
			return OrderList;
		}
		
		//관리자 모드에서 [전체주문/매출조회]에서 전체 OrderCancel(주문 취소) 개수 구하기
		public int selectOrderCancelList() {
			int OrderCancelList = 0;
			
			String sql = "select count(*) from order_table where order_status='cancel'";
			
				try {
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						OrderCancelList = rs.getInt(1);
				}
			}catch (Exception e) {
				System.out.println(" selectOrderCancelList : " + e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return OrderCancelList;
		}
	
	
	
	
}

























