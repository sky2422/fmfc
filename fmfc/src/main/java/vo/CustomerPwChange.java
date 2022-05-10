package vo;

public class CustomerPwChange {
	//멤버변수
		private String c_id;
		private String pre_password;
		private String new_password;
		
		//반드시 기본 생성자
		public CustomerPwChange() {
			super();
		}

		public CustomerPwChange(String c_id, String pre_password, String new_password) {
			super();
			this.c_id = c_id;
			this.pre_password = pre_password;
			this.new_password = new_password;
			
			//[방법-3]DAO에서 암호화된 비번을 전달받았으므로 그래도 저장하는 changePw()호출
			//this.new_password = SHA256.encodeSHA256(new_password);
			
		}

		//메서드
		public String getC_id() {
			return c_id;
		}

		public void setC_id(String c_id) {
			this.c_id = c_id;
		}

		public String getPre_password() {
			return pre_password;
		}

		public void setPre_password(String pre_password) {
			this.pre_password = pre_password;
		}

		public String getNew_password() {
			return new_password;
		}

		public void setNew_password(String new_password) {
			this.new_password = new_password;
		}
}
