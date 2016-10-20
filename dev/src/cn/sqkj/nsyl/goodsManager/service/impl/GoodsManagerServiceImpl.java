package cn.sqkj.nsyl.goodsManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.goodsManager.dao.NsGoodsDAO;
import cn.sqkj.nsyl.goodsManager.pojo.NsGoods;
import cn.sqkj.nsyl.goodsManager.pojo.NsGoodsCategory;
import cn.sqkj.nsyl.goodsManager.service.IGoodsManagerService;

import com.opensymphony.xwork2.Action;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.DateUtils;

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

	/**
	 * 删除商品分类
	 * @return
	 * @throws Exception
	 */
	public String deleteGoodsCategory(NsGoodsCategory goodsCategoryVO) throws Exception {
		DBUtil db = DBUtil.getDBUtilByRequest();
		if(goodsCategoryVO.getParentId() == null) {
			return "根节点不能被删除";
		}
		
		//查询是否有子分类
		String hql = " select t.id from NsGoodsCategory as t where t.flag='0' and t.parentId=? ";
		List<NsGoodsCategory> childs = db.queryByHql(hql, goodsCategoryVO.getId());
		if(childs != null && !childs.isEmpty()) {
			return "该分类下存在子分类，不能被删除";
		}
		
		hql = " update NsGoodsCategory set flag='1', isuser='1' where id=? ";
		int count = db.executeHql(hql, goodsCategoryVO.getId());
		db.commit();
		if(count==0) {
			return "删除失败";
		}
		return null;
	}

	/**
	 * 保存商品分类
	 * @throws Exception
	 */
	public NsGoodsCategory saveGoodsCategory(NsGoodsCategory goodsCategoryVO) throws Exception {
		DBUtil db = DBUtil.getDBUtilByRequest();
		if(goodsCategoryVO.getId()==null) {
			goodsCategoryVO.setCreateTime(DateUtils.getDate());
			goodsCategoryVO.setUpdateTime(DateUtils.getDate());
			goodsCategoryVO.setFlag("0");
			goodsCategoryVO.setIsuser("1");
			Integer id = (Integer) db.insert(goodsCategoryVO);
			goodsCategoryVO.setId(id);
		} else {
			NsGoodsCategory goodsCategory = (NsGoodsCategory)db.get(NsGoodsCategory.class, goodsCategoryVO.getId());
			goodsCategory.setCateName(goodsCategoryVO.getCateName());
			goodsCategory.setCateOrder(goodsCategoryVO.getCateOrder());
			goodsCategory.setLogo(goodsCategoryVO.getLogo());
			goodsCategory.setUrl(goodsCategoryVO.getUrl());
			goodsCategory.setUpdateTime(DateUtils.getDate());
			goodsCategory.setIsuser(goodsCategoryVO.getIsuser());
			db.update(goodsCategory);
		}
		return goodsCategoryVO;
	}
}