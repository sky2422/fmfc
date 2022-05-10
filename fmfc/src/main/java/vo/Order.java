package vo;

import java.util.Date;

public class Order {
	private int order_num;
	private String c_id;
	private String c_email;
	private Date order_date;
	private String order_status;
	private int totalMoney;
	
	public Order() {
		//super();
	}
	
	public Order(int order_num, String c_id, String c_email, Date order_date, String order_status, int totalMoney) {
		super();
		this.order_num = order_num;
		this.c_id = c_id;
		this.c_email = c_email;
		this.order_date = order_date;
		this.order_status = order_status;
		this.totalMoney = totalMoney;
	}

	
	public Order(String order_status) {
		super();
		this.order_status = order_status;
	}

	
	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_email() {
		return c_email;
	}

	public void setC_email(String c_email) {
		this.c_email = c_email;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	
	
	
	
}
