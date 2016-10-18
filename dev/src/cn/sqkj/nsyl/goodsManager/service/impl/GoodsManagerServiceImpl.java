package cn.sqkj.nsyl.goodsManager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.goodsManager.dao.NsGoodsDAO;
import cn.sqkj.nsyl.goodsManager.pojo.NsGoods;
import cn.sqkj.nsyl.goodsManager.service.IGoodsManagerService;
import framework.bean.PageBean;

/**
 * @author yangchaowen
 * 2016年10月18日
 * 商品维护Service实现类
 */
@Service("goodsManagerService")
public class GoodsManagerServiceImpl implements IGoodsManagerService {
	@Resource(name="goodsDAO")
	private NsGoodsDAO goodsDAO;

	/**
	 * 查询商品列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PageBean queryGoodsList(PageBean pageBean) throws Exception {
		//查询总计路数
		int total = goodsDAO.findGoodsCount();
		//查询数据
		List<NsGoods> goods = goodsDAO.findGoodsPage(pageBean);
		
		pageBean.setTotal(total);
		pageBean.setPageData(goods);
		return pageBean;
	}
}
