package cn.sqkj.nsyl.dao;
// default package

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import cn.sqkj.nsyl.db.po.NsOrder;

/**
 * A data access object (DAO) providing persistence and search support for
 * NsOrder entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .NsOrder
 * @author MyEclipse Persistence Tools
 */
public class NsOrderDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(NsOrderDAO.class);
	// property constants
	public static final String USERID = "userid";
	public static final String TOTAL = "total";
	public static final String COUNTS = "counts";
	public static final String PAYTYPE = "paytype";
	public static final String OUTWAY = "outway";
	public static final String ORDERSTATUS = "orderstatus";
	public static final String DELIVERY_NUMB = "deliveryNumb";
	public static final String PAYNUMB = "paynumb";

	public void save(NsOrder transientInstance) {
		log.debug("saving NsOrder instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NsOrder persistentInstance) {
		log.debug("deleting NsOrder instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NsOrder findById(java.lang.Long id) {
		log.debug("getting NsOrder instance with id: " + id);
		try {
			NsOrder instance = (NsOrder) getSession().get("NsOrder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(NsOrder instance) {
		log.debug("finding NsOrder instance by example");
		try {
			List results = getSession().createCriteria("NsOrder")
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
		log.debug("finding NsOrder instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from NsOrder as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List findByTotal(Object total) {
		return findByProperty(TOTAL, total);
	}

	public List findByCounts(Object counts) {
		return findByProperty(COUNTS, counts);
	}

	public List findByPaytype(Object paytype) {
		return findByProperty(PAYTYPE, paytype);
	}

	public List findByOutway(Object outway) {
		return findByProperty(OUTWAY, outway);
	}

	public List findByOrderstatus(Object orderstatus) {
		return findByProperty(ORDERSTATUS, orderstatus);
	}

	public List findByDeliveryNumb(Object deliveryNumb) {
		return findByProperty(DELIVERY_NUMB, deliveryNumb);
	}

	public List findByPaynumb(Object paynumb) {
		return findByProperty(PAYNUMB, paynumb);
	}

	public List findAll() {
		log.debug("finding all NsOrder instances");
		try {
			String queryString = "from NsOrder";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NsOrder merge(NsOrder detachedInstance) {
		log.debug("merging NsOrder instance");
		try {
			NsOrder result = (NsOrder) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NsOrder instance) {
		log.debug("attaching dirty NsOrder instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NsOrder instance) {
		log.debug("attaching clean NsOrder instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}