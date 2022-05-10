package vo;

public class ActionForward {
	//최종 뷰 페이지의 URL이 저장
		private String path = null;
		
		//포워딩 방식 저장. false이면 디스패치(기존요청), true이면 리다이렉트(새요청)
		private boolean isRedirect = false;

		//매개변수가 없는 생성자 - 반드시 존재(수동으로 만들기)
		public ActionForward() {}
		
		public ActionForward(String path, boolean isRedirect) {
			super();
			this.path = path;
			this.isRedirect = isRedirect;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public boolean isRedirect() {
			return isRedirect;
		}

		public void setRedirect(boolean isRedirect) {
			this.isRedirect = isRedirect;
		}
}
