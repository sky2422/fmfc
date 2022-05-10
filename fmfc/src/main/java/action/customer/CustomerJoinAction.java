package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.customer.CustomerJoinService;
import vo.ActionForward;
import vo.Address;
import vo.CustomerBean;

public class CustomerJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String c_id = request.getParameter("c_id");
		String c_grade = request.getParameter("c_grade");
		String c_password = request.getParameter("c_password");
		String c_name = request.getParameter("c_name");
		String c_email = request.getParameter("c_email");
		String c_call = request.getParameter("c_call");
		
		int postcode = Integer.parseInt(request.getParameter("postcode"));
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		//[방법-1] 기본값으로 채워진 CustomerBean객체 만듬
		/*
		CustomerBean customer = new CustomerBean();
		customer.setU_id(c_id);
		customer.setU_grade(c_grade);//"Normal"
		customer.setU_password(SHA256.encodeSHA256(request.getParameter("c_password")));//암호화된 비번값  셋팅
		customer.setU_name(c_name);
		customer.setU_email(c_email);
		customer.setU_call(c_call);
		*/
		
		//[방법-2] DAO에서 암호화안된 비번을 Insert하기 전 암호화 시킨 후 insert함
		
		//[방법-3] 생성자에서 비번을 암호화 시킴
		CustomerBean customer = new CustomerBean(c_id, c_grade, c_password, c_name, c_email, c_call);
		
		Address addr = new Address(c_id, postcode, address1, address2);
		
		
		CustomerJoinService customerJoinService = new CustomerJoinService();
		boolean isCustomerJoinSuccess = customerJoinService.customerJoin(customer, addr);
		
		if(isCustomerJoinSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('회원가입 실패')");
			out.println("history.back()");//다시 '로그인 폼보기' 요청
			out.println("</script>");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('회원가입 성공')");
			out.println("history.back()");//다시 '로그인 폼보기' 요청
			out.println("</script>");
			
			forward = new ActionForward("customerLogin.cus", true);//다시 '로그인 폼보기' 요청
		}
		
		
		return forward;
	}

}
