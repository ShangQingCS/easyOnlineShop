package cn.sqkj.nsyl.orderManager.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.sqkj.nsyl.orderManager.pojo.NsOrder;
import cn.sqkj.nsyl.orderManager.service.IOrderManagerService;

import com.opensymphony.xwork2.Action;

import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;

/**
 * @author yangchaowen
 * 2016年11月7日
 * 订单管理Action类
 */
public class OrderManagerAction extends PageAction {
	@Resource(name="orderManagerService")
	private IOrderManagerService orderManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(OrderManagerAction.class); //系统log日志对象
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String message;
	private NsOrder order;
	private List orderDetail;
	
	public String findOrder() {
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.orderManagerService.queryOrderList(this.getPageBean());
			
			//设置页面要回显的结果集
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "查询订单列表成功！");
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			log.error("查询订单列表失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "查询订单列表失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String loadOrder() {
		try {
			this.order = this.orderManagerService.queryOrderById(this.order.getId());
			this.orderDetail = this.orderManagerService.queryOrderDetailByOrderId(this.order.getId());
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "查询订单详细成功："+this.order.getId());
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			log.error("查询订单详细失败！"+this.order.getId(), e);
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "查询订单详细失败："+this.order.getId());
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String editOrderNum() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsOrder o = this.orderManagerService.queryOrderById(this.order.getId());
			o.setDeliveryNumb(this.order.getDeliveryNumb());
			db.update(o);
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "编辑订单快递单号成功："+this.order.getId());
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "编辑订单快递单号失败："+this.order.getId());
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String changeStatus() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsOrder o = this.orderManagerService.queryOrderById(this.order.getId());
			o.setOrderstatus(new Long("3")); //已发货
			db.update(o);
			this.message="success";
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "修改订单已发货成功:"+this.order.getId());
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			this.message="操作失败";
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "修改订单已发货失败:"+this.order.getId());
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String cancelStatus() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsOrder o = this.orderManagerService.queryOrderById(this.order.getId());
			o.setOrderstatus(new Long("2")); //已付款
			db.update(o);
			this.message="success";
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "取消订单已发货成功:"+this.order.getId());
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			this.message="操作失败";
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "取消订单已发货失败:"+this.order.getId());
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String getMessage() {
		return message;
	}

	public NsOrder getOrder() {
		return order;
	}

	public void setOrder(NsOrder order) {
		this.order = order;
	}

	public List getOrderDetail() {
		return orderDetail;
	}
	
}