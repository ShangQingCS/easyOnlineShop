package cn.sqkj.nsyl.goodsManager.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.sqkj.nsyl.advertiseManager.pojo.NsAdvertise;
import cn.sqkj.nsyl.advertiseManager.service.IAdvertiseManagerService;
import cn.sqkj.nsyl.eventsManager.pojo.NsEventsinfo;
import cn.sqkj.nsyl.eventsManager.service.IEventsManagerService;
import cn.sqkj.nsyl.goodsManager.pojo.NsGoods;
import cn.sqkj.nsyl.goodsManager.service.IGoodsManagerService;

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
 * 2016年10月18日
 * 商品维护Action类
 */
public class GoodsManagerAction extends PageAction {
	@Resource(name="goodsManagerService")
	private IGoodsManagerService goodsManagerService;
	@Resource(name="advertiseManagerService")
	private IAdvertiseManagerService advertiseManagerService;
	@Resource(name="eventsManagerService")
	private IEventsManagerService eventsManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction.class); //系统log日志对象
	private NsGoods goods;
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String message;
	//封面
	private File goodimg;
    private String goodimgFileName;
    /*private String goodimgContentType;*/
    //图片1
    private File img1;
    private String img1FileName;
    //图片2
    private File img2;
    private String img2FileName;
    //图片3
    private File img3;
    private String img3FileName;
    //图片4
    private File img4;
    private String img4FileName;
    //图片5
    private File img5;
    private String img5FileName;
    private String removeImgs;
    
	public String queryGoods() {
		try {
			//传入分页信息查询数据库
			PageBean resultData = this.goodsManagerService.queryGoodsList(this.getPageBean());
			
			//设置页面要回显的结果集
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "查询商品列表成功！");
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			log.error("查询商品列表失败！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "查询商品列表失败！");
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String loadGoods() {
		try {
			this.goods = this.goodsManagerService.queryGoodsById(this.goods.getId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询商品失败！"+this.goods.getId(), e);
		}
		return Action.SUCCESS;
	}
	
	public String saveGoods() {
		//从配置文件读取
		/*String imgPathPrefix = "http://127.0.0.1:8080/dev/upload/"; 
		String imgUploadPath = ServletActionContext.getServletContext().getRealPath("/upload");*/
		/*String imgPathPrefix = Config.get("img.server.basepath");*/
		String imgUploadPath = Config.get("img.upload.basepath");
		String currDate = DateUtils.toDate("yyyyMMdd",DateUtils.getDate());
		
		try {
			File dir = new File(imgUploadPath);
			if(!dir.exists()) {
				dir.mkdir();
			}
			dir = new File(dir.getAbsolutePath()+"/"+currDate);
			if(!dir.exists()) {
				dir.mkdir();
			} 
			
	        //封面
			if(this.goodimg != null) {
		        //上传封面
		        String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.goodimgFileName.substring(this.goodimgFileName.lastIndexOf("."));
		        InputStream is = new FileInputStream(this.goodimg);
	            OutputStream os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
	            byte[] buffer = new byte[500];
	            int length = 0;
	            while(-1 != (length = is.read(buffer, 0, buffer.length))) {
	                os.write(buffer);
	            }
	            os.close();
	            is.close();
	            this.goods.setGoodimg(currDate+"/"+newFileName);
			}
			//图片1
			if(this.img1 != null) {
				//上传封面
				String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.img1FileName.substring(this.img1FileName.lastIndexOf("."));
				InputStream is = new FileInputStream(this.img1);
				OutputStream os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
				byte[] buffer = new byte[500];
				int length = 0;
				while(-1 != (length = is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
				this.goods.setImg1(currDate+"/"+newFileName);
			}
			//图片2
			if(this.img2 != null) {
				//上传封面
				String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.img2FileName.substring(this.img2FileName.lastIndexOf("."));
				InputStream is = new FileInputStream(this.img2);
				OutputStream os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
				byte[] buffer = new byte[500];
				int length = 0;
				while(-1 != (length = is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
				this.goods.setImg2(currDate+"/"+newFileName);
			}
			//图片3
			if(this.img3 != null) {
				//上传封面
				String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.img3FileName.substring(this.img3FileName.lastIndexOf("."));
				InputStream is = new FileInputStream(this.img3);
				OutputStream os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
				byte[] buffer = new byte[500];
				int length = 0;
				while(-1 != (length = is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
				this.goods.setImg3(currDate+"/"+newFileName);
			}
			//图片4
			if(this.img4 != null) {
				//上传封面
				String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.img4FileName.substring(this.img4FileName.lastIndexOf("."));
				InputStream is = new FileInputStream(this.img4);
				OutputStream os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
				byte[] buffer = new byte[500];
				int length = 0;
				while(-1 != (length = is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
				this.goods.setImg4(currDate+"/"+newFileName);
			}
			//图片5
			if(this.img5 != null) {
				//上传封面
				String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.img5FileName.substring(this.img5FileName.lastIndexOf("."));
				InputStream is = new FileInputStream(this.img5);
				OutputStream os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
				byte[] buffer = new byte[500];
				int length = 0;
				while(-1 != (length = is.read(buffer, 0, buffer.length))) {
					os.write(buffer);
				}
				os.close();
				is.close();
				this.goods.setImg5(currDate+"/"+newFileName);
			}
			
			DBUtil db = DBUtil.getDBUtilByRequest();
			if(this.goods.getId()==null) {
				this.goods.setIsuse(SysDict.ISUSE_NO);
				
				Long id = (Long) db.insert(this.goods);
				this.goods.setGoodsCode(this.goods.getCategory()+""+id);
				db.update(this.goods);
			} else {
				NsGoods ns = this.goodsManagerService.queryGoodsById(this.goods.getId());
				ns.setGname(this.goods.getGname());
				ns.setGfullname(this.goods.getGfullname());
				ns.setPrice(this.goods.getPrice());
				ns.setStorenumb(this.goods.getStorenumb());
				ns.setCategory(this.goods.getCategory());
				ns.setKind(this.goods.getKind());
				ns.setBrand(this.goods.getBrand());
				ns.setDetail(this.goods.getDetail());
				if(StringUtils.isNotBlank(this.goods.getGoodimg())) 
					ns.setGoodimg(this.goods.getGoodimg());
				if(StringUtils.isNotBlank(this.goods.getGoodimg())) 
					ns.setGoodimglist(this.goods.getGoodimglist());
				if(StringUtils.isNotBlank(this.goods.getImg1())) 
					ns.setImg1(this.goods.getImg1());
				if(StringUtils.isNotBlank(this.goods.getImg2())) 
					ns.setImg2(this.goods.getImg2());
				if(StringUtils.isNotBlank(this.goods.getImg3())) 
					ns.setImg3(this.goods.getImg3());
				if(StringUtils.isNotBlank(this.goods.getImg4())) 
					ns.setImg4(this.goods.getImg4());
				if(StringUtils.isNotBlank(this.goods.getImg5())) 
					ns.setImg5(this.goods.getImg5());
				//清除图片
				if(StringUtils.isNotBlank(this.removeImgs)) {
					String[] temps = this.removeImgs.split(",");
					for(String temp : temps) {
						if(StringUtils.isBlank(temp))
							continue;
						if("img1".equals(temp))
							ns.setImg1("");
						if("img2".equals(temp)) 
							ns.setImg2("");
						if("img3".equals(temp)) 
							ns.setImg3("");
						if("img4".equals(temp))  
							ns.setImg4("");
						if("img5".equals(temp))  
							ns.setImg5("");
					}
				}
				db.update(ns);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	/*public String deleteGoods() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String idstr = RequestHelper.getParameter("id");
			String[] ids = idstr.split(",");
			for(String id : ids){
				NsGoods ns = this.goodsManagerService.queryGoodsById(new Long(id));
				ns.setIsuse(SysDict.ISUSE_NO);
				db.update(ns);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.message="success";
		return Action.SUCCESS;
	}*/
	
	public String invalidGoods() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String id = RequestHelper.getParameter("id");
			
			Map<String, Object> params = new HashMap<String, Object>(2);
			params.put("linkkind", new Long(0));
			params.put("imglink", id);
			List<NsAdvertise> list = this.advertiseManagerService.queryAdvByParams(params);
			if(list!=null && !list.isEmpty()) {
				this.message = "该商品有广告未过期，不能设为无效";
				return Action.SUCCESS;
			}
			
			List<NsEventsinfo> list2 = this.eventsManagerService.queryEventsGoodsByGoodsId(new Long(id));
			if(list!=null && !list.isEmpty()) {
				this.message = "该商品有活动发布，不能设为无效";
				return Action.SUCCESS;
			}
			
			NsGoods ns = this.goodsManagerService.queryGoodsById(new Long(id));
			ns.setIsuse(SysDict.ISUSE_NO);
			db.update(ns);
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.message="success";
		return Action.SUCCESS;
	}
	
	
	public String recoverGoods() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String id = RequestHelper.getParameter("id");
			NsGoods ns = this.goodsManagerService.queryGoodsById(new Long(id));
			ns.setIsuse(SysDict.ISUSE_YES); 
			db.update(ns);
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.message="success";
		return Action.SUCCESS;
	}
	
	public NsGoods getGoods() {
		return goods;
	}

	public void setGoods(NsGoods goods) {
		this.goods = goods;
	}
	
	public String getMessage() {
		return message;
	}

	public void setRemoveImgs(String removeImgs) {
		this.removeImgs = removeImgs;
	}

	public void setGoodimg(File goodimg) {
		this.goodimg = goodimg;
	}

	public void setGoodimgFileName(String goodimgFileName) {
		this.goodimgFileName = goodimgFileName;
	}

	/*public void setGoodimgContentType(String goodimgContentType) {
		this.goodimgContentType = goodimgContentType;
	}*/

	public void setImg1(File img1) {
		this.img1 = img1;
	}

	public void setImg1FileName(String img1FileName) {
		this.img1FileName = img1FileName;
	}

	public void setImg2(File img2) {
		this.img2 = img2;
	}

	public void setImg2FileName(String img2FileName) {
		this.img2FileName = img2FileName;
	}

	public void setImg3(File img3) {
		this.img3 = img3;
	}

	public void setImg3FileName(String img3FileName) {
		this.img3FileName = img3FileName;
	}

	public void setImg4(File img4) {
		this.img4 = img4;
	}

	public void setImg4FileName(String img4FileName) {
		this.img4FileName = img4FileName;
	}

	public void setImg5(File img5) {
		this.img5 = img5;
	}

	public void setImg5FileName(String img5FileName) {
		this.img5FileName = img5FileName;
	}
	
}