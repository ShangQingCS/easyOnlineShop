package cn.sqkj.nsyl.dao;
// default package

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import cn.sqkj.nsyl.db.po.NsAdvertise;

/**
 * A data access object (DAO) providing persistence and search support for
 * NsAdvertise entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .NsAdvertise
 * @author MyEclipse Persistence Tools
 */
public class NsAdvertiseDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(NsAdvertiseDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String MEMO = "memo";
	public static final String IMGURL = "imgurl";
	public static final String LINKKIND = "linkkind";
	public static final String IMGLINK = "imglink";

	public void save(NsAdvertise transientInstance) {
		log.debug("saving NsAdvertise instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NsAdvertise persistentInstance) {
		log.debug("deleting NsAdvertise instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NsAdvertise findById(java.lang.Long id) {
		log.debug("getting NsAdvertise instance with id: " + id);
		try {
			NsAdvertise instance = (NsAdvertise) getSession().get(
					"NsAdvertise", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(NsAdvertise instance) {
		log.debug("finding NsAdvertise instance by example");
		try {
			List results = getSession().createCriteria("NsAdvertise")
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
		log.debug("finding NsAdvertise instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from NsAdvertise as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findByImgurl(Object imgurl) {
		return findByProperty(IMGURL, imgurl);
	}

	public List findByLinkkind(Object linkkind) {
		return findByProperty(LINKKIND, linkkind);
	}

	public List findByImglink(Object imglink) {
		return findByProperty(IMGLINK, imglink);
	}

	public List findAll() {
		log.debug("finding all NsAdvertise instances");
		try {
			String queryString = "from NsAdvertise";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NsAdvertise merge(NsAdvertise detachedInstance) {
		log.debug("merging NsAdvertise instance");
		try {
			NsAdvertise result = (NsAdvertise) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NsAdvertise instance) {
		log.debug("attaching dirty NsAdvertise instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NsAdvertise instance) {
		log.debug("attaching clean NsAdvertise instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}