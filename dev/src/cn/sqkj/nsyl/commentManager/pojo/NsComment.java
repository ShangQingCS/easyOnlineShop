package cn.sqkj.nsyl.commentManager.pojo;

import java.sql.Timestamp;

public class NsComment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8720848335697833020L;
	private Long id;
	private Long goodid;
	private String comment;
	private Timestamp createTime;
	private Integer score;

	public NsComment() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodid() {
		return this.goodid;
	}

	public void setGoodid(Long goodid) {
		this.goodid = goodid;
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

}