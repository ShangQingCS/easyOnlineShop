package cn.sqkj.nsyl.goodsManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sqkj.nsyl.goodsManager.pojo.NsGoods;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@SuppressWarnings("unchecked")
@Repository("goodsDAO")
public class NsGoodsDAO extends PageDAO {
	/*
	 * 这里分页不能用hql，原因返回前台后easyui的datagrid无法显示
	 */
	/*public int findGoodsCount() {
		Query query = DBUtil.getDBUtilByRequest().getSession().createQuery(" select count(*) from NsGoods ");
	    return ((Number)query.uniqueResult()).intValue();
	}
	
	public List<NsGoods> findGoodsPage(PageBean pageBean) {
		List<NsGoods> pageData = this.queryByHql(" select id,gname from NsGoods as goods ", null, pageBean);
		return pageData;
	}*/
	
	public int findGoodsCount() {
		return DBUtil.getDBUtilByRequest().queryCountBySQL("select count(*) from ns_goods",null);
	}
	
	public List<NsGoods> findGoodsPage(PageBean pageBean) {
		List<NsGoods> pageData = this.query(" select * from ns_goods ", pageBean);
		return pageData;
	}
}