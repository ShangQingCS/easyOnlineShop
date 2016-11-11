package cn.sqkj.nsyl.eventsManager.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;

import cn.sqkj.nsyl.eventsManager.service.IEventsManagerService;
import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;

/**
 * @author yangchaowen
 * 2016年11月10日
 * 活动管理action类
 */
public class EventsManagerAction extends PageAction {
	@Resource(name="eventsManagerService")
	private IEventsManagerService eventsManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(EventsManagerAction.class); //系统log日志对象
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	
	public String queryEvents() {
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.eventsManagerService.queryEventsList(this.getPageBean());
			
			//设置页面要回显的结果集
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "查询活动列表成功！");
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			log.error("查询活动列表失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "查询活动列表失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
}
