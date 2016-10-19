package cn.sqkj.nsyl.goodsManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings({"rawtypes"})
@Repository("goodsDAO")
public class NsGoodsDAO extends PageDAO {
	//HQL方式
	public int findGoodsCount(String hql, List params) {
		return DBUtil.getDBUtilByRequest().queryCountByHql(hql, params);
	}
	
	public List findGoodsPage(String hql, List params, PageBean pageBean) {
		return this.queryByHql(hql.toString(), params, pageBean);
	}
	
	//SQL方式
	/*public int findGoodsCount() {
		return DBUtil.getDBUtilByRequest().queryCountBySQL("select count(*) from ns_goods",null);
	}
	
	public List<NsGoods> findGoodsPage(PageBean pageBean) {
		List<NsGoods> pageData = this.query(" select * from ns_goods ", pageBean);
		return pageData;
	}*/
}