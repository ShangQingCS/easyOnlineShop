package cn.sqkj.nsyl.goodsManager.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

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
public class GoodsManagerAction extends PageAction {
	@Resource(name="goodsManagerService")
	private IGoodsManagerService goodsManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction.class); //系统log日志对象
	private List<Map> trees;
	
	public String queryGoods() {
		TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
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
			e.printStackTrace();
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
		List<Map> list = db.queryBySQL(sql.toString());
		
		Map root = null;
		Map<String,Map> temp = new TreeMap();
		for (Map pojo : list) {
			Map node = new TreeMap();
			node.put("id", pojo.get("id"));
			node.put("text", pojo.get("cateName"));
			node.put("parentId", pojo.get("parentId"));
			
			Map attrs = new HashMap(5);
			attrs.put("cateOrder", pojo.get("cateOrder"));
			attrs.put("logo", pojo.get("logo"));
			attrs.put("level", pojo.get("level"));
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
			Map node = temp.get(key);
			Map parentMap = temp.get(node.get("parentId").toString());
			if(parentMap != null) {
				if(parentMap.get("children") == null) {
					parentMap.put("children", new ArrayList());
				}
				((ArrayList) parentMap.get("children")).add(node);
			}
		}
		
		//排序
		for(String key : temp.keySet()) {
			Map node = temp.get(key);
			if(node.get("children") != null) {
				List childOrgList = (ArrayList) node.get("children");
				if(!childOrgList.isEmpty()) {
					Collections.sort(childOrgList, new ComparatorGoodsCategoryVO());
				}
			} 
		}
		
		trees = new ArrayList(1);
		if(root != null) {
			trees.add(root);
		}
		return Action.SUCCESS;
	}
	
	public List getTrees() {
		return trees;
	}
}