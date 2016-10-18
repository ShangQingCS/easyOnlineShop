package cn.sqkj.nsyl.goodsManager.service;

import framework.bean.PageBean;

public interface IGoodsManagerService {
	
	/**
	 * 查询商品列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PageBean queryGoodsList(PageBean pageBean) throws Exception;
}
