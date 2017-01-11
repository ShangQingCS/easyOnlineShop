package cn.sqkj.nsyl.coupon.service;

import cn.sqkj.nsyl.coupon.pojo.NsCoupon;
import framework.bean.PageBean;

public interface ICouponService {
	/**
	 * 查询优惠券列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryCouponList(PageBean pageBean) throws Exception;
	
	/**
	 * 根据ID查询优惠券
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NsCoupon queryCouponById(Long id) throws Exception;
	
	
	/**
	 * 修改优惠券
	 * @param coupon
	 * @return
	 * @throws Exception
	 */
	public void updateNsCoupon(NsCoupon coupon) throws Exception;
	
	
	/**
	 * 新增coupon
	 * @param coupon
	 * @return
	 * @throws Exception
	 */
	public NsCoupon addNsCoupon(NsCoupon coupon) throws Exception;
	
}
