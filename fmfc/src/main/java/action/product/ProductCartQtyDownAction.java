package action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.product.ProductCartQtyDownService;
import vo.ActionForward;

public class ProductCartQtyDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String p_code = request.getParameter("p_code");
		
		//장바구니 목록에서 해당 p_code를 찾아 수량 1 감소
		ProductCartQtyDownService productCartQtyDownService = new ProductCartQtyDownService();
		productCartQtyDownService.downCartQty(p_code, request);
		
		//다시 장바구니 목록보기를 요청
		forward = new ActionForward("productCartList.odr", false);
		
		return forward;
	}

}
