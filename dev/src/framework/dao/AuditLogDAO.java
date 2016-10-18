package framework.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.HibernateSessionFactory;
import framework.db.PageDAO;
import framework.db.pojo.TAuditLog;

@Repository("auditLogDAO")
public class AuditLogDAO extends PageDAO {
	
	private final String T_DRAFT_LOG_SELECT = "select t.id,t.loginname,"
			+ "t.logdetail,t.logsource,t.ip,t.createdate from t_draft_log t where 1=1 ";
	
	/**
	 * 添加日志.
	 * @param log
	 * @return
	 */
	public Integer addAuditLog(TAuditLog log) throws Exception{
		log.setCreatedate(new Date());
		Session session = HibernateSessionFactory.getSession();
		Transaction tra = session.beginTransaction();
		/*String id = UUID.randomUUID().toString();
		log.setId(id);*/
		session.save(log);
		tra.commit();
		return 1;
	}
	
	/**
	 * 分页查询日志
	 * @param pageBean
	 * @param whereSql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TAuditLog> queryLogPagination(PageBean pageBean, String whereSql,String orderbySql) {
		List<TAuditLog> pageData = this.query(T_DRAFT_LOG_SELECT + whereSql + orderbySql, null, DBUtil.getDBUtilByRequest(), pageBean);
		return pageData;
	}
	/**
	 * 统计日志
	 * @param pageBean
	 * @param whereSql
	 * @return
	 */
	public int queryLogCount(PageBean pageBean, String whereSql) {
		return DBUtil.getDBUtilByRequest().queryCountBySQL(T_DRAFT_LOG_SELECT + whereSql, null);
	}
}
