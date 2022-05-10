package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.ProductDeleteService;
import vo.ActionForward;

public class ProductDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String p_code = request.getParameter("p_code");

		
		if(p_code != null) {//해당 p_code가 있으면
			
			ProductDeleteService productDeleteService = new ProductDeleteService();
			boolean isProductDeleteSuccess = productDeleteService.deleteProduct(p_code);
			
			if(isProductDeleteSuccess ) {
				
				forward = new ActionForward("productManage.adm", true);
				
			}else {
				response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
				// ★★ 주의 : jsp가 아니므로 직접 생성해야함
				PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
				out.println("<script>");
				out.println("alert('상품 삭제가 실패했습니다.')"); // 경고창을 띄우고
				out.println("history.back();"); //
				out.println("</script>");
			}
		}else {//해당 p_code가 없으면
			response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
			// ★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
			out.println("<script>");
			out.println("alert('삭제하려는 상품은 존재하지 않습니다.')"); // 경고창을 띄우고
			out.println("history.back();"); //
			out.println("</script>");
		}

		return forward;
	}

}
