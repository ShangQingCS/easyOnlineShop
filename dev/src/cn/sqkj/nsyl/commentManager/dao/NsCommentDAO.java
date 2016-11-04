package cn.sqkj.nsyl.commentManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sqkj.nsyl.commentManager.pojo.NsComment;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("commentDAO")
public class NsCommentDAO extends PageDAO {
	public int findCommentCount(String sql, List params) {
		return DBUtil.getDBUtilByRequest().queryCountBySQL(sql,params);
	}
	
	public List<NsComment> findCommentPage(String sql,List params, PageBean pageBean) {
		List<NsComment> pageData = this.query(sql, params, pageBean);
		return pageData;
	}
}