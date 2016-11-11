package cn.sqkj.nsyl.advertiseManager.pojo;

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
	private String imglink;

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

	public String getImglink() {
		return this.imglink;
	}

	public void setImglink(String imglink) {
		this.imglink = imglink;
	}

}