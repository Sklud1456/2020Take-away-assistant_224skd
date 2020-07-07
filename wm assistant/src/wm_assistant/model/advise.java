package wm_assistant.model;

import java.sql.Timestamp;
import java.util.Date;

public class advise {
	private int product_no;
	private int merchat_no;
	private int user_no;
	private String advise_thing;
	private Date advise_date;
	private int advise_star;
	private String advise_photo;
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public int getMerchat_no() {
		return merchat_no;
	}
	public void setMerchat_no(int merchat_no) {
		this.merchat_no = merchat_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getAdvise_thing() {
		return advise_thing;
	}
	public void setAdvise_thing(String advise_thing) {
		this.advise_thing = advise_thing;
	}
	public Date getAdvise_date() {
		return advise_date;
	}
	public void setAdvise_date(Date advise_date) {
		this.advise_date = advise_date;
	}
	public int getAdvise_star() {
		return advise_star;
	}
	public void setAdvise_star(int advise_star) {
		this.advise_star = advise_star;
	}
	public String getAdvise_photo() {
		return advise_photo;
	}
	public void setAdvise_photo(String advise_photo) {
		this.advise_photo = advise_photo;
	}
	
	
}
