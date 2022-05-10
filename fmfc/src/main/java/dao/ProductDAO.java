package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Player;
import vo.Product;

public class ProductDAO {
	//멤버변수 (전역변수 : 전체 메서드에서 사용이 가능)
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;

		/*** 싱글톤 패턴 : AdminDAO객체 단1개만 생성 ***
		 * 이유? 외부클래스에서 "처음 생성된 AdminDAO객체를 공유해서 사용하도록 하기 위해"
		 */
		
		//1.생성자는 무조건 private : 외부 클래스에서 생성자를 호출 불가하여 직접적으로 객체 생성 불가능 
		private ProductDAO() {}
		
		private static ProductDAO productDAO;//2.반드시 static이여야 한다.
		
		//2.메서드가 static인 이유? 객체 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 AdminDAO객체를 1개만 만들수 있도록 하기 위해서
		public static ProductDAO getInstance(){//1.static이면
			if(productDAO == null) {//AdminDAO 객체가 없으면 
				productDAO = new ProductDAO();//객체생성
			}
			
			return productDAO;//기존 AdminDAO객체의 주소를 리턴
		}
		/***************** 싱글톤 만드는 방법 끝 *****************/
		
		public void setConnection(Connection con){
			this.con = con;
		}
		
		//상품 상세 정보 조회
		public Product selectProductInfo(String p_code) {
			Product productInfo = null;
			
			String sql = "select * from product where p_code=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, p_code);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					productInfo = new Product(rs.getString("p_code"),
											  rs.getString("category"),
											  rs.getString("p_name"),
											  
											  rs.getInt("p_price"),
											  
											  rs.getString("p_detail"),
											  rs.getString("p_status"),
																		
											  rs.getString("image"));
				}
				
			}catch (Exception e) {
				System.out.println("selectProductInfo 에러 : " + e );//e:예외종류+예외메세지
			}finally {
				//JdbcUtil.close(rs);
				//JdbcUtil.close(pstmt);
				close(rs);
				close(pstmt);
			}
			
			return productInfo;
		}
		
		//새로운 상품 추가 
		public int insertNewProduct(Product newProduct) {
			int insertNewProductCount = 0;
			
			String sql = "insert into Product values(?, ?, ?, ?, ?, ?, now(), ?)";
			
			try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, newProduct.getP_code());
					pstmt.setString(2, newProduct.getCategory());
					pstmt.setString(3, newProduct.getP_name());
					
					pstmt.setInt(4, newProduct.getP_price());//int 타입 
					
					pstmt.setString(5, newProduct.getP_detail());
					pstmt.setString(6, newProduct.getP_status());
					pstmt.setString(7, newProduct.getImage());
					
					insertNewProductCount = pstmt.executeUpdate();//업데이트 성공하면 1리턴
					
			}catch(Exception e){
				 System.out.println("insertNewProduct 에러 : " + e );//e:예외종류+예외메세지
				}finally {
					//JdbcUtil.close(rs);
					//JdbcUtil.close(pstmt);
					//close(rs);
					close(pstmt);
				}
			return insertNewProductCount;
		}
		
		// 상품 수정
		public int updateProduct(Product product) {
			int updateProductCount = 0;

			// [방법-1] : 기존의 이미지를 그대로 사용하려면
			String sql = "update product set category=?, p_name=?, p_price=?, p_detail=?, p_status=?, p_date=now()";

			if (product.getImage() != null) {
				sql += ", image=? '" + product.getImage() + "'";
			}
			System.out.println("[update] 올리는 이미지 파일명 : " + product.getImage());
			sql += " where p_code=?";
			// [방법-2] : 이미지를 다시 올리는 경우
			// String sql = "update product_table set category=?, m_name=?, m_price=?,
			// m_detail=?, m_status=?, m_date=now(), image=? where m_id=?";

			try {
				pstmt = con.prepareStatement(sql); // prepareStatement 객체생성

				// [방법-1] : 기존의 이미지를 그대로 사용하려면
				pstmt.setString(1, product.getCategory());
				pstmt.setString(2, product.getP_name());

				pstmt.setInt(3, product.getP_price());

				pstmt.setString(4, product.getP_detail());
				pstmt.setString(5, product.getP_status());
				// pstmt.setString(6, product.getImage());//있으면 if문 거짓일 때 문제 발생함
				pstmt.setString(6, product.getP_code());

				// [방법-2] : 이미지를 다시 올리는 경우
				/*
				 * pstmt.setString(1, product.getCategory()); pstmt.setString(2, product.getP_name());
				 * 
				 * pstmt.setInt(3, product.getP_price());
				 * 
				 * pstmt.setString(4, product.getP_detail()); pstmt.setString(5,
				 * product.getP_status()); pstmt.setString(6, product.getImage()); pstmt.setString(6,
				 * product.getP_id());
				 */

				updateProductCount = pstmt.executeUpdate();

				//System.out.println("updateProductCount: " + updateProductCount);

			} catch (Exception e) {
				System.out.println(" updateProduct 에러: " + e);
			} finally {
				// close(rs);
				close(pstmt);
			}

			return updateProductCount;
	}
	
		// 상품 삭제
		public int deleteProduct(String p_code) {
			int deleteProductCount = 0;

			String sql = "DELETE FROM product WHERE p_code=?";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, p_code);

				deleteProductCount = pstmt.executeUpdate();

				//System.out.println("deleteProductCount : " + deleteProductCount);

			} catch (Exception e) {
				System.out.println("deleteProduct 에러: " + e);
			} finally {
				// close(rs);
				close(pstmt);
			}

			return deleteProductCount;
		
		}
		
		//리스트 - 이미지,가격 
		public ArrayList<Product> selectProductList() {
			ArrayList<Product> productList = null;
			
			String sql = "select * from product";
			
			try {
				   pstmt = con.prepareStatement(sql);
				   rs = pstmt.executeQuery();
				   
				   if(rs.next()) {
					   productList = new ArrayList<Product>();
					   
					   do {
						   Product product = new Product(rs.getString("p_code"),
								   					  	 rs.getString("category"),
								   					     rs.getString("p_name"),
								   					  
								   					     rs.getInt("p_price"),
								   					     
								   					     rs.getString("p_detail"),
								   					     rs.getString("p_status"),
								   					     
								   					 
								   					     
														 rs.getString("image"));
								   					     					     
						   productList.add(product);
						   
					   }while(rs.next());
					   
				   }//if문 끝
				   
			}catch(Exception e) {
				System.out.println("selectProductList 에러 : " + e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return productList;
			
		}	
			
		public int insertOrderProduct(String c_id, String c_email, ArrayList<Product> productList, int totalMoney) {
			int insertOrderProductCount = 0;
			
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			
			PreparedStatement pstmt3 = null;
			
			//1.[사용자모드] : 주문 테이블(order_table)에 'order(주문대기)'상태로 주문을 넣으면, [관리자모드] : '실시간주문관리'에서 order_status를 'get(주문승인)' 또는 'cancel(주문취소)'로 변경해줘야 함
			String sql = "insert into order_table(c_id, c_email, order_date, order_status, totalmoney) values(?,?,now(),'order',?)";
			
			try {
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, c_id);
				pstmt.setString(2, c_email);
				pstmt.setInt(3, totalMoney);
				
				pstmt.executeUpdate();
				
				//2.주문 테이블(order_table)에서 사용자의 '가장 최근 주문번호'를 얻어와 (방금 1에서 insert한 주문번호 조회)
				pstmt2 = con.prepareStatement("select MAX(order_num) from order_table where c_id=?");
				pstmt2.setString(1, c_id);
				rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {//최근 주문 번확 있으면
					for(int i=0;i<productList.size();i++) {//주문한 내역을 하나씩 가져와
						//★★주문상세테이블(orderDetail_table)에 다시 insert함(주문번호 클릭하면 '주문상세보기' 정보)
						pstmt3 = con.prepareStatement("insert into orderDetail_table(p_code, order_num, quantity, p_name, p_price) values(?,?,?,?,?)");
						
						Product product = productList.get(i);
						pstmt3.setString(1, product.getP_code());/*상품 코드*/
						pstmt3.setInt(2, rs2.getInt("MAX(order_num)"));//★★ 주의 : 1에서 insert한 가장 최근 주문번호 /*주문 번호*/
						pstmt3.setInt(3, product.getQuantity());/*상품 수량*/
						pstmt3.setString(4, product.getP_name());/*상품명*/
						pstmt3.setInt(5, product.getP_price());/*상품 가격*/
						
						insertOrderProductCount = pstmt3.executeUpdate();
						
					}
					
				}
				System.out.println("insertOrderProductCount : " + insertOrderProductCount);				
				
			} catch (Exception e) {
				System.out.println("insertOrderProduct 에러: " + e);
			}finally {
				//close(rs);
				//close(rs2);
				close(pstmt);
				close(pstmt2);
				close(pstmt3);
			}
						
			return insertOrderProductCount;
		}	
}
