package cn.sqkj.nsyl.coupon.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;

import cn.sqkj.nsyl.coupon.pojo.NsCoupon;
import cn.sqkj.nsyl.coupon.service.ICouponService;
import cn.sqkj.nsyl.goodsManager.action.GoodsManagerAction_bak;
import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;

public class CouponAction  extends PageAction {
	@Resource(name="couponService")
	private ICouponService couponService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction_bak.class); //系统log日志对象
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String messages;//通知
	private NsCoupon coupon;
	
	
	public String queryNsCoupon(){
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.couponService.queryCouponList(this.getPageBean());
			//设置页面要回显的结果集
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "查询优惠券列表成功！");
			logger.info(message);
		} catch (Exception e) {
			log.error("查询优惠券列表失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "查询优惠券列表失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String queryCouponById(){
		try {
			String id = RequestHelper.getParameter("id");
			this.coupon = this.couponService.queryCouponById(Long.valueOf(id));
			messages = "success";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询优惠券失败！"+this.coupon.getId(), e);
		}
		return Action.SUCCESS;
	}
	
	public String updateNsCoupon(){
		try {
			if(this.coupon.getId()!=null){
				this.couponService.updateNsCoupon(this.coupon);
			}else{
				this.couponService.addNsCoupon(this.coupon);
			}
			messages = "success";
			TAuditLog message = new TAuditLog(user.getUId(), "修改用户信息成功！");
			logger.info(message);
		}catch (Exception e) {
			log.error("操作失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "操作失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public NsCoupon getCoupon() {
		return coupon;
	}

	public void setCoupon(NsCoupon coupon) {
		this.coupon = coupon;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
	
}
