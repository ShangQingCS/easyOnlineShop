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
import framework.bean.PageBean;
import framework.config.SysDict;
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
		//HQL方式 
		/*StringBuffer hql = new StringBuffer(" select id as id,gname as gname,price as price,category as category,kind as kind,brand as brand,detail as detail,goodimglist as goodimglist,isuser as isuser,gfullname as gfullname,storenumb as storenumb,goodimg as goodimg,freazes as freazes "
				+ ",categoryName as categoryName "
				//+ ",kindName as kindName "
				//+ ",brandName as brandName "
				+ "from NsGoods as goods where 1=1 ");
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
		pageBean.setPageData(goods);*/
		
		//SQL方式
		StringBuffer sql = new StringBuffer("select t1.*,t2.cate_name categoryName ,t3.cate_name as kindName, t4.cate_name as brandName from ns_goods t1 ");
		sql.append(" left join ns_goods_category t2 on t1.category=t2.id ");
		sql.append(" left join ns_goods_category t3 on t1.kind=t3.id ");
		sql.append(" left join ns_goods_category t4 on t1.brand=t4.id ");
		sql.append(" where 1=1 ");
		List params = new ArrayList();
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("gname"))) {
				sql.append(" and t1.gname like ? ");
				params.add("%"+pageBean.getQueryParams().get("gname")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("category"))) {
				sql.append(" and t1.category = ? ");
				params.add(pageBean.getQueryParams().get("category"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("kind"))) {
				sql.append(" and t1.kind = ? ");
				params.add(pageBean.getQueryParams().get("kind"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("brand"))) {
				sql.append(" and t1.brand = ? ");
				params.add(pageBean.getQueryParams().get("brand"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("storenumbone"))) {
				sql.append(" and t1.storenumb >= ? ");
				params.add(pageBean.getQueryParams().get("storenumbone"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("storenumbtwo"))) {
				sql.append(" and t1.storenumb <= ? ");
				params.add(pageBean.getQueryParams().get("storenumbtwo"));
			}
		}
		
		//查询总计路数
		int total = goodsDAO.findGoodsCount(sql.toString(), params);
		//查询数据
		List<NsGoods> goods = goodsDAO.findGoodsPage(sql.toString(), params, pageBean);
		
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
			goodsCategoryVO.setFlag(SysDict.FLAG_ACT);
			goodsCategoryVO.setIsuse(SysDict.ISUSE_NO);
			Long id = (Long) db.insert(goodsCategoryVO);
			goodsCategoryVO.setId(id);
		} else {
			NsGoodsCategory goodsCategory = (NsGoodsCategory)db.get(NsGoodsCategory.class, goodsCategoryVO.getId());
			goodsCategory.setCateName(goodsCategoryVO.getCateName());
			goodsCategory.setCateOrder(goodsCategoryVO.getCateOrder());
			goodsCategory.setLogo(goodsCategoryVO.getLogo());
			goodsCategory.setUrl(goodsCategoryVO.getUrl());
			goodsCategory.setUpdateTime(DateUtils.getDate());
			goodsCategory.setIsuse(goodsCategoryVO.getIsuse());
			if(StringUtils.isNotBlank(goodsCategoryVO.getLogo())) 
				goodsCategory.setLogo(goodsCategoryVO.getLogo());
			db.update(goodsCategory);
		}
		return goodsCategoryVO;
	}

	public NsGoods queryGoodsById(Long id) throws Exception {
		if(id!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsGoods ng = (NsGoods) db.getSession().get(NsGoods.class, id);
			return ng;
		}
		return null;
	}
}