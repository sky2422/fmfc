package action.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.ProductListService;
import vo.ActionForward;
import vo.Product;

public class ProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		ProductListService productListService = new ProductListService();
		ArrayList<Product> productList = productListService.getProductList();
		
		request.setAttribute("productList", productList);
		
		request.setAttribute("showProduct" , "/product/productList.jsp");
		
		//request.setAttribute("showPage", "customerTemplate.jsp");
		forward = new ActionForward("productTemplate.jsp", false);
		
		return forward;
	}

}
