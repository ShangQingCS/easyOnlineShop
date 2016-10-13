package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TPbUploadfile entity. @author MyEclipse Persistence Tools
 */

public class TPbUploadfile implements java.io.Serializable {

	// Fields

	private String fileId;
	private Integer parentFileId;
	private String fileName;
	private String fileType;
	private Integer fileSize;
	private String filePath;
	private String upType;
	private String lrryDm;
	private String userType;
	private String xzfw;
	private String yxBj;
	private Timestamp lrSj;
	private Timestamp xgSj;
	private String groupId;

	// Constructors

	/** default constructor */
	public TPbUploadfile() {
	}

	/** minimal constructor */
	public TPbUploadfile(String fileId, String fileName, Integer fileSize,
			String filePath, String lrryDm, String userType, String yxBj,
			Timestamp lrSj, String groupId) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.filePath = filePath;
		this.lrryDm = lrryDm;
		this.userType = userType;
		this.yxBj = yxBj;
		this.lrSj = lrSj;
		this.groupId = groupId;
	}

	/** full constructor */
	public TPbUploadfile(String fileId, Integer parentFileId, String fileName,
			String fileType, Integer fileSize, String filePath, String upType,
			String lrryDm, String userType, String xzfw, String yxBj,
			Timestamp lrSj, Timestamp xgSj,String groupId) {
		this.fileId = fileId;
		this.parentFileId = parentFileId;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.filePath = filePath;
		this.upType = upType;
		this.lrryDm = lrryDm;
		this.userType = userType;
		this.xzfw = xzfw;
		this.yxBj = yxBj;
		this.lrSj = lrSj;
		this.xgSj = xgSj;
		this.groupId = groupId;
	}

	// Property accessors

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getParentFileId() {
		return this.parentFileId;
	}

	public void setParentFileId(Integer parentFileId) {
		this.parentFileId = parentFileId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUpType() {
		return this.upType;
	}

	public void setUpType(String upType) {
		this.upType = upType;
	}

	public String getLrryDm() {
		return this.lrryDm;
	}

	public void setLrryDm(String lrryDm) {
		this.lrryDm = lrryDm;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getXzfw() {
		return this.xzfw;
	}

	public void setXzfw(String xzfw) {
		this.xzfw = xzfw;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	

}