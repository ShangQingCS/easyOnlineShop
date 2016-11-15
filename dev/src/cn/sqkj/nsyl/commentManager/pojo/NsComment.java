package cn.sqkj.nsyl.commentManager.pojo;

import java.sql.Timestamp;

public class NsComment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8720848335697833020L;
	private Long id;
	private Long goodsid;
	private String comment;
	private Timestamp createTime;
	private Integer score;
	private Long userid;
	private String ishidden;
	private String flag;

	public NsComment() {	
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getIshidden() {
		return ishidden;
	}

	public void setIshidden(String ishidden) {
		this.ishidden = ishidden;
	}

	public Long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}