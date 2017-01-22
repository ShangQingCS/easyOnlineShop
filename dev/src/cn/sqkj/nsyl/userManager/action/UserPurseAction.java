package cn.sqkj.nsyl.userManager.action;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;

import cn.sqkj.nsyl.goodsManager.action.GoodsManagerAction_bak;
import cn.sqkj.nsyl.userManager.pojo.NsUser;
import cn.sqkj.nsyl.userManager.pojo.NsUserPurse;
import cn.sqkj.nsyl.userManager.service.IUserManagerService;
import cn.sqkj.nsyl.userManager.service.IUserPurseService;
import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;
import framework.util.DateUtils;

public class UserPurseAction extends PageAction {
	
	@Resource(name="userManagerService")
	private IUserManagerService userManagerService;
	@Resource(name="userPurseService")
	private IUserPurseService userPurseService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction_bak.class); //系统log日志对象
	private NsUserPurse nsUserPurse;
	private NsUser nsUser;
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String messages;//通知
	
	
	public String queryUserPurseJF(){
		try {
			//传入分页信息查询数据库
			String purse_type = "2";
			PageBean resultData = this.userPurseService.queryUserPurseList(this.getPageBean(),purse_type);
			//System.out.println(resultData.getPageData().get(0));
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
	
	public String queryUserPurseKY(){
		try {
			//传入分页信息查询数据库
			String purse_type = "0";
			PageBean resultData = this.userPurseService.queryUserPurseList(this.getPageBean(),purse_type);
			//System.out.println(resultData.getPageData().get(0));
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
	
	public String queryUserPurseFX(){
		try {
			//传入分页信息查询数据库
			String purse_type = "1";
			PageBean resultData = this.userPurseService.queryUserPurseList(this.getPageBean(),purse_type);
			//System.out.println(resultData.getPageData().get(0));
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
	
	
	public String addUserPurse(){
		try {
			int result = this.userManagerService.updateNsUser(this.nsUser);//更新用户积分
			DBUtil db = DBUtil.getDBUtilByRequest();
			String radiojf = RequestHelper.getParameter("radiojf");
			String jf_balance = RequestHelper.getParameter("_balance");
			String purse_type = RequestHelper.getParameter("purse_type");
			db.beginTransaction();
			NsUserPurse nsUserPurse = new NsUserPurse();
			nsUserPurse.setTrade_type(0);
			nsUserPurse.setTrade_amount(Double.valueOf(jf_balance));//输入值
			UUID uuid = UUID.randomUUID();
			String tradeSn = "S"+uuid.toString().replaceAll("-", ""); 
			nsUserPurse.setTrade_sn(tradeSn);
			nsUserPurse.setTrade_state(2);//交易成功
			nsUserPurse.setOption_type(Integer.valueOf(radiojf));
			nsUserPurse.setUser_id(this.nsUser.getId());
			nsUserPurse.setUser_amount(this.nsUser.getUser_jf_balance());//-----------计算后的值
			nsUserPurse.setPurse_type(Integer.valueOf(purse_type));//积分
			nsUserPurse.setPay_account("");
			nsUserPurse.setPay_open_bank("");
			nsUserPurse.setPurse_status(1);
			nsUserPurse.setCreate_time(DateUtils.getDate());
			nsUserPurse.setOption_time(DateUtils.getDate());
			nsUserPurse.setOption_adminid(0l);
			nsUserPurse.setOption_adminname("");
			nsUserPurse.setOption_remark("交易成功");
			db.insert(nsUserPurse);
			if(result>0){
				messages = "success";
			}
			TAuditLog message = new TAuditLog(user.getUId(), "修改用户信息成功！");
			logger.info(message);
		}catch (Exception e) {
			log.error("操作失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "操作失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String queryUserPurseListCount(){
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.userPurseService.queryUserPurseListCount(this.getPageBean());
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
	
	
	public NsUser getNsUser() {
		return nsUser;
	}

	public void setNsUser(NsUser nsUser) {
		this.nsUser = nsUser;
	}
	
	public NsUserPurse getNsUserPurse() {
		return nsUserPurse;
	}
	public void setNsUserPurse(NsUserPurse nsUserPurse) {
		this.nsUserPurse = nsUserPurse;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	
	
	
}
