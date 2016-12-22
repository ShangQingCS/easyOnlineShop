package cn.sqkj.nsyl.eventsManager.service;

import java.util.List;

import cn.sqkj.nsyl.eventsManager.pojo.NsEventsGoods;
import cn.sqkj.nsyl.eventsManager.pojo.NsEventsinfo;
import framework.bean.PageBean;

/**
 * @author yangchaowen
 * 2016年11月10日
 * 活动管理service接口类
 */
public interface IEventsManagerService {
	/** 
	 * 查询活动列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PageBean queryEventsList(PageBean pageBean) throws Exception;
	
	public NsEventsinfo queryEventsById(Long id) throws Exception;
	
	public List queryEventsGoodsByGoodsId(Long goodsId) throws Exception;
	
	public List queryEventsGoodsByEventsId(Long eventsId)throws Exception;
	
	public List queryEGVOByEventsId(Long eventsId)throws Exception;
}
