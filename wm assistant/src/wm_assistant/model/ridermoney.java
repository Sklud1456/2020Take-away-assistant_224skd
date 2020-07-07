package wm_assistant.model;

import java.sql.Timestamp;

public class ridermoney {
	private int rider_no;
	private int PO_NO;
	private Timestamp ridermoney_time;
	private String ridermoney_advise;
	private double ridermoney_money;
	public int getRider_no() {
		return rider_no;
	}
	public void setRider_no(int rider_no) {
		this.rider_no = rider_no;
	}
	public int getPO_NO() {
		return PO_NO;
	}
	public void setPO_NO(int pO_NO) {
		PO_NO = pO_NO;
	}
	public Timestamp getRidermoney_time() {
		return ridermoney_time;
	}
	public void setRidermoney_time(Timestamp ridermoney_time) {
		this.ridermoney_time = ridermoney_time;
	}
	public String getRidermoney_advise() {
		return ridermoney_advise;
	}
	public void setRidermoney_advise(String ridermoney_advise) {
		this.ridermoney_advise = ridermoney_advise;
	}
	public double getRidermoney_money() {
		return ridermoney_money;
	}
	public void setRidermoney_money(double ridermoney_money) {
		this.ridermoney_money = ridermoney_money;
	}
	

}
