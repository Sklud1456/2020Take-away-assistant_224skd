package wm_assistant.model;

import java.sql.Timestamp;
import java.util.Date;

public class users {
	private int user_no;
	private String user_name;
	private String user_sex;
	private String user_password;
	private String user_phone;
	private String user_mail;
	private String user_city;
	private Timestamp user_createtime;
	private String user_vip;
	private Date user_ipvenddate;
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public Timestamp getUser_createtime() {
		return user_createtime;
	}
	public void setUser_createtime(Timestamp user_createtime) {
		this.user_createtime = user_createtime;
	}
	public String getUser_vip() {
		return user_vip;
	}
	public void setUser_vip(String user_vip) {
		this.user_vip = user_vip;
	}
	public Date getUser_ipvenddate() {
		return user_ipvenddate;
	}
	public void setUser_ipvenddate(Date user_ipvenddate) {
		this.user_ipvenddate = user_ipvenddate;
	}

}
