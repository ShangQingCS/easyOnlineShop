package cn.sqkj.nsyl.userManager.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;

import cn.sqkj.nsyl.goodsManager.action.GoodsManagerAction_bak;
import cn.sqkj.nsyl.userManager.pojo.NsUser;
import cn.sqkj.nsyl.userManager.service.IUserManagerService;
import framework.action.PageAction;
import framework.bean.PageBean;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;

public class UserManagerAction  extends PageAction {
	
	@Resource(name="userManagerService")
	private IUserManagerService userManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction_bak.class); //系统log日志对象
	private NsUser nsUser;
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String messages;//通知
	
	public String queryNsUser(){
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.userManagerService.queryUserList(this.getPageBean());
			//设置页面要回显的结果集
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "查询用户列表成功！");
			logger.info(message);
		} catch (Exception e) {
			log.error("查询用户列表失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "查询用户列表失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 修改用户的status或者flag
	 * @return
	 */
	public String updateNsUserFlagorStatus(){
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String id = RequestHelper.getParameter("id");
			String tag = RequestHelper.getParameter("tag");
			String reason = RequestHelper.getParameter("reason");
			NsUser nu = this.userManagerService.queryUserById(Long.parseLong(id));
			//打印审计日志
			if(tag.equals("1")){
				System.out.println("设为冻结");
				nu.setStatus(SysDict.STATUS_YES);
				db.update(nu);
				TAuditLog message = new TAuditLog(user.getUId(), "设为冻结成功！");
				TAuditLog message2 = new TAuditLog(user.getUId(), "冻结原由:"+reason);
				logger.info(message);
				logger.info(message2);
			}else if(tag.equals("2")){
				System.out.println("设为非冻结");
				nu.setStatus(SysDict.STATUS_NO);
				db.update(nu);
				TAuditLog message = new TAuditLog(user.getUId(), "设为非冻结成功！");
				logger.info(message);
			}else if(tag.equals("3")){
				System.out.println("设为注销");
				nu.setFlag(SysDict.FLAG_YES);
				db.update(nu);
				TAuditLog message = new TAuditLog(user.getUId(), "设为注销成功！");
				TAuditLog message2 = new TAuditLog(user.getUId(), "注销原由:"+reason);
				logger.info(message);
				logger.info(message2);
			}else if(tag.equals("4")){
				System.out.println("设为非注销");
				nu.setFlag(SysDict.FLAG_NO);
				db.update(nu);
				TAuditLog message = new TAuditLog(user.getUId(), "设为非注销成功！");
				logger.info(message);
			}
			messages = "success";
		}catch (Exception e) {
			log.error("操作失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "操作失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}

	

	public NsUser getNsUser() {
		return nsUser;
	}

	public void setNsUser(NsUser nsUser) {
		this.nsUser = nsUser;
	}

	public String getMessage() {
		return messages;
	}

	public void setMessage(String messages) {
		this.messages = messages;
	}
	
	
	
	
}
