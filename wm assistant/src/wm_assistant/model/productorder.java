package wm_assistant.model;

import java.sql.Timestamp;

public class productorder {
	private int PO_no;
	private int quan_no;
	private int merchat_no;
	private int user_no;
	private int address_no;
	private int manjian_no;
	private int rider_no;
	private double PO_originM;
	private double PO_finalM;
	private Timestamp PO_ordertime;
	private Timestamp PO_sendtime;
	private String PO_sit;
	public int getPO_no() {
		return PO_no;
	}
	public void setPO_no(int pO_no) {
		PO_no = pO_no;
	}
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
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getAddress_no() {
		return address_no;
	}
	public void setAddress_no(int address_no) {
		this.address_no = address_no;
	}
	public int getManjian_no() {
		return manjian_no;
	}
	public void setManjian_no(int manjian_no) {
		this.manjian_no = manjian_no;
	}
	public int getRider_no() {
		return rider_no;
	}
	public void setRider_no(int rider_no) {
		this.rider_no = rider_no;
	}
	public double getPO_originM() {
		return PO_originM;
	}
	public void setPO_originM(double pO_originM) {
		PO_originM = pO_originM;
	}
	public double getPO_finalM() {
		return PO_finalM;
	}
	public void setPO_finalM(double pO_finalM) {
		PO_finalM = pO_finalM;
	}
	public Timestamp getPO_ordertime() {
		return PO_ordertime;
	}
	public void setPO_ordertime(Timestamp pO_ordertime) {
		PO_ordertime = pO_ordertime;
	}
	public Timestamp getPO_sendtime() {
		return PO_sendtime;
	}
	public void setPO_sendtime(Timestamp pO_sendtime) {
		PO_sendtime = pO_sendtime;
	}
	public String getPO_sit() {
		return PO_sit;
	}
	public void setPO_sit(String pO_sit) {
		PO_sit = pO_sit;
	}
	

}
