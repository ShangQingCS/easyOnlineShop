package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TWfActivityConfig entity. @author MyEclipse Persistence Tools
 */

public class TWfActivityConfig implements java.io.Serializable {

	// Fields

	private String id;
	private String processDefinitionId;
	private String activityId;
	private String activityName;
	private String activityType;
	private String activityOptions;
	private String orgLevel;
	private String roleIds;
	private String postIds;
	private String orgIds;
	private Integer timeout;
	private String formEditFlag;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TWfActivityConfig() {
	}

	/** minimal constructor */
	public TWfActivityConfig(String id) {
		this.id = id;
	}

	/** full constructor */
	public TWfActivityConfig(String id, String processDefinitionId,
			String activityId, String activityName, String activityType,
			String activityOptions, String orgLevel, String roleIds,
			String postIds, String orgIds, Integer timeout,
			String formEditFlag, String flag, Timestamp createTime,
			Timestamp updateTime) {
		this.id = id;
		this.processDefinitionId = processDefinitionId;
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityType = activityType;
		this.activityOptions = activityOptions;
		this.orgLevel = orgLevel;
		this.roleIds = roleIds;
		this.postIds = postIds;
		this.orgIds = orgIds;
		this.timeout = timeout;
		this.formEditFlag = formEditFlag;
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

	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityOptions() {
		return this.activityOptions;
	}

	public void setActivityOptions(String activityOptions) {
		this.activityOptions = activityOptions;
	}

	public String getOrgLevel() {
		return this.orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getRoleIds() {
		return this.roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getPostIds() {
		return this.postIds;
	}

	public void setPostIds(String postIds) {
		this.postIds = postIds;
	}

	public String getOrgIds() {
		return this.orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public Integer getTimeout() {
		return this.timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getFormEditFlag() {
		return this.formEditFlag;
	}

	public void setFormEditFlag(String formEditFlag) {
		this.formEditFlag = formEditFlag;
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