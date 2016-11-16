package cn.sqkj.nsyl.eventsManager.pojo;

import java.sql.Timestamp;

public class NsEventsinfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String isuse;
	private String memo;
	private String minpicture;
	private String picture;
	private Timestamp startTime;
	private Timestamp endTime;
	private String flag;

	public NsEventsinfo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsuse() {
		return this.isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMinpicture() {
		return this.minpicture;
	}

	public void setMinpicture(String minpicture) {
		this.minpicture = minpicture;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}