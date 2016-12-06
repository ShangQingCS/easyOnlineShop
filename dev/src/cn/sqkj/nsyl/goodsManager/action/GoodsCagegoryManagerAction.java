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

import cn.sqkj.nsyl.goodsManager.pojo.NsGoodsCategory;
import cn.sqkj.nsyl.goodsManager.service.IGoodsManagerService;
import cn.sqkj.nsyl.goodsManager.util.ComparatorGoodsCategoryVO;

import com.opensymphony.xwork2.Action;

import framework.action.PageAction;
import framework.config.Config;
import framework.db.DBUtil;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;
import framework.util.DateUtils;

/**
 * @author yangchaowen
 * 2016年10月18日
 * 商品类别维护Action类
 */
@SuppressWarnings("unchecked")
public class GoodsCagegoryManagerAction extends PageAction {
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsCagegoryManagerAction.class); //系统log日志对象
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	@Resource(name="goodsManagerService")
	private IGoodsManagerService goodsManagerService;
	private List<Map<String,Object>> goodsCategoryTree;
	private NsGoodsCategory goodsCategory;
	private List<NsGoodsCategory> categorys;
	private NsGoodsCategory categoryVo;
	private List<NsGoodsCategory> kinds;
	private List<NsGoodsCategory> brands;
	private File logo;
    private String logoFileName;
    
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
		StringBuffer sql = new StringBuffer(" select c.id,c.cate_name,c.desc_,c.cate_order,c.`level`,c.isuse,c.parent_id,c.logo,c.cate_code ");
		sql.append(" from ns_goods_category c ");
//		sql.append(" where c.flag='0' order by c.cate_order ");
		sql.append(" where c.flag='1' order by c.cate_order ");
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
			attrs.put("isuse", pojo.get("isuse"));
			attrs.put("cateCode", pojo.get("cateCode"));
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
			if(this.logo != null) {
		        //上传封面
		        String newFileName = UUID.randomUUID().toString()+System.currentTimeMillis()+this.logoFileName.substring(this.logoFileName.lastIndexOf("."));
		        InputStream is = new FileInputStream(this.logo);
	            OutputStream os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
	            byte[] buffer = new byte[500];
	            int length = 0;
	            while(-1 != (length = is.read(buffer, 0, buffer.length))) {
	                os.write(buffer);
	            }
	            os.close();
	            is.close();
	            this.goodsCategory.setLogo(currDate+"/"+newFileName);
			}
			
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
	
	public List<NsGoodsCategory> getCategorys() {
		return categorys;
	}

	public List<NsGoodsCategory> getKinds() {
		return kinds;
	}

	public List<NsGoodsCategory> getBrands() {
		return brands;
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
	
	public NsGoodsCategory getCategoryVo() {
		return categoryVo;
	}

	public void setCategoryVo(NsGoodsCategory categoryVo) {
		this.categoryVo = categoryVo;
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}
	
}