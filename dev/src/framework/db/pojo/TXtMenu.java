package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TXtMenu entity. @author MyEclipse Persistence Tools
 */

public class TXtMenu implements java.io.Serializable {

	// Fields

	private String menuId;
	private String menuName;
	private String menuParentId;
	private String menuParentIds;
	private String url;
	private Integer pxXh;
	private String yxBj;
	private Timestamp lrSj;
	private Timestamp xgSj;
	private String openMethod;
	private String logo;

	// Constructors

	/** default constructor */
	public TXtMenu() {
	}

	/** minimal constructor */
	public TXtMenu(String menuId, String menuName, String menuParentId,
			String menuParentIds, Timestamp lrSj) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuParentId = menuParentId;
		this.menuParentIds = menuParentIds;
		this.lrSj = lrSj;
	}

	/** full constructor */
	public TXtMenu(String menuId, String menuName, String menuParentId,
			String menuParentIds, String url, Integer pxXh, String yxBj,
			Timestamp lrSj, Timestamp xgSj, String openMethod) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuParentId = menuParentId;
		this.menuParentIds = menuParentIds;
		this.url = url;
		this.pxXh = pxXh;
		this.yxBj = yxBj;
		this.lrSj = lrSj;
		this.xgSj = xgSj;
		this.openMethod = openMethod;
	}

	// Property accessors

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuParentId() {
		return this.menuParentId;
	}

	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}

	public String getMenuParentIds() {
		return this.menuParentIds;
	}

	public void setMenuParentIds(String menuParentIds) {
		this.menuParentIds = menuParentIds;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPxXh() {
		return this.pxXh;
	}

	public void setPxXh(Integer pxXh) {
		this.pxXh = pxXh;
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

	public String getOpenMethod() {
		return openMethod;
	}

	public void setOpenMethod(String openMethod) {
		this.openMethod = openMethod;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}