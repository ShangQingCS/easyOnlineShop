package cn.sqkj.nsyl.dao;
// default package

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import cn.sqkj.nsyl.db.po.NsOrderDetail;

/**
 * A data access object (DAO) providing persistence and search support for
 * NsOrderDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .NsOrderDetail
 * @author MyEclipse Persistence Tools
 */
public class NsOrderDetailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(NsOrderDetailDAO.class);
	// property constants
	public static final String ORDERID = "orderid";
	public static final String USERID = "userid";
	public static final String GOODSID = "goodsid";
	public static final String COUNT = "count";
	public static final String PRICE = "price";

	public void save(NsOrderDetail transientInstance) {
		log.debug("saving NsOrderDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NsOrderDetail persistentInstance) {
		log.debug("deleting NsOrderDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NsOrderDetail findById(java.lang.Long id) {
		log.debug("getting NsOrderDetail instance with id: " + id);
		try {
			NsOrderDetail instance = (NsOrderDetail) getSession().get(
					"NsOrderDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(NsOrderDetail instance) {
		log.debug("finding NsOrderDetail instance by example");
		try {
			List results = getSession().createCriteria("NsOrderDetail")
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
		log.debug("finding NsOrderDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from NsOrderDetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOrderid(Object orderid) {
		return findByProperty(ORDERID, orderid);
	}

	public List findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List findByGoodsid(Object goodsid) {
		return findByProperty(GOODSID, goodsid);
	}

	public List findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findAll() {
		log.debug("finding all NsOrderDetail instances");
		try {
			String queryString = "from NsOrderDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NsOrderDetail merge(NsOrderDetail detachedInstance) {
		log.debug("merging NsOrderDetail instance");
		try {
			NsOrderDetail result = (NsOrderDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NsOrderDetail instance) {
		log.debug("attaching dirty NsOrderDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NsOrderDetail instance) {
		log.debug("attaching clean NsOrderDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}