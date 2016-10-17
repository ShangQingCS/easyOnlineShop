package cn.sqkj.nsyl.db.po;
// default package

/**
 * NsOrderDetail entity. @author MyEclipse Persistence Tools
 */

public class NsOrderDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long orderid;
	private Long userid;
	private Long goodsid;
	private Integer count;
	private Long price;

	// Constructors

	/** default constructor */
	public NsOrderDetail() {
	}

	/** full constructor */
	public NsOrderDetail(Long id, Long orderid, Long userid, Long goodsid,
			Integer count, Long price) {
		this.id = id;
		this.orderid = orderid;
		this.userid = userid;
		this.goodsid = goodsid;
		this.count = count;
		this.price = price;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getPrice() {
		return this.price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}