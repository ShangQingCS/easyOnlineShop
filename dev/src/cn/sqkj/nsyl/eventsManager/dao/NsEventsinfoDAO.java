package cn.sqkj.nsyl.eventsManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sqkj.nsyl.eventsManager.pojo.NsEventsinfo;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("eventsDAO")
public class NsEventsinfoDAO extends PageDAO {
	public int findEventsCount(String sql, List params) {
		return DBUtil.getDBUtilByRequest().queryCountBySQL(sql,params);
	}
	
	public List<NsEventsinfo> findEventsPage(String sql,List params, PageBean pageBean) {
		List<NsEventsinfo> pageData = this.query(sql, params, pageBean);
		return pageData;
	}
}