package vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class Cart {//장바구니 
	private String name;
	private int qty;//수량
	private String image;
	private int price;
	
	private String p_code;
	private String category;
	private Date p_date;
	
	private String encoding_p_code;//★★인코딩된 p_code 추가
	
	//기본생성자 
	//public Cart() {}
	
	/* ★★ 링크 방식으로 파라미터 값을 전송하면 자동 인코딩이 되지 않아
	 * 서버쪽에서 한글 파라미터를 받으면 한글이 깨진다.
	 * 그래서 p_code값을 UTF-8로 인코딩해서 반환해주는 메서드를 정의함 
	 */
	public String getEncoding_p_code() {
		
		try {
			encoding_p_code = URLEncoder.encode(p_code, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		return encoding_p_code;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getp_code() {
		return p_code;
	}
	public void setp_code(String p_code) {
		this.p_code = p_code;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getp_date() {
		return p_date;
	}
	public void setp_date(Date p_date) {
		this.p_date = p_date;
	}
	

	
	

}
