package cn.sqkj.nsyl.coupon.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.coupon.dao.UserCouponDAO;
import cn.sqkj.nsyl.coupon.pojo.NsCoupon;
import cn.sqkj.nsyl.coupon.pojo.NsUserCoupon;
import cn.sqkj.nsyl.coupon.service.IUserCouponService;
import framework.bean.PageBean;
import framework.db.DBUtil;


@SuppressWarnings({"rawtypes","unchecked"})
@Service("userCouponService")
public class UserCouponServiceImpl implements IUserCouponService{
	
	@Resource(name="userCouponDAO")
	private UserCouponDAO userCouponDAO;
	
	public PageBean queryUserCouponList(PageBean pageBean) throws Exception {
		StringBuffer sql = new StringBuffer("select t.id,t.user_id,t.user_name,t.coupon_id,t.coupon_name,t.coupon_blance," +
				"t.coupon_xf_balance,t.coupon_creatdate,t.coupon_expirydate,t.order_sn,t.coupon_status,t.option_time," +
				"t.option_adminid,t.option_remark from ns_user_coupon t where 1=1 ");
		List params = new ArrayList();
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("user_name"))) {
				sql.append(" and t.user_name like ? ");
				params.add("%"+pageBean.getQueryParams().get("user_name")+"%");
			}
		}
		//查询总计路数
		int total = userCouponDAO.findUserCouponCount(sql.toString(), params);
		//查询数据集
		List<NsUserCoupon> list = userCouponDAO.findUserCouponPage(sql.toString(), params, pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}

	public NsUserCoupon queryUserCouponById(Long id) throws Exception {
		if(id!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsUserCoupon nsUserCoupon = (NsUserCoupon) db.getSession().get(NsUserCoupon.class, id);
			return nsUserCoupon;
		}
		return null;
	}
	
	public void updateNsUserCoupon(NsUserCoupon nsUserCoupon) throws Exception {
		if(nsUserCoupon!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			db.update(nsUserCoupon);
		}
	}
}
