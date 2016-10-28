package cn.sqkj.nsyl.goodsManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sqkj.nsyl.goodsManager.pojo.NsGoods;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings({"rawtypes"})
@Repository("goodsDAO")
public class NsGoodsDAO extends PageDAO {
	//HQL方式
	/*public int findGoodsCount(String hql, List params) {
		return DBUtil.getDBUtilByRequest().queryCountByHql(hql, params);
	}
	
	public List findGoodsPage(String hql, List params, PageBean pageBean) {
		return this.queryByHql(hql.toString(), params, pageBean);
	}*/
	
	//SQL方式
	public int findGoodsCount(String sql, List params) {
		return DBUtil.getDBUtilByRequest().queryCountBySQL(sql,params);
	}
	
	@SuppressWarnings("unchecked")
	public List<NsGoods> findGoodsPage(String sql,List params, PageBean pageBean) {
		List<NsGoods> pageData = this.query(sql, params, pageBean);
		return pageData;
	}
}