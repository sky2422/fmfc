package action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.ProductViewService;
import vo.ActionForward;
import vo.Product;

public class ProductViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String p_code = request.getParameter("p_code");
		
		
		/*------------------[상품 정보 상세 보기]---------------------*/
		ProductViewService productViewService = new ProductViewService();
		Product productInfo = productViewService.getProductView(p_code);
		
		request.setAttribute("productInfo", productInfo);
		
		request.setAttribute("showProduct", "product/productView.jsp");
		
		forward =  new ActionForward("productTemplate.jsp", false);
		return forward;
	}

}
