package cn.sqkj.nsyl.userManager.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.sqkj.nsyl.goodsManager.action.GoodsManagerAction_bak;
import cn.sqkj.nsyl.userManager.pojo.NsUser;
import cn.sqkj.nsyl.userManager.pojo.NsUserPurse;
import cn.sqkj.nsyl.userManager.service.IUserManagerService;
import cn.sqkj.nsyl.userManager.service.IUserPurseService;

import com.opensymphony.xwork2.Action;

import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;

public class UserManagerAction  extends PageAction {
	
	@Resource(name="userManagerService")
	private IUserManagerService userManagerService;
	@Resource(name="userPurseService")
	private IUserPurseService userPurseService;
	
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction_bak.class); //系统log日志对象
	private NsUser nsUser;
	private NsUserPurse nsUp;
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String messages;//通知
	
	public String queryNsUser(){
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.userManagerService.queryUserList(this.getPageBean());
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
	
	public String queryNsUserTeam(){
		try {
			//传入分页信息查询数据库
			String id = RequestHelper.getParameter("id");
			if(id!=null&&id!=""){
				PageBean resultData = this.userManagerService.queryNsUserTeamList(this.getPageBean(),id);
				this.setTotal(resultData.getTotal());
				this.setDataRows(resultData.getPageData());
				//打印审计日志
				TAuditLog message = new TAuditLog(user.getUId(), "查询用户列表成功！");
				logger.info(message);
			}
		} catch (Exception e) {
			log.error("查询用户列表失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "查询用户列表失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String queryNsUserByCondition(){
		try {
			//传入分页信息查询数据库
			String user_name = RequestHelper.getParameter("queryParams.user_name");
			String true_name = RequestHelper.getParameter("queryParams.true_name");
			String user_phone = RequestHelper.getParameter("queryParams.user_phone");
			String identity_card = RequestHelper.getParameter("queryParams.identity_card");
			System.out.println(user_name);
			this.nsUser = this.userManagerService.queryUserListByCondition(user_name,true_name,user_phone,identity_card);
			//设置页面要回显的结果集
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
	
	public String queryNsUserCount(){
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.userManagerService.queryNsUserCount(this.getPageBean());
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
	
	public String queryUserById(){
		try {
			String id = RequestHelper.getParameter("id");
			this.nsUser = this.userManagerService.queryUserById(Long.valueOf(id));
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
			int result = this.userManagerService.updateNsUser(this.nsUser);
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
	
	
	public String accredUserName(){
		try {
			String id = RequestHelper.getParameter("id");
			String tag = RequestHelper.getParameter("tag");
			String flag = RequestHelper.getParameter("flag");
			this.nsUser = this.userManagerService.queryUserById(Long.valueOf(id));
			if(nsUser!=null){
				if(tag!=null){
					if(tag.equals("1")){//审核通过
						this.nsUser.setIdentity_status("2");
					}else if(tag.equals("0")){//审核不通过
						this.nsUser.setIdentity_status("0");
					}
				}
				if(flag!=null){
					if(flag.equals("1")){//审核通过
						//ns_user_purse	更改	trade_state为2	option_remark字段默认输出提现成功
						this.nsUp = this.userPurseService.queryUserPurseById(Long.valueOf(id));
						if(nsUp!=null){
							nsUp.setOption_remark("提现成功");
							nsUp.setTrade_state(2);
							this.userPurseService.updateUserPurse(nsUp);
						}
						
					}else if(flag.equals("0")){//审核不通过
						this.nsUser.setUser_fx_balance(nsUser.getUser_fx_balance()+nsUser.getUser_dj_balance());
						this.nsUp = this.userPurseService.queryUserPurseById(Long.valueOf(id));
						if(nsUp!=null){
							nsUp.setOption_remark("提现失败");
							nsUp.setTrade_state(0);
							this.userPurseService.updateUserPurse(nsUp);
						}
						//ns_user_purse	更改	trade_state为0	option_remark字段默认输出提现失败
					}
					this.nsUser.setUser_dj_balance(0d);
					this.nsUser.setTixian_status("0");
				}
				int result = this.userManagerService.updateNsUser(this.nsUser);
				if(result>0){
					messages = "success";
				}
				TAuditLog message = new TAuditLog(user.getUId(), "修改用户信息成功！");
				logger.info(message);
			}
			
		} catch (Exception e) {
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

	public NsUserPurse getNsUp() {
		return nsUp;
	}

	public void setNsUp(NsUserPurse nsUp) {
		this.nsUp = nsUp;
	}
	
	
	
}
