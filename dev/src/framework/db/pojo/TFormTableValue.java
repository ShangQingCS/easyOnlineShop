package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TFormTableValue entity. @author MyEclipse Persistence Tools
 */

public class TFormTableValue implements java.io.Serializable {

	// Fields

	private String id;
	private String groupId;
	private String tableValue;
	private String createUserId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TFormTableValue() {
	}

	/** minimal constructor */
	public TFormTableValue(String id, String createUserId, Timestamp createTime) {
		this.id = id;
		this.createUserId = createUserId;
		this.createTime = createTime;
	}

	/** full constructor */
	public TFormTableValue(String id, String groupId, String tableValue,
			String createUserId, String flag, Timestamp createTime,
			Timestamp updateTime) {
		this.id = id;
		this.groupId = groupId;
		this.tableValue = tableValue;
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

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTableValue() {
		return this.tableValue;
	}

	public void setTableValue(String tableValue) {
		this.tableValue = tableValue;
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