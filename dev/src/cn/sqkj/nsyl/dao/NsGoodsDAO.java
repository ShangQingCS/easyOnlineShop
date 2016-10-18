package cn.sqkj.nsyl.dao;
// default package

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import cn.sqkj.nsyl.goodsManager.pojo.NsGoods;

/**
 * A data access object (DAO) providing persistence and search support for
 * NsGoods entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .NsGoods
 * @author MyEclipse Persistence Tools
 */
public class NsGoodsDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(NsGoodsDAO.class);
	// property constants
	public static final String GNAME = "gname";
	public static final String PRICE = "price";
	public static final String CATEGORY = "category";
	public static final String KIND = "kind";
	public static final String BRAND = "brand";
	public static final String DETAIL = "detail";
	public static final String GOODIMGLIST = "goodimglist";
	public static final String ISUSER = "isuser";
	public static final String GFULLNAME = "gfullname";

	public void save(NsGoods transientInstance) {
		log.debug("saving NsGoods instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NsGoods persistentInstance) {
		log.debug("deleting NsGoods instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NsGoods findById(java.lang.Long id) {
		log.debug("getting NsGoods instance with id: " + id);
		try {
			NsGoods instance = (NsGoods) getSession().get("NsGoods", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(NsGoods instance) {
		log.debug("finding NsGoods instance by example");
		try {
			List results = getSession().createCriteria("NsGoods")
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
		log.debug("finding NsGoods instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from NsGoods as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGname(Object gname) {
		return findByProperty(GNAME, gname);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByCategory(Object category) {
		return findByProperty(CATEGORY, category);
	}

	public List findByKind(Object kind) {
		return findByProperty(KIND, kind);
	}

	public List findByBrand(Object brand) {
		return findByProperty(BRAND, brand);
	}

	public List findByDetail(Object detail) {
		return findByProperty(DETAIL, detail);
	}

	public List findByGoodimglist(Object goodimglist) {
		return findByProperty(GOODIMGLIST, goodimglist);
	}

	public List findByIsuser(Object isuser) {
		return findByProperty(ISUSER, isuser);
	}

	public List findByGfullname(Object gfullname) {
		return findByProperty(GFULLNAME, gfullname);
	}

	public List findAll() {
		log.debug("finding all NsGoods instances");
		try {
			String queryString = "from NsGoods";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NsGoods merge(NsGoods detachedInstance) {
		log.debug("merging NsGoods instance");
		try {
			NsGoods result = (NsGoods) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NsGoods instance) {
		log.debug("attaching dirty NsGoods instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NsGoods instance) {
		log.debug("attaching clean NsGoods instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}