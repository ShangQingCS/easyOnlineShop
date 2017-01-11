package cn.sqkj.nsyl.userManager.pojo;

import java.io.Serializable;
import java.util.Date;

public class NsUserPurse  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer trade_type;
	private Double trade_amount;
	private String trade_sn;
	private Integer trade_state;
	private Integer option_type;
	private Long user_id;
	private Double user_amount;
	private Integer purse_type;
	private String pay_account;
	private String pay_open_bank;
	private Integer purse_status;
	private Date create_time;
	private Date option_time;
	private Long option_adminid;
	private String option_adminname;
	private String option_remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(Integer trade_type) {
		this.trade_type = trade_type;
	}
	public Double getTrade_amount() {
		return trade_amount;
	}
	public void setTrade_amount(Double trade_amount) {
		this.trade_amount = trade_amount;
	}
	public String getTrade_sn() {
		return trade_sn;
	}
	public void setTrade_sn(String trade_sn) {
		this.trade_sn = trade_sn;
	}
	public Integer getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(Integer trade_state) {
		this.trade_state = trade_state;
	}
	public Integer getOption_type() {
		return option_type;
	}
	public void setOption_type(Integer option_type) {
		this.option_type = option_type;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Double getUser_amount() {
		return user_amount;
	}
	public void setUser_amount(Double user_amount) {
		this.user_amount = user_amount;
	}
	public Integer getPurse_type() {
		return purse_type;
	}
	public void setPurse_type(Integer purse_type) {
		this.purse_type = purse_type;
	}
	public String getPay_account() {
		return pay_account;
	}
	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}
	public String getPay_open_bank() {
		return pay_open_bank;
	}
	public void setPay_open_bank(String pay_open_bank) {
		this.pay_open_bank = pay_open_bank;
	}
	public Integer getPurse_status() {
		return purse_status;
	}
	public void setPurse_status(Integer purse_status) {
		this.purse_status = purse_status;
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
	public Long getOption_adminid() {
		return option_adminid;
	}
	public void setOption_adminid(Long option_adminid) {
		this.option_adminid = option_adminid;
	}
	public String getOption_adminname() {
		return option_adminname;
	}
	public void setOption_adminname(String option_adminname) {
		this.option_adminname = option_adminname;
	}
	public String getOption_remark() {
		return option_remark;
	}
	public void setOption_remark(String option_remark) {
		this.option_remark = option_remark;
	}
	
}
