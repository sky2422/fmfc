package vo;

public class Address {
	private String c_id;
	
	private int postcode;/*우편번호*/
	
	private String address1;
	private String address2;
	
	//기본생성자 
	public Address() {}
	
	public Address(int postcode, String address1, String address2) {
		super();
		//this.c_id = c_id;
		this.postcode = postcode;
		this.address1 = address1;
		this.address2 = address2;
	}
	public Address(String c_id, int postcode, String address1, String address2) {
		super();
		this.c_id = c_id;
		this.postcode = postcode;
		this.address1 = address1;
		this.address2 = address2;
	}


	public String getC_id() {
		return c_id;
	}


	public void setC_id(String c_id) {
		this.c_id = c_id;
	}


	public int getPostcode() {
		return postcode;
	}


	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}


	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	
}
