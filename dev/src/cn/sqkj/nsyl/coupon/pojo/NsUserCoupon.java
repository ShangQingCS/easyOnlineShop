package cn.sqkj.nsyl.coupon.pojo;

import java.io.Serializable;
import java.util.Date;

public class NsUserCoupon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long user_id;
	private String user_name;
	private Long coupon_id;
	private String coupon_name;
	private Double coupon_blance;
	private Double coupon_xf_balance;
	private Date coupon_creatdate;
	private Date coupon_expirydate;
	private String order_sn;
	private String coupon_status;
	private Date option_time;
	private Long option_adminid;
	private String option_remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Long getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(Long coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public Double getCoupon_blance() {
		return coupon_blance;
	}
	public void setCoupon_blance(Double coupon_blance) {
		this.coupon_blance = coupon_blance;
	}
	public Double getCoupon_xf_balance() {
		return coupon_xf_balance;
	}
	public void setCoupon_xf_balance(Double coupon_xf_balance) {
		this.coupon_xf_balance = coupon_xf_balance;
	}
	public Date getCoupon_creatdate() {
		return coupon_creatdate;
	}
	public void setCoupon_creatdate(Date coupon_creatdate) {
		this.coupon_creatdate = coupon_creatdate;
	}
	public Date getCoupon_expirydate() {
		return coupon_expirydate;
	}
	public void setCoupon_expirydate(Date coupon_expirydate) {
		this.coupon_expirydate = coupon_expirydate;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getCoupon_status() {
		return coupon_status;
	}
	public void setCoupon_status(String coupon_status) {
		this.coupon_status = coupon_status;
	}
	public Date getOption_time() {
		return option_time;
	}
	public void setOption_time(Date option_time) {
		this.option_time = option_time;
	}
	public Long getOption_adminid() {
		return option_adminid;
	}
	public void setOption_adminid(Long option_adminid) {
		this.option_adminid = option_adminid;
	}
	public String getOption_remark() {
		return option_remark;
	}
	public void setOption_remark(String option_remark) {
		this.option_remark = option_remark;
	}
	
}
