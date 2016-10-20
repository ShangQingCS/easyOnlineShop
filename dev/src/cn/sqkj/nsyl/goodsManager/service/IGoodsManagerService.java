package cn.sqkj.nsyl.goodsManager.service;

import cn.sqkj.nsyl.goodsManager.pojo.NsGoodsCategory;
import framework.bean.PageBean;

public interface IGoodsManagerService {
	
	/**
	 * 查询商品列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PageBean queryGoodsList(PageBean pageBean) throws Exception;
	
	/**
	 * 保存商品分类
	 * @throws Exception
	 */
	public NsGoodsCategory saveGoodsCategory(NsGoodsCategory goodsCategoryVO) throws Exception;
	
	/**
	 * 删除商品分类
	 * @return
	 * @throws Exception
	 */
	public String deleteGoodsCategory(NsGoodsCategory goodsCategoryVO) throws Exception;
}
