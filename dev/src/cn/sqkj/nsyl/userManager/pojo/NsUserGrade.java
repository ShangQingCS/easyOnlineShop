package cn.sqkj.nsyl.userManager.pojo;

import java.io.Serializable;
import java.util.Date;

public class NsUserGrade  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String grade_name;
	private Double grade_fc_level;
	private Double grade_fc_level1;
	private Double grade_fc_level2;
	private Double grade_fc_level3;
	private Double grade_balance;
	private Double grade_tx_balance;
	private Date create_time;
	private Long option_adminid;
	private Date option_time;
	private String option_remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	public Double getGrade_fc_level() {
		return grade_fc_level;
	}
	public void setGrade_fc_level(Double grade_fc_level) {
		this.grade_fc_level = grade_fc_level;
	}
	public Double getGrade_fc_level1() {
		return grade_fc_level1;
	}
	public void setGrade_fc_level1(Double grade_fc_level1) {
		this.grade_fc_level1 = grade_fc_level1;
	}
	public Double getGrade_fc_level2() {
		return grade_fc_level2;
	}
	public void setGrade_fc_level2(Double grade_fc_level2) {
		this.grade_fc_level2 = grade_fc_level2;
	}
	public Double getGrade_fc_level3() {
		return grade_fc_level3;
	}
	public void setGrade_fc_level3(Double grade_fc_level3) {
		this.grade_fc_level3 = grade_fc_level3;
	}
	public Double getGrade_balance() {
		return grade_balance;
	}
	public void setGrade_balance(Double grade_balance) {
		this.grade_balance = grade_balance;
	}
	public Double getGrade_tx_balance() {
		return grade_tx_balance;
	}
	public void setGrade_tx_balance(Double grade_tx_balance) {
		this.grade_tx_balance = grade_tx_balance;
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
	public String getOption_remark() {
		return option_remark;
	}
	public void setOption_remark(String option_remark) {
		this.option_remark = option_remark;
	}
	
}
