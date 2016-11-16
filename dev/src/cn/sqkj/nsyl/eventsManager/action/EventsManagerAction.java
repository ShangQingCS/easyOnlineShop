package cn.sqkj.nsyl.eventsManager.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.sqkj.nsyl.eventsManager.pojo.NsEventsinfo;
import cn.sqkj.nsyl.eventsManager.service.IEventsManagerService;

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
 * 2016年11月10日
 * 活动管理action类
 */
public class EventsManagerAction extends PageAction {
	@Resource(name="eventsManagerService")
	private IEventsManagerService eventsManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(EventsManagerAction.class); //系统log日志对象
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private NsEventsinfo eve;
	private String message;
	private File minpicture;
    private String minpictureFileName; 
    private File picture;
    private String pictureFileName; 
    
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
	
	public String save() {
		try {
			//从配置文件读取
			String imgPathPrefix = "http://127.0.0.1:8080/dev/upload/"; //访问路径
			String imgUploadPath = ServletActionContext.getServletContext().getRealPath("/upload"); //实际存储路径
			
			File dir = new File(imgUploadPath);
			if(!dir.exists()) dir.mkdir();
			
			DBUtil db = DBUtil.getDBUtilByRequest();
			db.beginTransaction();
			
			//上传
			if(this.picture != null) {
				//上传封面
				String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.pictureFileName.substring(this.pictureFileName.lastIndexOf("."));
				InputStream is = new FileInputStream(this.picture);
				OutputStream os = new FileOutputStream(new File(imgUploadPath, newFileName));
				byte[] buffer = new byte[500];
				int length = 0;
				while(-1 != (length = is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
				this.eve.setPicture(imgPathPrefix+newFileName);
			}
			
			//上传
			if(this.minpicture != null) {
				//上传封面
				String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.minpictureFileName.substring(this.minpictureFileName.lastIndexOf("."));
				InputStream is = new FileInputStream(this.minpicture);
				OutputStream os = new FileOutputStream(new File(imgUploadPath, newFileName));
				byte[] buffer = new byte[500];
				int length = 0;
				while(-1 != (length = is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
				this.eve.setMinpicture(imgPathPrefix+newFileName);
			}
			
			if(this.eve.getId() == null) {
				db.insert(this.eve);
			} else {
				NsEventsinfo ne = this.eventsManagerService.queryEventsById(this.eve.getId());
				ne.setName(this.eve.getName()); 
				ne.setStartTime(this.eve.getStartTime()); 
				ne.setEndTime(this.eve.getEndTime()); 
				ne.setMemo(this.eve.getMemo());
				ne.setIsuse(this.eve.getIsuse());
				if(StringUtils.isNotBlank(this.eve.getMinpicture())) {
					ne.setMinpicture(this.eve.getMinpicture());
				}
				if(StringUtils.isNotBlank(this.eve.getPicture())) {
					ne.setPicture(this.eve.getPicture());
				}
				db.update(ne);
			}
			db.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String loadEvents() {
		try {
			this.eve = this.eventsManagerService.queryEventsById(this.eve.getId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询活动失败！"+this.eve.getId(), e);
		}
		return Action.SUCCESS;
	}
	
	public String isuse() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String idstr = RequestHelper.getParameter("id");
			String[] ids = idstr.split(",");
			for(String id : ids){
				NsEventsinfo ne = this.eventsManagerService.queryEventsById(new Long(id));
				ne.setIsuse("0");
				db.update(ne);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.message="success";
		return Action.SUCCESS;
	}
	
	public String notIsuse() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String idstr = RequestHelper.getParameter("id");
			String[] ids = idstr.split(",");
			for(String id : ids){
				NsEventsinfo ne = this.eventsManagerService.queryEventsById(new Long(id));
				ne.setIsuse("1");
				db.update(ne);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.message="success";
		return Action.SUCCESS;
	}
	
	public String delete() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String idstr = RequestHelper.getParameter("id");
			String[] ids = idstr.split(",");
			for(String id : ids){
				NsEventsinfo ne = this.eventsManagerService.queryEventsById(new Long(id));
				ne.setFlag(SysDict.FLAG_HIS);
				db.update(ne);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.message="success";
		return Action.SUCCESS;
	}

	public NsEventsinfo getEve() {
		return eve;
	}

	public void setEve(NsEventsinfo eve) {
		this.eve = eve;
	}

	public File getMinpicture() {
		return minpicture;
	}

	public void setMinpicture(File minpicture) {
		this.minpicture = minpicture;
	}

	public String getMinpictureFileName() {
		return minpictureFileName;
	}

	public void setMinpictureFileName(String minpictureFileName) {
		this.minpictureFileName = minpictureFileName;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getMessage() {
		return message;
	}
	
}
