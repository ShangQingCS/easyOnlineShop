package cn.sqkj.nsyl.db.po;
// default package

/**
 * NsAdvertise entity. @author MyEclipse Persistence Tools
 */

public class NsAdvertise implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String memo;
	private String imgurl;
	private Long linkkind;
	private String imglink;

	// Constructors

	/** default constructor */
	public NsAdvertise() {
	}

	/** minimal constructor */
	public NsAdvertise(Long id) {
		this.id = id;
	}

	/** full constructor */
	public NsAdvertise(Long id, String name, String memo, String imgurl,
			Long linkkind, String imglink) {
		this.id = id;
		this.name = name;
		this.memo = memo;
		this.imgurl = imgurl;
		this.linkkind = linkkind;
		this.imglink = imglink;
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

	public String getImglink() {
		return this.imglink;
	}

	public void setImglink(String imglink) {
		this.imglink = imglink;
	}

}