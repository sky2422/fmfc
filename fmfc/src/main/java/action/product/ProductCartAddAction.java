package action.product;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.product.ProductCartAddService;
import vo.ActionForward;
import vo.Product;

public class ProductCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//'장바구니 담기'위해 로그인된 상태인지를 확인
		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("c_id");
		
		if(c_id == null) {
			response.setContentType("text/html; charset=utf-8");//응답할 타입
			//★★주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter();//화면에 출력(자바이기 때문에 직접 출력 스트림을 생성해줘야함)
			out.print("<script>");
			out.print("alert('로그인 후 이용해주세요.');");//경고창을 띄우고
			out.print("location.href = 'customerLogin.cus';");
			out.print("</script>");
		}else {
			ProductCartAddService productCartAddService = new ProductCartAddService();
			
			Product productInfo = productCartAddService.getProductView(request.getParameter("p_code"));
			
			//request 전송이유 => 장바구니 항목을 유지하기 위해 session영역에 추가해야 하므로
			productCartAddService.addCart(request,productInfo);
			
			//★ 반드시 리다이렉트로 포워딩 : 장바구니 항목에 새롭게 추가했으므로
			forward = new ActionForward("productCartList.odr", false);
		}
		
				
		return forward;
	}

}
