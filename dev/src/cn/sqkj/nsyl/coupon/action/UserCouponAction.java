package cn.sqkj.nsyl.coupon.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;

import cn.sqkj.nsyl.coupon.pojo.NsUserCoupon;
import cn.sqkj.nsyl.coupon.service.IUserCouponService;
import cn.sqkj.nsyl.goodsManager.action.GoodsManagerAction_bak;
import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;

public class UserCouponAction  extends PageAction {
	@Resource(name="userCouponService")
	private IUserCouponService userCouponService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction_bak.class); //系统log日志对象
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String messages;//通知
	private NsUserCoupon nsUserCoupon;
	
	public String queryNsUserCoupon(){
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.userCouponService.queryUserCouponList(this.getPageBean());
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
	
	public String updateNsUserCoupon(){
		try {
			String id = RequestHelper.getParameter("id");
			String couponStatus = RequestHelper.getParameter("couponStatus");
			this.nsUserCoupon = this.userCouponService.queryUserCouponById(Long.valueOf(id));
			this.nsUserCoupon.setCoupon_status(couponStatus);//
			this.userCouponService.updateNsUserCoupon(this.nsUserCoupon);
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "修改优惠券成功！");
			logger.info(message);
		} catch (Exception e) {
			log.error("修改优惠券列表失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "修改优惠券列表失败！");
			logger.info(message);
		}
		
		return Action.SUCCESS;
	}
	

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public NsUserCoupon getNsUserCoupon() {
		return nsUserCoupon;
	}

	public void setNsUserCoupon(NsUserCoupon nsUserCoupon) {
		this.nsUserCoupon = nsUserCoupon;
	}
	
	
	
}
