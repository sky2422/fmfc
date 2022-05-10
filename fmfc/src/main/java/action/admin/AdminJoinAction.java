package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.AdminJoinService;
import vo.ActionForward;
import vo.Address;
import vo.CustomerBean;

public class AdminJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String c_id = request.getParameter("c_id");
		String c_grade = request.getParameter("c_grade");//"Admin"
		String c_password = request.getParameter("c_password");
		String c_name = request.getParameter("c_name");
		String c_email = request.getParameter("c_email");
		String c_call = request.getParameter("c_call");
		
		int postcode = Integer.parseInt(request.getParameter("postcode"));
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		System.out.println("[AdminJoinAction]");
		System.out.println("a_id : + a_id");
		
		//[방법-1] 기본값으로 채워진 CustomerBean객체 만듬
		/*
		CustomerBean customer = new CustomerBean();
		customer.setC_id(c_id);
		customer.setC_grade(c_grade);//"Normal"
		customer.setC_password(SHA256.encodeSHA256(request.getParameter("c_password")));//암호화된 비번값  셋팅
		customer.setC_name(c_name);
		customer.setC_email(c_email);
		customer.setC_call(c_call);
		*/
		
		//[방법-2] DAO에서 암호화안된 비번을 Insert하기 전 암호화 시킨 후 insert함
		
		//[방법-3] 생성자에서 비번을 암호화 시킴
		CustomerBean admin = new CustomerBean(c_id, c_grade, c_password, c_name, c_email, c_call);
		
		Address addr = new Address(c_id, postcode, address1, address2);
		
		
		AdminJoinService adminJoinService = new AdminJoinService();
		boolean isAdminJoinSuccess = adminJoinService.adminJoin(admin, addr);
		
		if(isAdminJoinSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('관리자등록 실패')");
			out.println("history.back()");//다시 '로그인 폼보기' 요청
			out.println("</script>");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('관리자등록 성공')");
			out.println("history.back()");//다시 '로그인 폼보기' 요청
			out.println("</script>");
			forward = new ActionForward("adminLogin.adm", true);//다시 '로그인 폼보기' 요청
		}
		
		
		return forward;
	}

}
