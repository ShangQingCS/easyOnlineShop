package cn.sqkj.nsyl.goodsManager.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings("rawtypes")
@Repository("goodsDAO")
public class NsGoodsDAO extends PageDAO {
	//HQL方式
	public int findGoodsCount() {
		Query query = DBUtil.getDBUtilByRequest().getSession().createQuery(" select count(*) from NsGoods ");
	    return ((Number)query.uniqueResult()).intValue();
	}
	
	public List findGoodsPage(PageBean pageBean) {
		return this.queryByHql(" select id as id ,gname as gname from NsGoods as goods ", null, pageBean);
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