package cn.sqkj.nsyl.dao;
// default package

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import cn.sqkj.nsyl.db.po.NsAddress;
import framework.db.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * NsAddress entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .NsAddress
 * @author MyEclipse Persistence Tools
 */
@Repository("nsAddressDAO")
public class NsAddressDAO extends BaseHibernateDAO{
	private static final Log log = LogFactory.getLog(NsAddressDAO.class);
	// property constants
	public static final String UERID = "uerid";
	public static final String ADDRESS = "address";
	public static final String NAME = "name";
	public static final String POST = "post";
	public static final String PHONENUMB = "phonenumb";
	public static final String TELNUMB = "telnumb";
	public static final String ISUSE = "isuse";

	public void save(NsAddress transientInstance) {
		log.debug("saving NsAddress instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NsAddress persistentInstance) {
		log.debug("deleting NsAddress instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NsAddress findById(java.lang.Long id) {
		log.debug("getting NsAddress instance with id: " + id);
		try {
			NsAddress instance = (NsAddress) getSession().get("NsAddress", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(NsAddress instance) {
		log.debug("finding NsAddress instance by example");
		try {
			List results = getSession().createCriteria("NsAddress")
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
		log.debug("finding NsAddress instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from NsAddress as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUerid(Object uerid) {
		return findByProperty(UERID, uerid);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByPost(Object post) {
		return findByProperty(POST, post);
	}

	public List findByPhonenumb(Object phonenumb) {
		return findByProperty(PHONENUMB, phonenumb);
	}

	public List findByTelnumb(Object telnumb) {
		return findByProperty(TELNUMB, telnumb);
	}

	public List findByIsuse(Object isuse) {
		return findByProperty(ISUSE, isuse);
	}

	public List findAll() {
		log.debug("finding all NsAddress instances");
		try {
			String queryString = "from NsAddress";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NsAddress merge(NsAddress detachedInstance) {
		log.debug("merging NsAddress instance");
		try {
			NsAddress result = (NsAddress) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NsAddress instance) {
		log.debug("attaching dirty NsAddress instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NsAddress instance) {
		log.debug("attaching clean NsAddress instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}