package cn.sqkj.nsyl.commentManager.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.sqkj.nsyl.commentManager.service.ICommentManagerService;

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
 * 2016年11月03日
 * 评论维护Action类
 */
public class CommentManagerAction extends PageAction {
	@Resource(name="commentManagerService")
	private ICommentManagerService commentManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(CommentManagerAction.class); //系统log日志对象
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String message;
	
	public String findComment() {
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.commentManagerService.queryCommentList(this.getPageBean());
			
			//设置页面要回显的结果集
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "查询评论列表成功！");
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			log.error("查询评论列表失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "查询评论列表失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String deleteComment() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String idstr = RequestHelper.getParameter("id");
			String[] ids = idstr.split(",");
			for(String id : ids){
				/*NsComment ns = this.commentManagerService.queryCommentById(new Long(id));
				//ns.setIsuser(1);
				db.update(ns);*/
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.message="success";
		return Action.SUCCESS;
	}
	
	public String getMessage() {
		return message;
	}
}