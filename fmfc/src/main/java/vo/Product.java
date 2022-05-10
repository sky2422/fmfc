package vo;

import java.util.Date;

public class Product {
	private String p_code;
	private String category;
	private String p_name;
	
	private int p_price;
	
	private String p_detail;
	private String p_status;
	
	private Date p_date;
	private String image;
	
	private int quantity; //메뉴 주문수량 추가함(SQL문에는 없음)
	
	public Product() {}

	public Product(String p_code, String category, String p_name, int p_price, int quantity) {
		super();
		this.p_code = p_code;
		this.category = category;
		this.p_name = p_name;
		this.p_price = p_price;
		this.quantity = quantity;
	}

	//quantity 제외한 생성자
	public Product(String p_code, String category, String p_name, int p_price, String p_detail, String p_status,
			Date p_date, String image) {
		super();
		this.p_code = p_code;
		this.category = category;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_detail = p_detail;
		this.p_status = p_status;
		this.p_date = p_date;
		this.image = image;
	}
	
	//m_date, quantity 제외한 생성자 : m_date는 나중에 오늘 날짜인 now()로 처리함 
	public Product(String p_code, String category, String p_name, int p_price, String p_detail, String p_status,
			String image) {
		super();
		this.p_code = p_code;
		this.category = category;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_detail = p_detail;
		this.p_status = p_status;
		this.image = image;
	}

	public String getP_code() {
		return p_code;
	}

	public void setP_code(String p_code) {
		this.p_code = p_code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getP_detail() {
		return p_detail;
	}

	public void setP_detail(String p_detail) {
		this.p_detail = p_detail;
	}

	public String getP_status() {
		return p_status;
	}

	public void setP_status(String p_status) {
		this.p_status = p_status;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
