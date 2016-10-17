package cn.sqkj.nsyl.dao;
// default package

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import cn.sqkj.nsyl.db.po.NsEventsinfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * NsEventsinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .NsEventsinfo
 * @author MyEclipse Persistence Tools
 */
public class NsEventsinfoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(NsEventsinfoDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String ISUSE = "isuse";
	public static final String MEMO = "memo";
	public static final String MINPICTURE = "minpicture";
	public static final String PICTURE = "picture";
	public static final String GOODS = "goods";

	public void save(NsEventsinfo transientInstance) {
		log.debug("saving NsEventsinfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NsEventsinfo persistentInstance) {
		log.debug("deleting NsEventsinfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NsEventsinfo findById(java.lang.Long id) {
		log.debug("getting NsEventsinfo instance with id: " + id);
		try {
			NsEventsinfo instance = (NsEventsinfo) getSession().get(
					"NsEventsinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(NsEventsinfo instance) {
		log.debug("finding NsEventsinfo instance by example");
		try {
			List results = getSession().createCriteria("NsEventsinfo")
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
		log.debug("finding NsEventsinfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from NsEventsinfo as model where model."
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

	public List findByIsuse(Object isuse) {
		return findByProperty(ISUSE, isuse);
	}

	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findByMinpicture(Object minpicture) {
		return findByProperty(MINPICTURE, minpicture);
	}

	public List findByPicture(Object picture) {
		return findByProperty(PICTURE, picture);
	}

	public List findByGoods(Object goods) {
		return findByProperty(GOODS, goods);
	}

	public List findAll() {
		log.debug("finding all NsEventsinfo instances");
		try {
			String queryString = "from NsEventsinfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NsEventsinfo merge(NsEventsinfo detachedInstance) {
		log.debug("merging NsEventsinfo instance");
		try {
			NsEventsinfo result = (NsEventsinfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NsEventsinfo instance) {
		log.debug("attaching dirty NsEventsinfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NsEventsinfo instance) {
		log.debug("attaching clean NsEventsinfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}