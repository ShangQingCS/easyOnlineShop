package cn.sqkj.nsyl.orderManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("userid"))) {
				sql.append(" and t.userid like ? ");
				params.add("%"+pageBean.getQueryParams().get("userid")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("mixTotal"))) {
				sql.append(" and t.total >= ? ");
				params.add(pageBean.getQueryParams().get("mixTotal"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("maxTotal"))) {
				sql.append(" and t.total <= ? ");
				params.add(pageBean.getQueryParams().get("maxTotal"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("orderstatus"))) {
				sql.append(" and t.orderstatus = ? ");
				params.add(pageBean.getQueryParams().get("orderstatus"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("paytype"))) {
				sql.append(" and t.paytype = ? ");
				params.add(pageBean.getQueryParams().get("paytype"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("invoice"))) {
				sql.append(" and t.invoice = ? ");
				params.add(pageBean.getQueryParams().get("invoice"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("deliveryNumb"))) {
				sql.append(" and t.deliveryNumb like ? ");
				params.add("%"+pageBean.getQueryParams().get("deliveryNumb")+"%");
			}
			/*if(StringUtils.isNotBlank(pageBean.getQueryParams().get("startCreateTime"))) {
				sql.append(" and t.startCreateTime >= ? ");
				params.add("%"+pageBean.getQueryParams().get("startCreateTime")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("endCreateTime"))) {
				sql.append(" and t.comment like ? ");
				params.add("%"+pageBean.getQueryParams().get("endCreateTime")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("startDeliveryTime"))) {
				sql.append(" and t.comment like ? ");
				params.add("%"+pageBean.getQueryParams().get("startDeliveryTime")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("endDeliveryTime"))) {
				sql.append(" and t.comment like ? ");
				params.add("%"+pageBean.getQueryParams().get("endDeliveryTime")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("paynumb"))) {
				sql.append(" and t.paynumb like ? ");
				params.add("%"+pageBean.getQueryParams().get("paynumb")+"%");
			}*/
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("name"))) {
				sql.append(" and t.name like ? ");
				params.add("%"+pageBean.getQueryParams().get("name")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("contactnumb"))) {
				sql.append(" and t.contactnumb like ? ");
				params.add("%"+pageBean.getQueryParams().get("contactnumb")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("companyname"))) {
				sql.append(" and t.companyname like ? ");
				params.add("%"+pageBean.getQueryParams().get("companyname")+"%");
			}
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
		if(orderId != null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			StringBuffer sql = new StringBuffer(" select t.*,t1.gname,t2.cate_name as category_name,t3.cate_name as kind_name,t4.cate_name as brand_name from ns_order_detail t ");
			sql.append(" left join ns_goods t1 on t.goodsid=t1.id ");
			sql.append(" left join ns_goods_category t2 on t1.category=t2.id ");
			sql.append(" left join ns_goods_category t3 on t1.kind=t3.id ");
			sql.append(" left join ns_goods_category t4 on t1.brand=t4.id ");
			sql.append(" where t.orderid=? ");
			List list = db.queryBySQL(sql.toString(), orderId);
			return list;
		}
		return null;
	}
}