package action.admin;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.admin.ProductAddService;
import svc.admin.ProductUpdateService;
import svc.product.ProductViewService;
import vo.ActionForward;
import vo.Product;

public class ProductUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
	int maxSize = 1024 * 1024 * 5;//한번에 올릴 수 있는 최대 용량 5M로 제한 
		
		//파일을 업로드할 서버 상의 디렉토리 경로 
		String uploadFolder = "/images"; 
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(uploadFolder);
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		//파라미터로 전송된 p_code로 제품의 상세정보를 얻어와 신상품인지 아닌지를 판별한다. 
		String p_code = multi.getParameter("p_code");
		//ProductViewService productViewService = new ProductViewService();
		//Product checkProduct = productViewService.getProductView(p_code);
		
		String category = multi.getParameter("category");
		String p_name = multi.getParameter("p_name");
		
		int p_price = Integer.parseInt(multi.getParameter("p_price"));
		
		String p_detail = multi.getParameter("p_detail");
		String p_status = multi.getParameter("p_status");
		//날짜는 DAO에서 now()로 했기때문에 가져올 필요없음 
		String image = multi.getOriginalFileName("image");//서버 상에 업로드 된 원래 파일 이름 
		
		//if(checkProduct == null) {//신상품이면
			Product newProduct = new Product(p_code, category, p_name, p_price, p_detail, p_status, image);
			
			ProductUpdateService productUpdateService = new ProductUpdateService();
			boolean isUpdateProductSuccess = productUpdateService.updateProduct(newProduct);
			
			if(isUpdateProductSuccess) {
				
			forward = new ActionForward("productManage.adm", true);
				
			}else {
				response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
				// ★★ 주의 : jsp가 아니므로 직접 생성해야함
				PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
				out.println("<script>");
				out.println("alert('상품 수정이 실패했습니다.')"); // 경고창을 띄우고
				out.println("history.back();"); //
				out.println("</script>");
			}
		/*
		}else{//신상품가 아니면 
			response.setContentType("text/html; charset=UTF-8"); // 응답할 타입
			// ★★ 주의 : jsp가 아니므로 직접 생성해야함
			PrintWriter out = response.getWriter(); // 화면에 출력 (자바이기 때문에 직접 출력 스트림을 생성해줘야함)			
			out.println("<script>");
			out.println("alert('등록하려는 상품이 이미 존재합니다.')"); // 경고창을 띄우고
			out.println("history.back();"); //
			out.println("</script>");
		}
		*/
		
		return forward;
	}

}
