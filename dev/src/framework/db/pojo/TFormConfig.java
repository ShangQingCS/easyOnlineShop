package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TFormConfig entity. @author MyEclipse Persistence Tools
 */

public class TFormConfig implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4331521727300975279L;
	// Fields

	private String formId;
	private String formName;
	private Integer formColumnSize;
	private String formOnloadEvent;
	private String formGroup;
	private String formElemens;
	private String formEvents;
	private String formCss;
	private String formViews;
	private String templateUrl;
	private String workflowId;
	private Integer formVersion;
	private String userId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String synchronizationObject;
	private String editConfigJson; 

	// Constructors

	/** default constructor */
	public TFormConfig() {
	}

	/** minimal constructor */
	public TFormConfig(String formId) {
		this.formId = formId;
	}

	/** full constructor */
	public TFormConfig(String formId, String formName, Integer formColumnSize,
			String formOnloadEvent, String formGroup, String formElemens,
			String formEvents, String formCss, String formViews,
			String templateUrl, String workflowId, Integer formVersion,
			String userId, String flag, Timestamp createTime,
			Timestamp updateTime,String synchronizationObject) {
		this.formId = formId;
		this.formName = formName;
		this.formColumnSize = formColumnSize;
		this.formOnloadEvent = formOnloadEvent;
		this.formGroup = formGroup;
		this.formElemens = formElemens;
		this.formEvents = formEvents;
		this.formCss = formCss;
		this.formViews = formViews;
		this.templateUrl = templateUrl;
		this.workflowId = workflowId;
		this.formVersion = formVersion;
		this.userId = userId;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.synchronizationObject = synchronizationObject;
	}

	// Property accessors

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Integer getFormColumnSize() {
		return this.formColumnSize;
	}

	public void setFormColumnSize(Integer formColumnSize) {
		this.formColumnSize = formColumnSize;
	}

	public String getFormOnloadEvent() {
		return this.formOnloadEvent;
	}

	public void setFormOnloadEvent(String formOnloadEvent) {
		this.formOnloadEvent = formOnloadEvent;
	}

	public String getFormGroup() {
		return this.formGroup;
	}

	public void setFormGroup(String formGroup) {
		this.formGroup = formGroup;
	}

	public String getFormElemens() {
		return this.formElemens;
	}

	public void setFormElemens(String formElemens) {
		this.formElemens = formElemens;
	}

	public String getFormEvents() {
		return this.formEvents;
	}

	public void setFormEvents(String formEvents) {
		this.formEvents = formEvents;
	}

	public String getFormCss() {
		return this.formCss;
	}

	public void setFormCss(String formCss) {
		this.formCss = formCss;
	}

	public String getFormViews() {
		return this.formViews;
	}

	public void setFormViews(String formViews) {
		this.formViews = formViews;
	}

	public String getTemplateUrl() {
		return this.templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	public String getWorkflowId() {
		return this.workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public Integer getFormVersion() {
		return this.formVersion;
	}

	public void setFormVersion(Integer formVersion) {
		this.formVersion = formVersion;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getSynchronizationObject() {
		return synchronizationObject;
	}

	public void setSynchronizationObject(String synchronizationObject) {
		this.synchronizationObject = synchronizationObject;
	}

	public String getEditConfigJson() {
		return editConfigJson;
	}

	public void setEditConfigJson(String editConfigJson) {
		this.editConfigJson = editConfigJson;
	}
	

}