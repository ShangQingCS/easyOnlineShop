package cn.sqkj.nsyl.userManager.pojo;

import java.io.Serializable;
import java.util.Date;

public class NsUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String user_name;
	private String nick_name;
	private String true_name;
	private String login_pwd;
	private String pay_pwd;
	private Long user_grade;
	private String user_phone;
	private String user_mail;
	private String user_sex;
	private String identity_card;
	private Date identity_card_validity;
	private String identity_issuing;
	private String identity_status;
	private Double user_ky_balance;
	private Double user_fx_balance;
	private Double user_dj_balance;
	private String tixian_status;
	private Double user_jf_balance;
	private Date create_time;
	private Date option_time;
	private String user_status;
	private Long user_pid;
	private String option_remark;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getTrue_name() {
		return true_name;
	}
	public void setTrue_name(String true_name) {
		this.true_name = true_name;
	}
	public String getLogin_pwd() {
		return login_pwd;
	}
	public void setLogin_pwd(String login_pwd) {
		this.login_pwd = login_pwd;
	}
	public String getPay_pwd() {
		return pay_pwd;
	}
	public void setPay_pwd(String pay_pwd) {
		this.pay_pwd = pay_pwd;
	}
	public Long getUser_grade() {
		return user_grade;
	}
	public void setUser_grade(Long user_grade) {
		this.user_grade = user_grade;
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
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getIdentity_card() {
		return identity_card;
	}
	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}
	public Date getIdentity_card_validity() {
		return identity_card_validity;
	}
	public void setIdentity_card_validity(Date identity_card_validity) {
		this.identity_card_validity = identity_card_validity;
	}
	public String getIdentity_issuing() {
		return identity_issuing;
	}
	public void setIdentity_issuing(String identity_issuing) {
		this.identity_issuing = identity_issuing;
	}
	public String getIdentity_status() {
		return identity_status;
	}
	public void setIdentity_status(String identity_status) {
		this.identity_status = identity_status;
	}
	public Double getUser_ky_balance() {
		return user_ky_balance;
	}
	public void setUser_ky_balance(Double user_ky_balance) {
		this.user_ky_balance = user_ky_balance;
	}
	public Double getUser_fx_balance() {
		return user_fx_balance;
	}
	public void setUser_fx_balance(Double user_fx_balance) {
		this.user_fx_balance = user_fx_balance;
	}
	public Double getUser_dj_balance() {
		return user_dj_balance;
	}
	public void setUser_dj_balance(Double user_dj_balance) {
		this.user_dj_balance = user_dj_balance;
	}
	public String getTixian_status() {
		return tixian_status;
	}
	public void setTixian_status(String tixian_status) {
		this.tixian_status = tixian_status;
	}
	public Double getUser_jf_balance() {
		return user_jf_balance;
	}
	public void setUser_jf_balance(Double user_jf_balance) {
		this.user_jf_balance = user_jf_balance;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getOption_time() {
		return option_time;
	}
	public void setOption_time(Date option_time) {
		this.option_time = option_time;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	public Long getUser_pid() {
		return user_pid;
	}
	public void setUser_pid(Long user_pid) {
		this.user_pid = user_pid;
	}
	public String getOption_remark() {
		return option_remark;
	}
	public void setOption_remark(String option_remark) {
		this.option_remark = option_remark;
	}
	
	
	
}
