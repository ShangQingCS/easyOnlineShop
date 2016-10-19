package cn.sqkj.nsyl.goodsManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
@SuppressWarnings({"rawtypes","unchecked"})
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
		
		StringBuffer hql = new StringBuffer(" select id as id ,gname as gname from NsGoods as goods where 1=1 ");
		List params = new ArrayList();
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("gname"))) {
				hql.append(" and gname like ? ");
				params.add("%"+pageBean.getQueryParams().get("gname")+"%");
			}
		}
		
		//查询总计路数
		int total = goodsDAO.findGoodsCount(hql.toString(), params);
		//查询数据
		List<NsGoods> goods = goodsDAO.findGoodsPage(hql.toString(), params, pageBean);
		
		pageBean.setTotal(total);
		pageBean.setPageData(goods);
		return pageBean;
	}
}