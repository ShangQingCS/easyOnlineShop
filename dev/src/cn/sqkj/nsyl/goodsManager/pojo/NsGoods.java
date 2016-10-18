package cn.sqkj.nsyl.goodsManager.pojo;

public class NsGoods implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4533970991297808751L;
	
	private Long id;
	private String gname;
	private Double price;
	private Long category;
	private Long kind;
	private Long brand;
	private String detail;
	private String goodimglist;
	private Integer isuser;
	private String gfullname;

	public NsGoods() {
	}

	public NsGoods(Long id) {
		this.id = id;
	}

	public NsGoods(Long id, String gname, Double price, Long category,
			Long kind, Long brand, String detail, String goodimglist,
			Integer isuser, String gfullname) {
		this.id = id;
		this.gname = gname;
		this.price = price;
		this.category = category;
		this.kind = kind;
		this.brand = brand;
		this.detail = detail;
		this.goodimglist = goodimglist;
		this.isuser = isuser;
		this.gfullname = gfullname;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getCategory() {
		return this.category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getKind() {
		return this.kind;
	}

	public void setKind(Long kind) {
		this.kind = kind;
	}

	public Long getBrand() {
		return this.brand;
	}

	public void setBrand(Long brand) {
		this.brand = brand;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getGoodimglist() {
		return this.goodimglist;
	}

	public void setGoodimglist(String goodimglist) {
		this.goodimglist = goodimglist;
	}

	public Integer getIsuser() {
		return this.isuser;
	}

	public void setIsuser(Integer isuser) {
		this.isuser = isuser;
	}

	public String getGfullname() {
		return this.gfullname;
	}

	public void setGfullname(String gfullname) {
		this.gfullname = gfullname;
	}

}