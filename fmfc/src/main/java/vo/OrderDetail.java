package vo;

public class OrderDetail {
  private int datail_index;
  private String p_code; /*메뉴 id*/
  private int order_num; /*주문 번호*/
  private int quantity; /*주문 수량*/
  private String p_name; /*메뉴명*/
  private int p_price; /*메뉴 가격*/
  
  
  public OrderDetail () { super(); }


public OrderDetail(int datail_index, String p_code, int order_num, int quantity, String p_name) {
	super();
	this.datail_index = datail_index;
	this.p_code = p_code;
	this.order_num = order_num;
	this.quantity = quantity;
	this.p_name = p_name;
}


public OrderDetail(int datail_index, String p_code, int order_num, int quantity, String p_name, int p_price) {
	super();
	this.datail_index = datail_index;
	this.p_code = p_code;
	this.order_num = order_num;
	this.quantity = quantity;
	this.p_name = p_name;
	this.p_price = p_price;
}


public int getDatail_index() {
	return datail_index;
}


public void setDatail_index(int datail_index) {
	this.datail_index = datail_index;
}


public String getP_code() {
	return p_code;
}


public void setP_code(String p_code) {
	this.p_code = p_code;
}


public int getOrder_num() {
	return order_num;
}


public void setOrder_num(int order_num) {
	this.order_num = order_num;
}


public int getQuantity() {
	return quantity;
}


public void setQuantity(int quantity) {
	this.quantity = quantity;
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



  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
