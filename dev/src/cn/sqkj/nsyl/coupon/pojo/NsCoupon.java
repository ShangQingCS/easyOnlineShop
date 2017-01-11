package cn.sqkj.nsyl.coupon.pojo;

import java.io.Serializable;
import java.util.Date;

public class NsCoupon  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String coupon_name;
	private String coupon_type;
	private Double coupon_zs_balance;
	private Double coupon_blance;
	private Double coupon_xf_balance;
	private Integer coupon_expiry_date;
	private String coupon_remark;
	private String coupon_status;
	private Date create_time;
	private Long option_adminid;
	private Date option_time;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public String getCoupon_type() {
		return coupon_type;
	}
	public void setCoupon_type(String coupon_type) {
		this.coupon_type = coupon_type;
	}
	public Double getCoupon_zs_balance() {
		return coupon_zs_balance;
	}
	public void setCoupon_zs_balance(Double coupon_zs_balance) {
		this.coupon_zs_balance = coupon_zs_balance;
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
	public Integer getCoupon_expiry_date() {
		return coupon_expiry_date;
	}
	public void setCoupon_expiry_date(Integer coupon_expiry_date) {
		this.coupon_expiry_date = coupon_expiry_date;
	}
	public String getCoupon_remark() {
		return coupon_remark;
	}
	public void setCoupon_remark(String coupon_remark) {
		this.coupon_remark = coupon_remark;
	}
	public String getCoupon_status() {
		return coupon_status;
	}
	public void setCoupon_status(String coupon_status) {
		this.coupon_status = coupon_status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Long getOption_adminid() {
		return option_adminid;
	}
	public void setOption_adminid(Long option_adminid) {
		this.option_adminid = option_adminid;
	}
	public Date getOption_time() {
		return option_time;
	}
	public void setOption_time(Date option_time) {
		this.option_time = option_time;
	}
	
	
	
	
}
