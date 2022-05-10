package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.customer.CustomerUpdateService;
import vo.ActionForward;
import vo.Address;
import vo.CustomerBean;

public class CustomerUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ActionForward forward=null;
	     
	      // 세션에 저장된 값 가져오기
	      
	      //HttpSession session=request.getSession();

	      //String c_id = (String) session.getAttribute("c_id");
	      
	      String c_id = request.getParameter("c_id");
	      String c_name = request.getParameter("c_name");
	      String c_email = request.getParameter("c_email");
	      String c_call = request.getParameter("c_call");
	      
	      
	      int postcode= Integer.parseInt(request.getParameter("postcode")); 
	      String address1=request.getParameter("address1");
	      String address2=request.getParameter("address2");
	      
	      CustomerBean customer = new CustomerBean();
	      customer.setC_id(c_id);
	      customer.setC_name(c_name);
	      customer.setC_email(c_email);
	      customer.setC_call(c_call);
	      /*
	      Address addr = new Address(); // 두가지방법 강사님 안알려주신거
	      
	      addr.setPostcode(postcode);
	      addr.setAddress1(address1);
	      addr.setAddress2(address1);
	      */
	      
	      Address addr =   new Address(c_id, postcode, address1, address2); //dto에 저장 dao 로 전송하기위해 
	      
	      CustomerUpdateService customerUpdateService   =new CustomerUpdateService();
	      boolean customerUpdateSuccess =customerUpdateService.customerUpdate(customer,addr); // int 타입으로 반환값을 돌려받아 1이면 성공으로 받아도됨 현재는 불린 //이것도 혼자할때 다른방법으로 해보기
	      
	      if(customerUpdateSuccess== false) {
	         response.setContentType("text/html;charset=utf-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>");
	         out.println("alert('회원정보 수정에 실패 했습니다 .');");
	         out.println("history.back();");
	         out.println("</script>");
	         
	      }else{
	         HttpSession session=request.getSession();
	         session.setAttribute("c_name", c_name);
	         
	         request.setAttribute("c_name", c_name);
	         
	         request.setAttribute("showPage", "customer/customerUpdateComplete.jsp");
	         forward=new ActionForward("customerTemplate.jsp",false); //디스페쳐로 보내기
	         
	      }
	      
	      
	      
	      return forward;

	}

}
