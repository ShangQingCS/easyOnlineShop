package cn.sqkj.nsyl.goodsManager.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

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
	private NsGoodsCategory goodsCategoryVO;
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	
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
	
	public String save() {
		try {
			this.goodsManagerService.saveGoodsCategory(this.goodsCategoryVO);
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "保存商品类别成功："+this.goodsCategoryVO.getId());
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			log.error("保存商品类别异常！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "保存商品类别失败；"+this.goodsCategoryVO.getId());
			logger.info(message);
		}
		return Action.SUCCESS;
	}
	
	public String delete() {
		String msg = null;
		try {
			msg = this.goodsManagerService.deleteGoodsCategory(this.goodsCategoryVO);
			
			//打印审计日志
			TAuditLog message = new TAuditLog(user.getUId(), "删除商品类别成功："+this.goodsCategoryVO.getId());
			logger.info(message);
		} catch (Exception e) {
			/*e.printStackTrace();*/
			log.error("删除商品类别异常！", e);
			TAuditLog message = new TAuditLog(user.getUId(), "删除商品类别失败；"+this.goodsCategoryVO.getId());
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

	public NsGoodsCategory getGoodsCategoryVO() {
		return goodsCategoryVO;
	}

	public void setGoodsCategoryVO(NsGoodsCategory goodsCategoryVO) {
		this.goodsCategoryVO = goodsCategoryVO;
	}
	
}