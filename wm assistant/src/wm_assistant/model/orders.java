package wm_assistant.model;

public class orders {
	private int PO_no;
	private int product_no;
	private int orders_sum;
	private double orders_price;
	private double orders_youhui;
	public int getPO_no() {
		return PO_no;
	}
	public void setPO_no(int pO_no) {
		PO_no = pO_no;
	}
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public int getOrders_sum() {
		return orders_sum;
	}
	public void setOrders_sum(int orders_sum) {
		this.orders_sum = orders_sum;
	}
	public double getOrders_price() {
		return orders_price;
	}
	public void setOrders_price(double orders_price) {
		this.orders_price = orders_price;
	}
	public double getOrders_youhui() {
		return orders_youhui;
	}
	public void setOrders_youhui(double orders_youhui) {
		this.orders_youhui = orders_youhui;
	}
	
}
