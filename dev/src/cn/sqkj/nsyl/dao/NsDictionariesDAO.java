package cn.sqkj.nsyl.dao;
// default package

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import cn.sqkj.nsyl.db.po.NsDictionaries;

/**
 * A data access object (DAO) providing persistence and search support for
 * NsDictionaries entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .NsDictionaries
 * @author MyEclipse Persistence Tools
 */
public class NsDictionariesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(NsDictionariesDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PARENTID = "parentid";
	public static final String MEMO = "memo";

	public void save(NsDictionaries transientInstance) {
		log.debug("saving NsDictionaries instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NsDictionaries persistentInstance) {
		log.debug("deleting NsDictionaries instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NsDictionaries findById(java.lang.Long id) {
		log.debug("getting NsDictionaries instance with id: " + id);
		try {
			NsDictionaries instance = (NsDictionaries) getSession().get(
					"NsDictionaries", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(NsDictionaries instance) {
		log.debug("finding NsDictionaries instance by example");
		try {
			List results = getSession().createCriteria("NsDictionaries")
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
		log.debug("finding NsDictionaries instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from NsDictionaries as model where model."
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

	public List findByParentid(Object parentid) {
		return findByProperty(PARENTID, parentid);
	}

	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findAll() {
		log.debug("finding all NsDictionaries instances");
		try {
			String queryString = "from NsDictionaries";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NsDictionaries merge(NsDictionaries detachedInstance) {
		log.debug("merging NsDictionaries instance");
		try {
			NsDictionaries result = (NsDictionaries) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NsDictionaries instance) {
		log.debug("attaching dirty NsDictionaries instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NsDictionaries instance) {
		log.debug("attaching clean NsDictionaries instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}