package cn.sqkj.nsyl.dao;
// default package

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import cn.sqkj.nsyl.db.po.NsComment;

/**
 * A data access object (DAO) providing persistence and search support for
 * NsComment entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .NsComment
 * @author MyEclipse Persistence Tools
 */
public class NsCommentDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(NsCommentDAO.class);
	// property constants
	public static final String GOODID = "goodid";
	public static final String COMMENT = "comment";
	public static final String SCORE = "score";

	public void save(NsComment transientInstance) {
		log.debug("saving NsComment instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NsComment persistentInstance) {
		log.debug("deleting NsComment instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NsComment findById(java.lang.Long id) {
		log.debug("getting NsComment instance with id: " + id);
		try {
			NsComment instance = (NsComment) getSession().get("NsComment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(NsComment instance) {
		log.debug("finding NsComment instance by example");
		try {
			List results = getSession().createCriteria("NsComment")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding NsComment instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from NsComment as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGoodid(Object goodid) {
		return findByProperty(GOODID, goodid);
	}

	public List findByComment(Object comment) {
		return findByProperty(COMMENT, comment);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findAll() {
		log.debug("finding all NsComment instances");
		try {
			String queryString = "from NsComment";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NsComment merge(NsComment detachedInstance) {
		log.debug("merging NsComment instance");
		try {
			NsComment result = (NsComment) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NsComment instance) {
		log.debug("attaching dirty NsComment instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NsComment instance) {
		log.debug("attaching clean NsComment instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}