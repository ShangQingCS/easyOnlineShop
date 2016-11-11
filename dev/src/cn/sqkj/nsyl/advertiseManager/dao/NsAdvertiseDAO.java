package cn.sqkj.nsyl.advertiseManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sqkj.nsyl.advertiseManager.pojo.NsAdvertise;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("advertiseDAO")
public class NsAdvertiseDAO extends PageDAO {
	public int findAdvCount(String sql, List params) {
		return DBUtil.getDBUtilByRequest().queryCountBySQL(sql,params);
	}
	
	public List<NsAdvertise> findAdvPage(String sql,List params, PageBean pageBean) {
		List<NsAdvertise> pageData = this.query(sql, params, pageBean);
		return pageData;
	}
}