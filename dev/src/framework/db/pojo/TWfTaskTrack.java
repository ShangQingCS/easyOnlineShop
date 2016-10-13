package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TWfTaskTrack entity. @author MyEclipse Persistence Tools
 */

public class TWfTaskTrack implements java.io.Serializable {

	// Fields

	private String id;
	private String taskId;
	private String workflowTaskId;
	private String activityId;
	private String action;
	private String nextUserIds;
	private String processResult;
	private String processNote;
	private String createUserId;
	private String createOrgId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Timestamp limitDate;
	private String nextTaskIds;
	private String proxyUserId;

	// Constructors

	/** default constructor */
	public TWfTaskTrack() {
	}

	/** minimal constructor */
	public TWfTaskTrack(String id) {
		this.id = id;
	}

	/** full constructor */
	public TWfTaskTrack(String id, String taskId, String workflowTaskId,
			String activityId, String action, String nextUserIds,
			String processResult, String processNote, String createUserId,
			String createOrgId, String flag, Timestamp createTime,
			Timestamp updateTime, Timestamp limitDate) {
		this.id = id;
		this.taskId = taskId;
		this.workflowTaskId = workflowTaskId;
		this.activityId = activityId;
		this.action = action;
		this.nextUserIds = nextUserIds;
		this.processResult = processResult;
		this.processNote = processNote;
		this.createUserId = createUserId;
		this.createOrgId = createOrgId;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.limitDate = limitDate;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getWorkflowTaskId() {
		return this.workflowTaskId;
	}

	public void setWorkflowTaskId(String workflowTaskId) {
		this.workflowTaskId = workflowTaskId;
	}

	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getNextUserIds() {
		return this.nextUserIds;
	}

	public void setNextUserIds(String nextUserIds) {
		this.nextUserIds = nextUserIds;
	}

	public String getProcessResult() {
		return this.processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public String getProcessNote() {
		return this.processNote;
	}

	public void setProcessNote(String processNote) {
		this.processNote = processNote;
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

	public String getNextTaskIds() {
		return nextTaskIds;
	}

	public void setNextTaskIds(String nextTaskIds) {
		this.nextTaskIds = nextTaskIds;
	}

	public String getProxyUserId() {
		return proxyUserId;
	}

	public void setProxyUserId(String proxyUserId) {
		this.proxyUserId = proxyUserId;
	}

}