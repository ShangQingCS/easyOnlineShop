package cn.sqkj.nsyl.eventsManager.service;

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
}
