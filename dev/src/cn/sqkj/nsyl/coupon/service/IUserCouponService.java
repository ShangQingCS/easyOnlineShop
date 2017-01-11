package cn.sqkj.nsyl.coupon.service;

import cn.sqkj.nsyl.coupon.pojo.NsUserCoupon;
import framework.bean.PageBean;

public interface IUserCouponService {
	/**
	 * 查询用户列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryUserCouponList(PageBean pageBean) throws Exception;
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NsUserCoupon queryUserCouponById(Long id) throws Exception;
	
	/**
	 * 修改优惠券
	 * @param coupon
	 * @return
	 * @throws Exception
	 */
	public void updateNsUserCoupon(NsUserCoupon nsUserCoupon) throws Exception;
}
