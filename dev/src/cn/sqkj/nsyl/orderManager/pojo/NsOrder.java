package cn.sqkj.nsyl.orderManager.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class NsOrder implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private String address;
	private String name;
	private String postcode;
	private String contactnumb;
	private String invoice;
	private String companyname;
	private String content;
	private String remark;
	private BigDecimal commisionCharge;
	private BigDecimal cash;
	private BigDecimal accountAmount;

	public NsOrder() {
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getContactnumb() {
		return contactnumb;
	}

	public void setContactnumb(String contactnumb) {
		this.contactnumb = contactnumb;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getCommisionCharge() {
		return commisionCharge;
	}

	public void setCommisionCharge(BigDecimal commisionCharge) {
		this.commisionCharge = commisionCharge;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}

}