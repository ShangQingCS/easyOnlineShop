package cn.sqkj.nsyl.goodsManager.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.sqkj.nsyl.goodsManager.pojo.NsGoods;
import cn.sqkj.nsyl.goodsManager.pojo.NsGoodsCategory;
import cn.sqkj.nsyl.goodsManager.service.IGoodsManagerService;
import cn.sqkj.nsyl.goodsManager.util.ComparatorGoodsCategoryVO;

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
@SuppressWarnings("unchecked")
public class GoodsManagerAction extends PageAction {
	@Resource(name="goodsManagerService")
	private IGoodsManagerService goodsManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction.class); //系统log日志对象
	private List<Map<String,Object>> goodsCategoryTree;
	private NsGoodsCategory goodsCategory;
	private NsGoods goods;
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private List<NsGoodsCategory> categorys;
	private List<NsGoodsCategory> kinds;
	private List<NsGoodsCategory> brands;
	private NsGoodsCategory categoryVo;
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
	
	public String saveGoods() {
		//从配置文件读取
		String imgPathPrefix = "http://127.0.0.1:8080/dev/upload/"; 
		String imgUploadPath = ServletActionContext.getServletContext().getRealPath("/upload");
		
		try {
			File dir = new File(imgUploadPath);
			if(!dir.exists()) dir.mkdir();
			StringBuffer files = new StringBuffer();
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		DBUtil db = DBUtil.getDBUtilByRequest();
		if(goods.getId()==null) {
			Long id = (Long) db.insert(goods);
		} else {
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询一级分类
	 */
	public String queryCategorys() {
		DBUtil db = DBUtil.getDBUtilByRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level", 1);
		this.categorys = db.queryByPojo(NsGoodsCategory.class, params);
		return Action.SUCCESS;
	}
	/**
	 * 查询二级分类
	 */
	public String queryKinds() {
		DBUtil db = DBUtil.getDBUtilByRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level", 2);
		params.put("parentId", categoryVo.getId());
		this.kinds = db.queryByPojo(NsGoodsCategory.class, params);
		return Action.SUCCESS;
	}
	/**
	 * 查询三级分类
	 */
	public String queryBrands() {
		DBUtil db = DBUtil.getDBUtilByRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level", 3);
		params.put("parentId", categoryVo.getId());
		this.brands = db.queryByPojo(NsGoodsCategory.class, params);
		return Action.SUCCESS;
	}
	
	/**
	 * 商品匪类树形结构
	 * @return
	 */
	public String goodsCategoryTree() {
		StringBuffer sql = new StringBuffer(" select c.id,c.cate_name,c.desc_,c.cate_order,c.`level`,c.isuser,c.parent_id ");
		sql.append(" from ns_goods_category c ");
		sql.append(" where c.flag='0' order by c.cate_order ");
		DBUtil db = DBUtil.getDBUtilByRequest();
		List<Map<String,Object>> list = db.queryBySQL(sql.toString());
		
		Map<String,Object> root = null;
		Map<String,Map<String,Object>> temp = new TreeMap<String,Map<String,Object>>();
		for (Map<String,Object> pojo : list) {
			Map<String,Object> node = new TreeMap<String,Object>();
			node.put("id", pojo.get("id"));
			node.put("text", pojo.get("cateName"));
			node.put("parentId", pojo.get("parentId"));
			
			Map<String,Object> attrs = new HashMap<String,Object>(5);
			attrs.put("cateOrder", pojo.get("cateOrder"));
			attrs.put("logo", pojo.get("logo"));
			attrs.put("level", pojo.get("level"));
			attrs.put("isuser", pojo.get("isuser"));
			if(pojo.get("level") != null && !"3".equals(pojo.get("level").toString())) {
				node.put("state", "closed");
			}
			node.put("attributes", attrs);
			temp.put(pojo.get("id").toString(), node);
			if("-1".equals(node.get("parentId").toString())) {
				root = node;
			}
		}
		
		//组装父子关系
		for(String key : temp.keySet()) {
			Map<String,Object> node = temp.get(key);
			Map<String,Object> parentMap = temp.get(node.get("parentId").toString());
			if(parentMap != null) {
				if(parentMap.get("children") == null) {
					parentMap.put("children", new ArrayList<Map<String,Object>>());
				}
				((ArrayList<Map<String,Object>>) parentMap.get("children")).add(node);
			}
		}
		
		//排序
		for(String key : temp.keySet()) {
			Map<String,Object> node = temp.get(key);
			if(node.get("children") != null) {
				List<Map<String,Object>> childOrgList = (ArrayList<Map<String,Object>>) node.get("children");
				if(!childOrgList.isEmpty()) {
					Collections.sort(childOrgList, new ComparatorGoodsCategoryVO());
				}
			} 
		}
		
		goodsCategoryTree = new ArrayList<Map<String,Object>>(1);
		if(root != null) {
			goodsCategoryTree.add(root);
		}
		//打印审计日志
		TAuditLog message = new TAuditLog(user.getUId(), "查询商品类别成功！");
		logger.info(message);
		return Action.SUCCESS;
	}
	
	public String saveGoodsCategory() {
		try {
			this.goodsManagerService.saveGoodsCategory(this.goodsCategory);
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "保存商品类别成功："+this.goodsCategory.getId());
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			log.error("保存商品类别异常！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "保存商品类别失败；"+this.goodsCategory.getId());
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String deleteGoodsCategory() {
		String msg = null;
		try {
			msg = this.goodsManagerService.deleteGoodsCategory(this.goodsCategory);
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "删除商品类别成功："+this.goodsCategory.getId());
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			log.error("删除商品类别异常！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "删除商品类别失败；"+this.goodsCategory.getId());
			logger.info(message);
		}
		
		if(StringUtils.isNotBlank(msg)) {
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public List<Map<String,Object>> getGoodsCategoryTree() {
		return goodsCategoryTree;
	}

	public NsGoodsCategory getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(NsGoodsCategory goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public NsGoods getGoods() {
		return goods;
	}

	public void setGoods(NsGoods goods) {
		this.goods = goods;
	}

	public List<NsGoodsCategory> getCategorys() {
		return categorys;
	}

	public List<NsGoodsCategory> getKinds() {
		return kinds;
	}

	public List<NsGoodsCategory> getBrands() {
		return brands;
	}

	public NsGoodsCategory getCategoryVo() {
		return categoryVo;
	}

	public void setCategoryVo(NsGoodsCategory categoryVo) {
		this.categoryVo = categoryVo;
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
}