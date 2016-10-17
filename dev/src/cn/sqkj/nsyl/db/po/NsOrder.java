package cn.sqkj.nsyl.db.po;
// default package

import java.sql.Timestamp;

/**
 * NsOrder entity. @author MyEclipse Persistence Tools
 */

public class NsOrder implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Long total;
	private Integer counts;
	private Long paytype;
	private String outway;
	private Long orderstatus;
	private String deliveryNumb;
	private Timestamp createTime;
	private Timestamp deliveryTime;
	private String paynumb;

	// Constructors

	/** default constructor */
	public NsOrder() {
	}

	/** minimal constructor */
	public NsOrder(Long id, Long userid, Long total, Integer counts,
			Timestamp createTime) {
		this.id = id;
		this.userid = userid;
		this.total = total;
		this.counts = counts;
		this.createTime = createTime;
	}

	/** full constructor */
	public NsOrder(Long id, Long userid, Long total, Integer counts,
			Long paytype, String outway, Long orderstatus, String deliveryNumb,
			Timestamp createTime, Timestamp deliveryTime, String paynumb) {
		this.id = id;
		this.userid = userid;
		this.total = total;
		this.counts = counts;
		this.paytype = paytype;
		this.outway = outway;
		this.orderstatus = orderstatus;
		this.deliveryNumb = deliveryNumb;
		this.createTime = createTime;
		this.deliveryTime = deliveryTime;
		this.paynumb = paynumb;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getTotal() {
		return this.total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getCounts() {
		return this.counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public Long getPaytype() {
		return this.paytype;
	}

	public void setPaytype(Long paytype) {
		this.paytype = paytype;
	}

	public String getOutway() {
		return this.outway;
	}

	public void setOutway(String outway) {
		this.outway = outway;
	}

	public Long getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(Long orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getDeliveryNumb() {
		return this.deliveryNumb;
	}

	public void setDeliveryNumb(String deliveryNumb) {
		this.deliveryNumb = deliveryNumb;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaynumb() {
		return this.paynumb;
	}

	public void setPaynumb(String paynumb) {
		this.paynumb = paynumb;
	}

}