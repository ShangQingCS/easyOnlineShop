package cn.sqkj.nsyl.goodsManager.pojo;

import java.util.Date;

public class NsGoodsCategory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7393347006869359438L;
	
	private Integer id;
	private String cateName;
	private String desc_;
	private Integer cateOrder;
	private Integer level;
	private String logo;
	private String url;
	private String isuser;
	private Integer parentId;
	private Date createTime;
	private Date updateTime;
	private String flag;

	public NsGoodsCategory() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDesc_() {
		return desc_;
	}

	public void setDesc_(String desc_) {
		this.desc_ = desc_;
	}

	public Integer getCateOrder() {
		return cateOrder;
	}

	public void setCateOrder(Integer cateOrder) {
		this.cateOrder = cateOrder;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsuser() {
		return isuser;
	}

	public void setIsuser(String isuser) {
		this.isuser = isuser;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}