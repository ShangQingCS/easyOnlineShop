package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TXtRole entity. @author MyEclipse Persistence Tools
 */

public class TXtRole implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private String roleType;
	private String roleRemark;
	private String roleStateValue;
	private String yxBj;
	private Timestamp lrSj;
	private Timestamp xgSj;

	// Constructors

	/** default constructor */
	public TXtRole() {
	}

	/** minimal constructor */
	public TXtRole(String roleId, String roleName, String roleType,
			Timestamp lrSj) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleType = roleType;
		this.lrSj = lrSj;
	}

	/** full constructor */
	public TXtRole(String roleId, String roleName, String roleType,
			String roleRemark, String roleStateValue, String yxBj,
			Timestamp lrSj, Timestamp xgSj) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleType = roleType;
		this.roleRemark = roleRemark;
		this.roleStateValue = roleStateValue;
		this.yxBj = yxBj;
		this.lrSj = lrSj;
		this.xgSj = xgSj;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleRemark() {
		return this.roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public String getRoleStateValue() {
		return this.roleStateValue;
	}

	public void setRoleStateValue(String roleStateValue) {
		this.roleStateValue = roleStateValue;
	}

	public String getYxBj() {
		return this.yxBj;
	}

	public void setYxBj(String yxBj) {
		this.yxBj = yxBj;
	}

	public Timestamp getLrSj() {
		return this.lrSj;
	}

	public void setLrSj(Timestamp lrSj) {
		this.lrSj = lrSj;
	}

	public Timestamp getXgSj() {
		return this.xgSj;
	}

	public void setXgSj(Timestamp xgSj) {
		this.xgSj = xgSj;
	}

}