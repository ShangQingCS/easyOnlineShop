package cn.sqkj.nsyl.userManager.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;

import cn.sqkj.nsyl.goodsManager.action.GoodsManagerAction_bak;
import cn.sqkj.nsyl.userManager.pojo.NsUserGrade;
import cn.sqkj.nsyl.userManager.service.IUserGradeService;
import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;

public class UserGradeAction   extends PageAction {
	
	@Resource(name="userGradeService")
	private IUserGradeService userGradeService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction_bak.class); //系统log日志对象
	private NsUserGrade nsUserGrade;
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String messages;//通知
	
	
	public String queryUserGrade(){
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.userGradeService.queryUserGradeList(this.getPageBean());
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
	
	public String queryUserGradeById(){
		try {
			String id = RequestHelper.getParameter("id");
			this.nsUserGrade = this.userGradeService.queryUserGradeById(Long.valueOf(id));
			messages = "success";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询用户失败！"+this.nsUserGrade.getId(), e);
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 修改用户信息
	 * @return
	 */
	public String updateUserGrade(){
		try {
			int result = this.userGradeService.updateUserGrade(this.nsUserGrade);
			if(result>0){
				messages = "success";
			}
			TAuditLog message = new TAuditLog(user.getUId(), "修改用户等级成功！");
			logger.info(message);
		}catch (Exception e) {
			log.error("操作失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "操作失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 修改用户信息
	 * @return
	 */
	public String updateUserGrade2(){
		try {
			int result = this.userGradeService.updateUserGrade2(this.nsUserGrade);
			if(result>0){
				messages = "success";
			}
			TAuditLog message = new TAuditLog(user.getUId(), "修改分销 分成成功！");
			logger.info(message);
		}catch (Exception e) {
			log.error("操作失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "操作失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}

	public NsUserGrade getNsUserGrade() {
		return nsUserGrade;
	}

	public void setNsUserGrade(NsUserGrade nsUserGrade) {
		this.nsUserGrade = nsUserGrade;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	
	
}
