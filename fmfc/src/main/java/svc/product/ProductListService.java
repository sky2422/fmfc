package svc.product;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.Product;
import vo.Product;

public class ProductListService {

	public ArrayList<Product> getProductList() {
		//1.커넥션 풀에서 Connection객체 얻어와 
		Connection con = getConnection();//static으로 가져와 JdbcUtil(클래스이름)생략가능
		
		//2.싱글톤 패턴 : DogDAO객체 생성 
		ProductDAO productDAO = ProductDAO.getInstance();//바로 호출해서 사용가능(import static db.JdbcUtil.*)
		
		//3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 전달하여 DB연결하여 작업하도록 서비스해줌
		productDAO.setConnection(con);
		
		/**---- CustomerDAO의 해당 메서드를 호출하여 처리 ----**/
		
		/*-(insert,update,delete) 성공하면 commit 
		 *  실패하면 rollback(select 제외)---*/
		ArrayList<Product> productList = productDAO.selectProductList();
		
		//4.해제 
		close(con);//Connection객체 해제 
				
		return productList;
	}

}
