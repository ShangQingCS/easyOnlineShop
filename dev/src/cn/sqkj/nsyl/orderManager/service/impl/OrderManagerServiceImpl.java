package cn.sqkj.nsyl.orderManager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.orderManager.dao.NsOrderDAO;
import cn.sqkj.nsyl.orderManager.pojo.NsOrder;
import cn.sqkj.nsyl.orderManager.service.IOrderManagerService;
import framework.bean.PageBean;
import framework.db.DBUtil;

/**
 * @author yangchaowen
 * 2016年11月7日
 * 订单管理Service实现类
 */
@SuppressWarnings({"rawtypes","unchecked"})
@Service("orderManagerService")
public class OrderManagerServiceImpl implements IOrderManagerService {
	@Resource(name="orderDAO")
	private NsOrderDAO orderDAO;
	
	/**
	 * 查询评论列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PageBean queryOrderList(PageBean pageBean) throws Exception {
		StringBuffer sql = new StringBuffer(" select t.* from ns_order t ");
		sql.append(" where 1=1 ");
		List params = new ArrayList();
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			/*if(StringUtils.isNotBlank(pageBean.getQueryParams().get("goodid"))) {
				sql.append(" and t.id = ? ");
				params.add(pageBean.getQueryParams().get("goodid"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("gname"))) {
				sql.append(" and t1.gname like ? ");
				params.add("%"+pageBean.getQueryParams().get("gname")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("category"))) {
				sql.append(" and t1.category = ? ");
				params.add(pageBean.getQueryParams().get("category"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("kind"))) {
				sql.append(" and t1.kind = ? ");
				params.add(pageBean.getQueryParams().get("kind"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("brand"))) {
				sql.append(" and t1.brand = ? ");
				params.add(pageBean.getQueryParams().get("brand"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("userid"))) {
				sql.append(" and t.userid like ? ");
				params.add("%"+pageBean.getQueryParams().get("userid")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("comment"))) {
				sql.append(" and t.comment like ? ");
				params.add("%"+pageBean.getQueryParams().get("comment")+"%");
			}*/
		}
		
		//查询总计路数
		int total = orderDAO.findOrderCount(sql.toString(), params);
		//查询数据
		List<NsOrder> goods = orderDAO.findOrderPage(sql.toString(), params, pageBean);
		
		pageBean.setTotal(total);
		pageBean.setPageData(goods);
		return pageBean;
	}
	
	public NsOrder queryOrderById(Long id) throws Exception {
		if(id!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsOrder nc = (NsOrder) db.getSession().get(NsOrder.class, id);
			return nc;
		}
		return null;
	}
	
	public List queryOrderDetailByOrderId(Long orderId) throws Exception {
		if(orderId!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			Map<String, Object> params = new HashMap<String, Object>(1);
			params.put("orderid", orderId);
			List list = db.queryByHql(" from NsOrderDetail as t where t.orderid=?", params);			
			return list;
		}
		return null;
	}
}