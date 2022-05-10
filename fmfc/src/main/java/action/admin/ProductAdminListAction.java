package action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.ProductListService;
import vo.ActionForward;
import vo.Product;

public class ProductAdminListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		ProductListService productListService = new ProductListService();
		ArrayList<Product> productList = productListService.getProductList();
		
		request.setAttribute("productList", productList);
		
		
		request.setAttribute("admin_showMenu" , "/admin/productAdminList.jsp");
		request.setAttribute("showAdmin", "admin/admin_template.jsp");
		forward = new ActionForward("adminMain.jsp", false);
		
		return forward;	
		}

}
