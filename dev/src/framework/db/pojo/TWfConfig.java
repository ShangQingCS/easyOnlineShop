package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TWfConfig entity. @author MyEclipse Persistence Tools
 */

public class TWfConfig implements java.io.Serializable {

	// Fields

	private String id;
	private String processDefinitionId;
	private String processDefinitionKey;
	private String processDefinitionName;
	private String remark;
	private String createUserId;
	private String createOrgId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String joinJson;
	

	// Constructors

	/** default constructor */
	public TWfConfig() {
	}

	/** minimal constructor */
	public TWfConfig(String id) {
		this.id = id;
	}

	/** full constructor */
	public TWfConfig(String id, String processDefinitionId,
			String processDefinitionKey, String processDefinitionName,
			String remark, String createUserId, String createOrgId,
			String flag, Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.processDefinitionId = processDefinitionId;
		this.processDefinitionKey = processDefinitionKey;
		this.processDefinitionName = processDefinitionName;
		this.remark = remark;
		this.createUserId = createUserId;
		this.createOrgId = createOrgId;
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

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionKey() {
		return this.processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionName() {
		return this.processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateOrgId() {
		return this.createOrgId;
	}

	public void setCreateOrgId(String createOrgId) {
		this.createOrgId = createOrgId;
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

	public String getJoinJson() {
		return joinJson;
	}

	public void setJoinJson(String joinJson) {
		this.joinJson = joinJson;
	}

}