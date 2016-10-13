package framework.db.pojo;
// default package

import java.sql.Timestamp;
import java.util.Date;

/**
 * TWfTaskProxy entity. @author MyEclipse Persistence Tools
 */

public class TWfTaskProxy implements java.io.Serializable {

	// Fields

	private String id;
	private String proxyUserId;
	private Date endDate;
	private String createUserId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String entrustUserId;

	// Constructors

	/** default constructor */
	public TWfTaskProxy() {
	}

	/** minimal constructor */
	public TWfTaskProxy(String id, String proxyUserId, Date endDate,
			String createUserId, String flag, Timestamp createTime) {
		this.id = id;
		this.proxyUserId = proxyUserId;
		this.endDate = endDate;
		this.createUserId = createUserId;
		this.flag = flag;
		this.createTime = createTime;
	}

	/** full constructor */
	public TWfTaskProxy(String id, String proxyUserId, Date endDate,
			String createUserId, String flag, Timestamp createTime,
			Timestamp updateTime) {
		this.id = id;
		this.proxyUserId = proxyUserId;
		this.endDate = endDate;
		this.createUserId = createUserId;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public String getEntrustUserId() {
		return entrustUserId;
	}

	public void setEntrustUserId(String entrustUserId) {
		this.entrustUserId = entrustUserId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProxyUserId() {
		return this.proxyUserId;
	}

	public void setProxyUserId(String proxyUserId) {
		this.proxyUserId = proxyUserId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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