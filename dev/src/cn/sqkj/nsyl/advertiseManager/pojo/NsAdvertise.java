package cn.sqkj.nsyl.advertiseManager.pojo;

import java.sql.Timestamp;

public class NsAdvertise implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String memo;
	private String imgurl;
	private Long linkkind;
	private Long imglink;
	private Integer ordernumb;
	private String type;
	private String isuse;
	private Long kind;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String flag;

	public NsAdvertise() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getImgurl() {
		return this.imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Long getLinkkind() {
		return this.linkkind;
	}

	public void setLinkkind(Long linkkind) {
		this.linkkind = linkkind;
	}

	public Long getImglink() {
		return this.imglink;
	}

	public void setImglink(Long imglink) {
		this.imglink = imglink;
	}

	public Integer getOrdernumb() {
		return ordernumb;
	}

	public void setOrdernumb(Integer ordernumb) {
		this.ordernumb = ordernumb;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public Long getKind() {
		return kind;
	}

	public void setKind(Long kind) {
		this.kind = kind;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}