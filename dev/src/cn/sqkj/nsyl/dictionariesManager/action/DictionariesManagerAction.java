package cn.sqkj.nsyl.dictionariesManager.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;

import cn.sqkj.nsyl.db.po.NsDictionaries;
import cn.sqkj.nsyl.dictionariesManager.service.IDictionariesManagerService;
import cn.sqkj.nsyl.eventsManager.pojo.NsEventsinfo;
import cn.sqkj.nsyl.goodsManager.action.GoodsManagerAction_bak;
import framework.action.PageAction;
import framework.bean.PageBean;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;

public class DictionariesManagerAction extends PageAction {
	@Resource(name="baseDataManagerService")
	private IDictionariesManagerService dictionariesManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction_bak.class); //系统log日志对象
	private NsDictionaries nd;
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String message;//通知
	
	public String queryNsDictionaries(){
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.dictionariesManagerService.queryBaseDataList(this.getPageBean());
			//设置页面要回显的结果集
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "查询数字字典成功！");
			logger.info(message);
		} catch (Exception e) {
			log.error("查询数字字典失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "查询数字字典失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String loadDictionaries(){
		try {
			this.nd = this.dictionariesManagerService.queryBaseDataById(this.nd.getId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询基础数据失败！"+this.nd.getId(), e);
		}
		return Action.SUCCESS;
	}
	
	public String saveNsDictionaries() {
		try {
			this.dictionariesManagerService.saveNsDictionaries(nd);
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "修改数字字典成功！");
			logger.info(message);
		} catch (Exception e) {
			log.error("修改数字字典失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "修改数字字典失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String deleteNsDictionaries() {
		try {
			String idstr = RequestHelper.getParameter("id");
			String[] ids = idstr.split(",");
			for(String id : ids){
				NsDictionaries nd = this.dictionariesManagerService.queryBaseDataById(Long.parseLong(id));
				System.out.println(nd.getName());
				if(nd!=null){
					this.dictionariesManagerService.deleteNsDictionaries(nd);
					this.message="success";
				}
			}
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "删除数字字典成功！");
			logger.info(message);
		} catch (Exception e) {
			log.error("删除数字字典失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "删除数字字典失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}

	
	public NsDictionaries getNd() {
		return nd;
	}

	public void setNd(NsDictionaries nd) {
		this.nd = nd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
