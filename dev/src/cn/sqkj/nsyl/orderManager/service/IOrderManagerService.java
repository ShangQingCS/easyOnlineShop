package cn.sqkj.nsyl.orderManager.service;

import java.util.List;

import cn.sqkj.nsyl.orderManager.pojo.NsOrder;
import framework.bean.PageBean;

public interface IOrderManagerService {
	
	public PageBean queryOrderList(PageBean pageBean) throws Exception;
	
	public NsOrder queryOrderById(Long id) throws Exception;
	
	public List queryOrderDetailByOrderId(Long orderId) throws Exception;
}