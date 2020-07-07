package wm_assistant.model;

import java.util.Date;

public class quan {
	private int quan_no;
	private int merchat_no;
	private String quan_name;
	
	private double quan_youhui;
	private int quan_jdyq;
	private Date quan_startdate;
	private Date quan_enddate;
	
	public int getQuan_no() {
		return quan_no;
	}
	public void setQuan_no(int quan_no) {
		this.quan_no = quan_no;
	}
	public int getMerchat_no() {
		return merchat_no;
	}
	public void setMerchat_no(int merchat_no) {
		this.merchat_no = merchat_no;
	}
	public double getQuan_youhui() {
		return quan_youhui;
	}
	public void setQuan_youhui(double quan_youhui) {
		this.quan_youhui = quan_youhui;
	}
	public int getQuan_jdyq() {
		return quan_jdyq;
	}
	public void setQuan_jdyq(int quan_jdyq) {
		this.quan_jdyq = quan_jdyq;
	}
	public Date getQuan_startdate() {
		return quan_startdate;
	}
	public void setQuan_startDate(Date quan_startdate) {
		this.quan_startdate = quan_startdate;
	}
	public Date getQuan_enddate() {
		return quan_enddate;
	}
	public void setQuan_enddate(Date quan_enddate) {
		this.quan_enddate = quan_enddate;
	}
	public String getQuan_name() {
		return quan_name;
	}
	public void setQuan_name(String quan_name) {
		this.quan_name = quan_name;
	}
	
	
	

}
