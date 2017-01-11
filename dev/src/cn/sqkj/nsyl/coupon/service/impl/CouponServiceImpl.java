package cn.sqkj.nsyl.coupon.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.coupon.dao.CouponDAO;
import cn.sqkj.nsyl.coupon.pojo.NsCoupon;
import cn.sqkj.nsyl.coupon.service.ICouponService;
import cn.sqkj.nsyl.userManager.pojo.NsUser;
import framework.bean.PageBean;
import framework.db.DBUtil;

@SuppressWarnings({"rawtypes","unchecked"})
@Service("couponService")
public class CouponServiceImpl implements ICouponService{
	
	@Resource(name="couponDAO")
	private CouponDAO couponDAO;

	public PageBean queryCouponList(PageBean pageBean) throws Exception {
		StringBuffer sql = new StringBuffer("select t.id,t.coupon_name,t.coupon_type,t.coupon_zs_balance,t.coupon_blance,t.coupon_xf_balance," +
				"t.coupon_expiry_date,t.coupon_remark,t.coupon_status,t.create_time,t.option_adminid,t.option_time from ns_coupon t  where 1=1 ");
		List params = new ArrayList();
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("coupon_status"))) {
				sql.append(" and t.coupon_status = ? ");
				params.add(pageBean.getQueryParams().get("coupon_status"));
			}
		}
		//查询总计路数
		int total = couponDAO.findCouponCount(sql.toString(), params);
		//查询数据集
		List<NsCoupon> list = couponDAO.findCouponPage(sql.toString(), params, pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}

	public NsCoupon queryCouponById(Long id) throws Exception {
		if(id!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsCoupon coupon = (NsCoupon) db.getSession().get(NsCoupon.class, id);
			return coupon;
		}
		return null;
	}
	
	public void updateNsCoupon(NsCoupon coupon) throws Exception {
		if(coupon!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			db.update(coupon);
		}
	}

	public NsCoupon addNsCoupon(NsCoupon coupon) throws Exception {
		if(coupon!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			db.insert(coupon);
		}
		return coupon;
	}
	
}
