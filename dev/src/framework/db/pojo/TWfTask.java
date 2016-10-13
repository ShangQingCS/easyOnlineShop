package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TWfTask entity. @author MyEclipse Persistence Tools
 */

public class TWfTask implements java.io.Serializable {

	// Fields

	private String id;
	private String formValueId;
	private String processDefinitionId;
	private String processImplementId;
	private String taskStatus;
	private String currActivityId;
	private String currUserId;
	private String currOrgId;
	private String createUserId;
	private String createOrgId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Timestamp limitDate;
	private Timestamp nextActivityLimitDate;

	// Constructors

	/** default constructor */
	public TWfTask() {
	}

	/** minimal constructor */
	public TWfTask(String id) {
		this.id = id;
	}

	/** full constructor */
	public TWfTask(String id, String formValueId, String processDefinitionId,
			String processImplementId, String taskStatus,
			String currActivityId, String currUserId, String currOrgId,
			String createUserId, String createOrgId, String flag,
			Timestamp createTime, Timestamp updateTime, Timestamp limitDate,
			Timestamp nextActivityLimitDate) {
		this.id = id;
		this.formValueId = formValueId;
		this.processDefinitionId = processDefinitionId;
		this.processImplementId = processImplementId;
		this.taskStatus = taskStatus;
		this.currActivityId = currActivityId;
		this.currUserId = currUserId;
		this.currOrgId = currOrgId;
		this.createUserId = createUserId;
		this.createOrgId = createOrgId;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.limitDate = limitDate;
		this.nextActivityLimitDate = nextActivityLimitDate;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormValueId() {
		return this.formValueId;
	}

	public void setFormValueId(String formValueId) {
		this.formValueId = formValueId;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessImplementId() {
		return this.processImplementId;
	}

	public void setProcessImplementId(String processImplementId) {
		this.processImplementId = processImplementId;
	}

	public String getTaskStatus() {
		return this.taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getCurrActivityId() {
		return this.currActivityId;
	}

	public void setCurrActivityId(String currActivityId) {
		this.currActivityId = currActivityId;
	}

	public String getCurrUserId() {
		return this.currUserId;
	}

	public void setCurrUserId(String currUserId) {
		this.currUserId = currUserId;
	}

	public String getCurrOrgId() {
		return this.currOrgId;
	}

	public void setCurrOrgId(String currOrgId) {
		this.currOrgId = currOrgId;
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

	public Timestamp getLimitDate() {
		return this.limitDate;
	}

	public void setLimitDate(Timestamp limitDate) {
		this.limitDate = limitDate;
	}

	public Timestamp getNextActivityLimitDate() {
		return this.nextActivityLimitDate;
	}

	public void setNextActivityLimitDate(Timestamp nextActivityLimitDate) {
		this.nextActivityLimitDate = nextActivityLimitDate;
	}

}