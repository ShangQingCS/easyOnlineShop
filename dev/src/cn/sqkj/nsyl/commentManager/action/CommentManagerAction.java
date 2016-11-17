package cn.sqkj.nsyl.commentManager.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.sqkj.nsyl.commentManager.pojo.NsComment;
import cn.sqkj.nsyl.commentManager.service.ICommentManagerService;

import com.opensymphony.xwork2.Action;

import framework.action.PageAction;
import framework.bean.PageBean;
import framework.config.SysDict;
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
	private NsComment comment;
	
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
				NsComment nc = this.commentManagerService.queryCommentById(new Long(id));
				nc.setFlag(SysDict.FLAG_HIS);
				db.update(nc);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.message="success";
		return Action.SUCCESS;
	}
	
	public String loadComment() {
		try {
			this.comment = this.commentManagerService.queryCommentById(this.comment.getId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询评论失败！"+this.comment.getId(), e);
		}
		return Action.SUCCESS;
	}
	
	public NsComment getComment() {
		return comment;
	}

	public void setComment(NsComment comment) {
		this.comment = comment;
	}

	public String getMessage() {
		return message;
	}
}