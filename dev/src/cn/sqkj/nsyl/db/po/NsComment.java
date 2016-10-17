package cn.sqkj.nsyl.db.po;
// default package

import java.sql.Timestamp;

/**
 * NsComment entity. @author MyEclipse Persistence Tools
 */

public class NsComment implements java.io.Serializable {

	// Fields

	private Long id;
	private Long goodid;
	private String comment;
	private Timestamp createTime;
	private Integer score;

	// Constructors

	/** default constructor */
	public NsComment() {
	}

	/** minimal constructor */
	public NsComment(Long id, Long goodid, Timestamp createTime) {
		this.id = id;
		this.goodid = goodid;
		this.createTime = createTime;
	}

	/** full constructor */
	public NsComment(Long id, Long goodid, String comment,
			Timestamp createTime, Integer score) {
		this.id = id;
		this.goodid = goodid;
		this.comment = comment;
		this.createTime = createTime;
		this.score = score;
	}

	// Property accessors

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