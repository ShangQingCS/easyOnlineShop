package framework.db.pojo;

import java.sql.Timestamp;

public class TXtRoleUser implements java.io.Serializable {

	private TXtRoleUserId id;
	private String yxBj;
	private Timestamp lrSj;
	private Timestamp xgSj;

	public TXtRoleUserId getId() {
		return this.id;
	}

	public void setId(TXtRoleUserId id) {
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