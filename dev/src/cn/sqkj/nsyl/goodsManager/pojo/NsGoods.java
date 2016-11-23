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
	private String isuse;
	private String gfullname;
	private Integer storenumb;
	private String goodimg;
	private Integer freazes;
	/*private NsGoodsCategory categoryName;
	private NsGoodsCategory kindName;
	private NsGoodsCategory brandName;*/
	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private String img5;
	private String goodsCode;
	
	public NsGoods() {
	}

	public NsGoods(Long id) {
		this.id = id;
	}

	public NsGoods(Long id, String gname, Double price, Long category,
			Long kind, Long brand, String detail, String goodimglist,
			String isuse, String gfullname) {
		this.id = id;
		this.gname = gname;
		this.price = price;
		this.category = category;
		this.kind = kind;
		this.brand = brand;
		this.detail = detail;
		this.goodimglist = goodimglist;
		this.isuse = isuse;
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

	public String getIsuse() {
		return this.isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public String getGfullname() {
		return this.gfullname;
	}

	public void setGfullname(String gfullname) {
		this.gfullname = gfullname;
	}

	public Integer getStorenumb() {
		return storenumb;
	}

	public void setStorenumb(Integer storenumb) {
		this.storenumb = storenumb;
	}

	public String getGoodimg() {
		return goodimg;
	}

	public void setGoodimg(String goodimg) {
		this.goodimg = goodimg;
	}

	public Integer getFreazes() {
		return freazes;
	}

	public void setFreazes(Integer freazes) {
		this.freazes = freazes;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getImg4() {
		return img4;
	}

	public void setImg4(String img4) {
		this.img4 = img4;
	}

	public String getImg5() {
		return img5;
	}

	public void setImg5(String img5) {
		this.img5 = img5;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	/*public NsGoodsCategory getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(NsGoodsCategory categoryName) {
		this.categoryName = categoryName;
	}
	
	public NsGoodsCategory getKindName() {
		return kindName;
	}

	public void setKindName(NsGoodsCategory kindName) {
		this.kindName = kindName;
	}

	public NsGoodsCategory getBrandName() {
		return brandName;
	}

	public void setBrandName(NsGoodsCategory brandName) {
		this.brandName = brandName;
	}*/
	
}