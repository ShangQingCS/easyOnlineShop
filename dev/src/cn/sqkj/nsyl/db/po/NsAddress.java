package cn.sqkj.nsyl.db.po;
// default package

/**
 * NsAddress entity. @author MyEclipse Persistence Tools
 */

public class NsAddress implements java.io.Serializable {

	// Fields

	private Long id;
	private Long uerid;
	private String address;
	private String name;
	private String post;
	private String phonenumb;
	private String telnumb;
	private Integer isuse;

	// Constructors

	/** default constructor */
	public NsAddress() {
	}

	/** minimal constructor */
	public NsAddress(Long id, Long uerid, String address, String name,
			Integer isuse) {
		this.id = id;
		this.uerid = uerid;
		this.address = address;
		this.name = name;
		this.isuse = isuse;
	}

	/** full constructor */
	public NsAddress(Long id, Long uerid, String address, String name,
			String post, String phonenumb, String telnumb, Integer isuse) {
		this.id = id;
		this.uerid = uerid;
		this.address = address;
		this.name = name;
		this.post = post;
		this.phonenumb = phonenumb;
		this.telnumb = telnumb;
		this.isuse = isuse;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUerid() {
		return this.uerid;
	}

	public void setUerid(Long uerid) {
		this.uerid = uerid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhonenumb() {
		return this.phonenumb;
	}

	public void setPhonenumb(String phonenumb) {
		this.phonenumb = phonenumb;
	}

	public String getTelnumb() {
		return this.telnumb;
	}

	public void setTelnumb(String telnumb) {
		this.telnumb = telnumb;
	}

	public Integer getIsuse() {
		return this.isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

}