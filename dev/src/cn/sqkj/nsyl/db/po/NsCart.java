package cn.sqkj.nsyl.db.po;
// default package

/**
 * NsCart entity. @author MyEclipse Persistence Tools
 */

public class NsCart implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Long goodsid;
	private Integer count;
	private Double price;

	// Constructors

	/** default constructor */
	public NsCart() {
	}

	/** minimal constructor */
	public NsCart(Long id, Long userid, Long goodsid) {
		this.id = id;
		this.userid = userid;
		this.goodsid = goodsid;
	}

	/** full constructor */
	public NsCart(Long id, Long userid, Long goodsid, Integer count,
			Double price) {
		this.id = id;
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

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}