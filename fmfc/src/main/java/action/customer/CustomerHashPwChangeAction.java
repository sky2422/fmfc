package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.customer.CustomerHashPwChangeService;
import vo.ActionForward;
import vo.CustomerPwChange;

public class CustomerHashPwChangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
ActionForward forward = null;
		
		String c_id = request.getParameter("c_id");//파라미터로 전송된 아이디
		String pre_password = request.getParameter("pre_password");//이전 비밀번호
		String new_password = request.getParameter("new_password");//새 비밀번호 
		
		//[방법-1] : 미리 비번 암호화해서 객체 생성 (c_id, pre_password, 암호화된 new_password )
		//CustomerPwChange customerPwChange = new CustomerPwChange(c_id, pre_password, SHA256.encodeSHA256(new_password));
		
		//[방법-3] : 생성자에서 비번 암호화해서 객체 생성 (c_id, pre_password, 암호화 안된 new_password )
		//CustomerPwChange customerPwChange = new CustomerPwChange(c_id, pre_password, new_password);
		
		//[방법-2] : 미리 비번 암호화하지 않고 객체 생성 (c_id, pre_password, 암호화 안된 new_password )
		CustomerPwChange customerPwChange = new CustomerPwChange(c_id, pre_password, new_password);
				
		CustomerHashPwChangeService customerHashPwChangeService = new CustomerHashPwChangeService();
		boolean isChangePwSuccess = customerHashPwChangeService.changePw(customerPwChange);
		
		if(isChangePwSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('비밀번호 변경에 실패했습니다. 다시 시도해주세요.')");
			out.println("history.back()");//다시 '로그인 폼보기' 요청
			out.println("</script>");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();//화면에 출력(★★주의 : jsp가 아니므로 직접 생성해야함 )
			out.println("<script>");
			out.println("alert('비밀번호 변경에 성공했습니다. 다시 로그인해 주세요.')");
			out.println("location.href='customerLogin.cus';");//[요청방법-1] 팝업창 뜨면서 이동됨!!(<=이방법 권장!!)
			out.println("</script>");
		}
			//forward = new ActionForward("customerLogin.cus", true);//[요청방법-2]리다이렉트 방식으로 포워딩 (팝업창 안뜨고 바로 이동됨)
		
		return forward;
	}

}
