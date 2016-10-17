package cn.sqkj.nsyl.db.po;
// default package

/**
 * NsEventsinfo entity. @author MyEclipse Persistence Tools
 */

public class NsEventsinfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Integer isuse;
	private String memo;
	private String minpicture;
	private String picture;
	private String goods;

	// Constructors

	/** default constructor */
	public NsEventsinfo() {
	}

	/** minimal constructor */
	public NsEventsinfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public NsEventsinfo(Long id, String name, Integer isuse, String memo,
			String minpicture, String picture, String goods) {
		this.id = id;
		this.name = name;
		this.isuse = isuse;
		this.memo = memo;
		this.minpicture = minpicture;
		this.picture = picture;
		this.goods = goods;
	}

	// Property accessors

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

	public Integer getIsuse() {
		return this.isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMinpicture() {
		return this.minpicture;
	}

	public void setMinpicture(String minpicture) {
		this.minpicture = minpicture;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGoods() {
		return this.goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

}