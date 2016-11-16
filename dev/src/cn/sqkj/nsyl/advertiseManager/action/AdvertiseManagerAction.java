package cn.sqkj.nsyl.advertiseManager.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.sqkj.nsyl.advertiseManager.pojo.NsAdvertise;
import cn.sqkj.nsyl.advertiseManager.service.IAdvertiseManagerService;
import cn.sqkj.nsyl.db.po.NsDictionaries;
import cn.sqkj.nsyl.goodsManager.pojo.NsGoods;
import cn.sqkj.nsyl.goodsManager.pojo.NsGoodsCategory;
import cn.sqkj.nsyl.goodsManager.service.IGoodsManagerService;
import cn.sqkj.nsyl.service.IDictService;

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
 * 广告管理action类
 */
public class AdvertiseManagerAction extends PageAction {
	@Resource(name="advertiseManagerService")
	private IAdvertiseManagerService advertiseManagerService;
	@Resource(name="goodsManagerService")
	private IGoodsManagerService goodsManagerService;
	@Resource(name="dictService")
	private IDictService dictService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(AdvertiseManagerAction.class); //系统log日志对象
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private NsAdvertise adv;
	private List<NsDictionaries> banner;
	private String message;
	private String objectName;
	//封面
	private File imgurl;
    private String imgurlFileName; 
	    
	public String queryAdv() {
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.advertiseManagerService.queryAdvList(this.getPageBean());
			
			//设置页面要回显的结果集
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "查询广告列表成功！");
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			log.error("查询广告列表失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "查询广告列表失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String queryBanner() {
		try {
			this.banner = this.dictService.queryDictByType("ADV_BANNER");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String save() {
		try {
			//从配置文件读取
			String imgPathPrefix = "http://127.0.0.1:8080/dev/upload/"; 
			String imgUploadPath = ServletActionContext.getServletContext().getRealPath("/upload");
			
			File dir = new File(imgUploadPath);
			if(!dir.exists()) dir.mkdir();
			
			DBUtil db = DBUtil.getDBUtilByRequest();
			db.beginTransaction();
			
			//上传
			if(this.imgurl != null) {
				//上传封面
				String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.imgurlFileName.substring(this.imgurlFileName.lastIndexOf("."));
				InputStream is = new FileInputStream(this.imgurl);
				OutputStream os = new FileOutputStream(new File(imgUploadPath, newFileName));
				byte[] buffer = new byte[500];
				int length = 0;
				while(-1 != (length = is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
				this.adv.setImgurl(imgPathPrefix+newFileName);
			}
			
			if(this.adv.getId() == null) {
				db.insert(this.adv);
			} else {
				NsAdvertise na = this.advertiseManagerService.queryAdvById(this.adv.getId());
				na.setName(this.adv.getName()); 
				na.setLinkkind(this.adv.getLinkkind());
				na.setImglink(this.adv.getImglink());
				na.setOrdernumb(this.adv.getOrdernumb());
				na.setType(this.adv.getType());
				na.setKind(this.adv.getKind());
				na.setMemo(this.adv.getMemo());
				na.setIsuse(this.adv.getIsuse());
				if(StringUtils.isNotBlank(this.adv.getImgurl())) {
					na.setImgurl(this.adv.getImgurl());
				}
				db.update(na);
			}
			db.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String loadAdv() {
		try {
			this.adv = this.advertiseManagerService.queryAdvById(this.adv.getId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询广告失败！"+this.adv.getId(), e);
		}
		return Action.SUCCESS;
	}
	
	public String loadObj() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String objType = RequestHelper.getParameter("objType");
			String objId = RequestHelper.getParameter("objId");
			if("0".equals(objType)) {
				NsGoods ng = goodsManagerService.queryGoodsById(new Long(objId));
				this.objectName = ng.getGname();
			} else if("1".equals(objType)) {
				
			} else if("2".equals(objType)) {
				
			} else if("3".equals(objType)) {
				NsGoodsCategory ngc = (NsGoodsCategory) db.getSession().get(NsGoodsCategory.class, new Long(objId));
				this.objectName = ngc.getCateName();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询广告品失败！"+this.adv.getId(), e);
		}
		return Action.SUCCESS;
	}

	public String isuse() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String idstr = RequestHelper.getParameter("id");
			String[] ids = idstr.split(",");
			for(String id : ids){
				NsAdvertise na = this.advertiseManagerService.queryAdvById(new Long(id));
				na.setIsuse("0");
				db.update(na);
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
				NsAdvertise na = this.advertiseManagerService.queryAdvById(new Long(id));
				na.setIsuse("1");
				db.update(na);
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
				NsAdvertise na = this.advertiseManagerService.queryAdvById(new Long(id));
				na.setFlag(SysDict.FLAG_HIS);
				db.update(na);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.message="success";
		return Action.SUCCESS;
	}
	
	public NsAdvertise getAdv() {
		return adv;
	}

	public void setAdv(NsAdvertise adv) {
		this.adv = adv;
	}

	public List<NsDictionaries> getBanner() {
		return banner;
	}

	public File getImgurl() {
		return imgurl;
	}

	public void setImgurl(File imgurl) {
		this.imgurl = imgurl;
	}

	public String getImgurlFileName() {
		return imgurlFileName;
	}

	public void setImgurlFileName(String imgurlFileName) {
		this.imgurlFileName = imgurlFileName;
	}

	public String getMessage() {
		return message;
	}

	public String getObjectName() {
		return objectName;
	}
	
}
