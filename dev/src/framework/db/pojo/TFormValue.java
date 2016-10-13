package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TFormValue entity. @author MyEclipse Persistence Tools
 */

public class TFormValue implements java.io.Serializable {

	// Fields

	private String formValueId;
	private String formId;
	private String businessCode;
	private String businessName;
	private String formValue;
	private String createUserId;
	private String createOrgId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TFormValue() {
	}

	/** minimal constructor */
	public TFormValue(String formValueId, String formId) {
		this.formValueId = formValueId;
		this.formId = formId;
	}

	/** full constructor */
	public TFormValue(String formValueId, String formId, String businessCode,
			String businessName, String formValue, String createUserId,
			String createOrgId, String flag, Timestamp createTime,
			Timestamp updateTime) {
		this.formValueId = formValueId;
		this.formId = formId;
		this.businessCode = businessCode;
		this.businessName = businessName;
		this.formValue = formValue;
		this.createUserId = createUserId;
		this.createOrgId = createOrgId;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getFormValueId() {
		return this.formValueId;
	}

	public void setFormValueId(String formValueId) {
		this.formValueId = formValueId;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getBusinessCode() {
		return this.businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getFormValue() {
		return this.formValue;
	}

	public void setFormValue(String formValue) {
		this.formValue = formValue;
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

}