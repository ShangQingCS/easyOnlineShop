package config.hibernate;
// default package

import java.sql.Timestamp;

/**
 * TSysLog entity. @author MyEclipse Persistence Tools
 */

public class TSysLog implements java.io.Serializable {

	// Fields

	private String id;
	private String bizType;
	private String logContent;
	private String createUserId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TSysLog() {
	}

	/** minimal constructor */
	public TSysLog(String id, String createUserId, String flag,
			Timestamp createTime) {
		this.id = id;
		this.createUserId = createUserId;
		this.flag = flag;
		this.createTime = createTime;
	}

	/** full constructor */
	public TSysLog(String id, String bizType, String logContent,
			String createUserId, String flag, Timestamp createTime,
			Timestamp updateTime) {
		this.id = id;
		this.bizType = bizType;
		this.logContent = logContent;
		this.createUserId = createUserId;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBizType() {
		return this.bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getLogContent() {
		return this.logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}