package cn.sqkj.nsyl.db.po;
// default package

/**
 * NsDictionaries entity. @author MyEclipse Persistence Tools
 */

public class NsDictionaries implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Long parentid;
	private String memo;

	// Constructors

	/** default constructor */
	public NsDictionaries() {
	}

	/** minimal constructor */
	public NsDictionaries(Long id) {
		this.id = id;
	}

	/** full constructor */
	public NsDictionaries(Long id, String name, Long parentid, String memo) {
		this.id = id;
		this.name = name;
		this.parentid = parentid;
		this.memo = memo;
	}

	// Property accessors

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

	public Long getParentid() {
		return this.parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}