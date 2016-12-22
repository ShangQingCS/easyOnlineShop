package cn.sqkj.nsyl.eventsManager.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.sqkj.nsyl.eventsManager.pojo.NsEventsGoodsVO;
import cn.sqkj.nsyl.eventsManager.pojo.NsEventsGoods;
import cn.sqkj.nsyl.eventsManager.pojo.NsEventsinfo;
import cn.sqkj.nsyl.eventsManager.service.IEventsManagerService;

import com.opensymphony.xwork2.Action;

import framework.action.PageAction;
import framework.bean.PageBean;
import framework.config.Config;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;
import framework.util.DateUtils;

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
	private String events_goods_list;
	private String message;
	private File minpicture;
    private String minpictureFileName; 
    private File picture;
    private String pictureFileName; 
    private List evegslist;
    
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
			/*String imgPathPrefix = "http://127.0.0.1:8080/dev/upload/"; //访问路径
			String imgUploadPath = ServletActionContext.getServletContext().getRealPath("/upload"); //实际存储路径*/
			
			String imgUploadPath = Config.get("img.upload.basepath");
			String currDate = DateUtils.toDate("yyyyMMdd",DateUtils.getDate());
			
			File dir = new File(imgUploadPath);
			System.out.println(dir.exists());
			if(!dir.exists()) {
				dir.mkdir();
			}
			dir = new File(dir.getAbsolutePath()+"/"+currDate);
			if(!dir.exists()) {
				dir.mkdir();
			} 
			
			DBUtil db = DBUtil.getDBUtilByRequest();
			db.beginTransaction();
			
			//上传
			if(this.picture != null) {
				//上传封面
				String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.pictureFileName.substring(this.pictureFileName.lastIndexOf("."));
				InputStream is = new FileInputStream(this.picture);
				OutputStream os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
				byte[] buffer = new byte[500];
				int length = 0;
				while(-1 != (length = is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
				this.eve.setPicture(currDate+"/"+newFileName);
			}
			
			//上传
			if(this.minpicture != null) {
				//上传封面
				String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.minpictureFileName.substring(this.minpictureFileName.lastIndexOf("."));
				InputStream is = new FileInputStream(this.minpicture);
				OutputStream os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
				byte[] buffer = new byte[500];
				int length = 0;
				while(-1 != (length = is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
				this.eve.setMinpicture(currDate+"/"+newFileName);
			}
			
			List<String> listgoodsid=new ArrayList<String>();
			
			if(StringUtils.isNotBlank(this.events_goods_list)){
				String [] goodslist=this.events_goods_list.split(",");
				if(goodslist != null && goodslist.length>0){
					for(String goodsid:goodslist){
						if(StringUtils.isNotBlank(goodsid)){
							listgoodsid.add(goodsid);
						}
					}
				}
			}
			try{
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
				List dbgoodslist =this.eventsManagerService.queryEventsGoodsByEventsId(this.eve.getId());
				List<NsEventsGoods> deletelist=new ArrayList<NsEventsGoods>();
				List<NsEventsGoods> insertlist=new ArrayList<NsEventsGoods>();
				if(listgoodsid.size()>0){
					int j=-1;
					if(dbgoodslist!=null && dbgoodslist.size()>0){
						for(int i=0;i<dbgoodslist.size();i++){
							Map nsegoods=(HashMap)dbgoodslist.get(i);
							j=0;
							for(String goodsid:listgoodsid){
								if(goodsid.equals(nsegoods.get("goodsId")+"")){
									j=1;
								}
							}
							if(j<=0){
								NsEventsGoods eventsgoods=new NsEventsGoods();
								eventsgoods.setId(Long.parseLong(nsegoods.get("id")+""));
								eventsgoods.setEventsId(Long.parseLong(nsegoods.get("eventsId")+""));
								eventsgoods.setGoodsId(Long.parseLong(nsegoods.get("goodsId")+""));
								deletelist.add(eventsgoods);
							}
						}						
					}
					
					for(String goodsid:listgoodsid){
						NsEventsGoods eventsgoods=new NsEventsGoods();
						if(dbgoodslist!=null && dbgoodslist.size()>0){
							for(int i=0;i<dbgoodslist.size();i++){
								Map nsegoods=(HashMap)dbgoodslist.get(i);
								j=0;
								if(goodsid.equals(nsegoods.get("goodsId")+"")){
									j=1;
								}
							}
							if(j<=0){
								NsEventsGoods neg=new NsEventsGoods();
								neg.setEventsId(this.eve.getId());
								neg.setGoodsId(Long.parseLong(goodsid));
								insertlist.add(neg);
							}
						}else{
							NsEventsGoods neg=new NsEventsGoods();
							neg.setEventsId(this.eve.getId());
							neg.setGoodsId(Long.parseLong(goodsid));
							insertlist.add(neg);
						}
					}
				}else{
					if(dbgoodslist!=null && dbgoodslist.size()>0){
						for(int i=0;i<dbgoodslist.size();i++){
							Map nsegoods=(HashMap)dbgoodslist.get(i);
							NsEventsGoods eventsgoods=new NsEventsGoods();
							eventsgoods.setId(Long.parseLong(nsegoods.get("id")+""));
							eventsgoods.setEventsId(Long.parseLong(nsegoods.get("eventsId")+""));
							eventsgoods.setGoodsId(Long.parseLong(nsegoods.get("goodsId")+""));
							deletelist.add(eventsgoods);
						}
					}
				}
				if(insertlist.size()>0){
					db.insert(insertlist);
				}
				if(deletelist.size()>0){						
					db.delete(deletelist);
				}
				db.commit();
			}catch(Exception e){
				db.rollback();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String loadEvents() {
		try {
			this.eve = this.eventsManagerService.queryEventsById(this.eve.getId());
			this.evegslist =this.eventsManagerService.queryEGVOByEventsId(this.eve.getId());
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
				ne.setIsuse(SysDict.ISUSE_YES);
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
				ne.setIsuse(SysDict.ISUSE_NO);
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

	public String getEvents_goods_list() {
		return events_goods_list;
	}

	public void setEvents_goods_list(String events_goods_list) {
		this.events_goods_list = events_goods_list;
	}

	public List<NsEventsGoods> getEvegslist() {
		return evegslist;
	}

	public void setEvegslist(List<NsEventsGoods> evegslist) {
		this.evegslist = evegslist;
	}
	
}
