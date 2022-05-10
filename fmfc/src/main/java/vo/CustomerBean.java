package vo;

public class CustomerBean {
	/*아래 6개는 회원가입 폼에 있음*/
	private String c_id;
	private String c_grade;/*회원가입 폼에서 숨김 - 첫회원 가입 때 hidden으로 Normal값 전달*/
	private String c_password;
	private String c_name;
	private String c_email;
	private String c_call;
	
	/*아래 3개는 회원가입 폼에 없음*/
	private String c_joindate;
	private int order_quantity;/*향후 삭제 예정*/
	private int order_money;/*향후 삭제 예정*/
	
	//기본생성자 : 
	public CustomerBean () {}

	//생성자 추가
	public CustomerBean(String c_id, String c_grade, String c_password, String c_name, String c_email, String c_call) {
		super();
		this.c_id = c_id;
		this.c_grade = c_grade;
		this.c_password = c_password;
		this.c_name = c_name;
		this.c_email = c_email;
		this.c_call = c_call;
	}
	
	//생성자 추가 
	public CustomerBean(String c_id, String c_name, String c_email, String c_call) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_email = c_email;
		this.c_call = c_call;
	}
	
	//생성자 추가 
	public CustomerBean(String c_id, String c_grade, String c_name, String c_email, String c_call) {
		super();
		this.c_id = c_id;
		this.c_grade = c_grade;
		this.c_name = c_name;
		this.c_email = c_email;
		this.c_call = c_call;
	}

	//get,set 
	public String getC_id() {
		return c_id;
	}

	

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_grade() {
		return c_grade;
	}

	public void setC_grade(String c_grade) {
		this.c_grade = c_grade;
	}

	public String getC_password() {
		return c_password;
	}

	public void setC_password(String c_password) {
		this.c_password = c_password;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_email() {
		return c_email;
	}

	public void setC_email(String c_email) {
		this.c_email = c_email;
	}

	public String getC_call() {
		return c_call;
	}

	public void setC_call(String c_call) {
		this.c_call = c_call;
	}

	public String getC_joindate() {
		return c_joindate;
	}

	public void setC_joindate(String c_joindate) {
		this.c_joindate = c_joindate;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	public int getOrder_money() {
		return order_money;
	}

	public void setOrder_money(int order_money) {
		this.order_money = order_money;
	}
	
	
	
	
}
