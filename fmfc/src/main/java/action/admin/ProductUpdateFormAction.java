package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.ProductViewService;
import vo.ActionForward;
import vo.Product;

public class ProductUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//파라미터로 전송된 p_code로 해당 메뉴의 정보를 얻어와 
		String p_code = request.getParameter("p_code");
		ProductViewService productViewService = new ProductViewService();
		Product product_update = productViewService.getProductView(p_code);
		
		request.setAttribute("product_update", product_update);
		
		request.setAttribute("admin_showMenu" , "/admin/productUpdateForm.jsp");
		request.setAttribute("showAdmin", "admin/admin_template.jsp");
		
		forward = new ActionForward("adminMain.jsp", false);
		
		return forward;
	}

}
