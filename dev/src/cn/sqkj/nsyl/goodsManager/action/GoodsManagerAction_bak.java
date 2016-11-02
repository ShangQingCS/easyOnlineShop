package cn.sqkj.nsyl.goodsManager.action;

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

import cn.sqkj.nsyl.goodsManager.pojo.NsGoods;
import cn.sqkj.nsyl.goodsManager.service.IGoodsManagerService;

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
 * 2016年10月18日
 * 商品维护Action类
 */
public class GoodsManagerAction_bak extends PageAction {
	@Resource(name="goodsManagerService")
	private IGoodsManagerService goodsManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction_bak.class); //系统log日志对象
	private NsGoods goods;
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	
	private String message;
	
	//封面
	private File goodimg;
    private String goodimgFileName;
    private String goodimgContentType;
    //图片
	//这里用List来存放上传过来的文件， 同样指的是临时文件夹中的临时文件，而不是真正上传过来的文件
    private List<File> goodimglist;
	//这个List存放的是文件的名字，和List<File>中的文件相对应
    private List<String> goodimglistFileName;
    private List<String> goodimglistContentType;

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
		String imgPathPrefix = "http://127.0.0.1:8080/dev/upload/"; 
		String imgUploadPath = ServletActionContext.getServletContext().getRealPath("/upload");
		
		try {
			File dir = new File(imgUploadPath);
			if(!dir.exists()) dir.mkdir();
			StringBuffer files = new StringBuffer();
			if(this.goodimglist!=null) {
		        for(int i = 0; i < goodimglist.size(); i++) {
		        	String newFileName = UUID.randomUUID().toString()+goodimglistFileName.get(i);
		        	files.append(",").append(imgPathPrefix+newFileName);
		            InputStream is = new FileInputStream(goodimglist.get(i));
		            OutputStream os = new FileOutputStream(new File(imgUploadPath, newFileName));
		            byte[] buffer = new byte[500];
		            int length = 0;
		            while(-1 != (length = is.read(buffer, 0, buffer.length))) {
		                os.write(buffer);
		            }
		            os.close();
		            is.close();
		        }
		        String imagesPath = files.toString().replaceFirst(",", "");
		        this.goods.setGoodimglist(imagesPath);
			}
	        
			if(this.goodimg != null) {
		        //上传封面
		        String newFileName = UUID.randomUUID().toString()+goodimgFileName;
		        InputStream is = new FileInputStream(goodimg);
	            OutputStream os = new FileOutputStream(new File(imgUploadPath, newFileName));
	            byte[] buffer = new byte[500];
	            int length = 0;
	            while(-1 != (length = is.read(buffer, 0, buffer.length))) {
	                os.write(buffer);
	            }
	            os.close();
	            is.close();
	            this.goods.setGoodimg(imgPathPrefix+newFileName);
			}
			
			DBUtil db = DBUtil.getDBUtilByRequest();
			if(this.goods.getId()==null) {
				this.goods.setIsuser(1);
				Long id = (Long) db.insert(this.goods);
			} else {
				NsGoods ns = this.goodsManagerService.queryGoodsById(this.goods.getId());
				ns.setGname(this.goods.getGname());
				ns.setGfullname(this.goods.getGfullname());
				ns.setPrice(this.goods.getPrice());
				ns.setStorenumb(this.goods.getStorenumb());
				ns.setCategory(this.goods.getCategory());
				ns.setKind(this.goods.getKind());
				ns.setBrand(this.goods.getBrand());
				if(StringUtils.isNotBlank(this.goods.getGoodimg())) {
					ns.setGoodimg(this.goods.getGoodimg());
				}
				if(StringUtils.isNotBlank(this.goods.getGoodimglist())) {
					ns.setGoodimglist(this.goods.getGoodimglist());
				}
				db.update(ns);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String deleteGoods() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			String idstr = RequestHelper.getParameter("id");
			String[] ids = idstr.split(",");
			for(String id : ids){
				NsGoods ns = this.goodsManagerService.queryGoodsById(new Long(id));
				ns.setIsuser(1);
				db.update(ns);
			}
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
			ns.setIsuser(0); 
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

	public List<File> getGoodimglist() {
		return goodimglist;
	}

	public void setGoodimglist(List<File> goodimglist) {
		this.goodimglist = goodimglist;
	}

	public List<String> getGoodimglistFileName() {
		return goodimglistFileName;
	}

	public void setGoodimglistFileName(List<String> goodimglistFileName) {
		this.goodimglistFileName = goodimglistFileName;
	}

	public List<String> getGoodimglistContentType() {
		return goodimglistContentType;
	}

	public void setGoodimglistContentType(List<String> goodimglistContentType) {
		this.goodimglistContentType = goodimglistContentType;
	}

	public File getGoodimg() {
		return goodimg;
	}

	public void setGoodimg(File goodimg) {
		this.goodimg = goodimg;
	}
	
	public String getGoodimgFileName() {
		return goodimgFileName;
	}

	public void setGoodimgFileName(String goodimgFileName) {
		this.goodimgFileName = goodimgFileName;
	}

	public String getGoodimgContentType() {
		return goodimgContentType;
	}

	public void setGoodimgContentType(String goodimgContentType) {
		this.goodimgContentType = goodimgContentType;
	}
	
	public String getMessage() {
		return message;
	}
}