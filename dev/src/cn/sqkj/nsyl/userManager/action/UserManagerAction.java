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
			System.out.println(resultData.getPageData().get(0));
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
	
	public String queryUserById(){
		try {
			String id = RequestHelper.getParameter("id");
			this.nsUser = this.userManagerService.queryUserById(Long.valueOf(id));
			System.out.println(nsUser.getTrue_name());
			messages = "success";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询用户失败！"+this.nsUser.getId(), e);
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 修改用户信息
	 * @return
	 */
	public String updateNsUserFlagorStatus(){
		try {
			NsUser nu = this.userManagerService.updateNsUser(this.nsUser);
			if(nu!=null){
				messages = "success";
			}
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
