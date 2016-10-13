package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TXtRoleMenu entity. @author MyEclipse Persistence Tools
 */

public class TXtRoleMenu implements java.io.Serializable {

	// Fields

	private TXtRoleMenuId id;
	private String yxBj;
	private Timestamp lrSj;
	private Timestamp xgSj;

	// Constructors

	/** default constructor */
	public TXtRoleMenu() {
	}

	/** minimal constructor */
	public TXtRoleMenu(TXtRoleMenuId id, Timestamp lrSj) {
		this.id = id;
		this.lrSj = lrSj;
	}

	/** full constructor */
	public TXtRoleMenu(TXtRoleMenuId id, String yxBj, Timestamp lrSj,
			Timestamp xgSj) {
		this.id = id;
		this.yxBj = yxBj;
		this.lrSj = lrSj;
		this.xgSj = xgSj;
	}

	// Property accessors

	public TXtRoleMenuId getId() {
		return this.id;
	}

	public void setId(TXtRoleMenuId id) {
		this.id = id;
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