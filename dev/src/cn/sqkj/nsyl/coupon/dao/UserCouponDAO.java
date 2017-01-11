package cn.sqkj.nsyl.coupon.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sqkj.nsyl.coupon.pojo.NsUserCoupon;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("userCouponDAO")
public class UserCouponDAO extends PageDAO{
	/**
	 * 获取用户总记录数
	 * @param sql
	 * @param params
	 * @return
	 */
	public int findUserCouponCount(String sql, List params){
		return DBUtil.getDBUtilByRequest().queryCountBySQL(sql,params);
	}
	
	/**
	 * 获取用户数据集
	 * @param sql
	 * @param params
	 * @param pageBean
	 * @return
	 */
	public List<NsUserCoupon> findUserCouponPage(String sql,List params, PageBean pageBean) {
		List<NsUserCoupon> pageData = this.query(sql, params, pageBean);
		return pageData;
	}
}
