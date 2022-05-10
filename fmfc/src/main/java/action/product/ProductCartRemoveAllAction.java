package action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.ProductCartRemoveAllService;
import vo.ActionForward;

public class ProductCartRemoveAllAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ProductCartRemoveAllService productCartRemoveAllService = new ProductCartRemoveAllService();
		productCartRemoveAllService.cartRemoveAll(request);
		
		forward = new ActionForward("productCartList.odr",true);
		
		return forward;
	}

}
