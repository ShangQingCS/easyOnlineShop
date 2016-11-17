package cn.sqkj.nsyl.advertiseManager.service;

import java.util.List;
import java.util.Map;

import cn.sqkj.nsyl.advertiseManager.pojo.NsAdvertise;
import framework.bean.PageBean;

/**
 * @author yangchaowen
 * 2016年11月10日
 * 广告管理service接口类
 */
public interface IAdvertiseManagerService {
	/**
	 * 查询广告列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PageBean queryAdvList(PageBean pageBean) throws Exception;
	
	public NsAdvertise queryAdvById(Long id) throws Exception;
	
	public List<NsAdvertise> queryAdvByParams(Map params) throws Exception;
}
