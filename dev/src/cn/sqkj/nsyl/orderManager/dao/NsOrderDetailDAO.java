package cn.sqkj.nsyl.orderManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sqkj.nsyl.orderManager.pojo.NsOrder;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("orderDetailDAO")
public class NsOrderDetailDAO extends PageDAO {
	public int findOrderCount(String sql, List params) {
		return DBUtil.getDBUtilByRequest().queryCountBySQL(sql,params);
	}
	
	public List<NsOrder> findOrderPage(String sql,List params, PageBean pageBean) {
		List<NsOrder> pageData = this.query(sql, params, pageBean);
		return pageData;
	}
}