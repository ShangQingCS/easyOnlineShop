package cn.sqkj.nsyl.eventsManager.pojo;

public class NsEventsGoodsVO implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long eventsId;
	private Long goodsId;
	private String gname;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEventsId() {
		return eventsId;
	}
	public void setEventsId(Long eventsId) {
		this.eventsId = eventsId;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}

}