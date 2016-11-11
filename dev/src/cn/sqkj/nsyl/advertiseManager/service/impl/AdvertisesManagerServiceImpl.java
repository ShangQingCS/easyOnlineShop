package cn.sqkj.nsyl.advertiseManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.advertiseManager.dao.NsAdvertiseDAO;
import cn.sqkj.nsyl.advertiseManager.pojo.NsAdvertise;
import cn.sqkj.nsyl.advertiseManager.service.IAdvertiseManagerService;
import framework.bean.PageBean;

/**
 * @author yangchaowen
 * 2016年11月10日
 * 广告管理service实现类
 */
@Service("advertiseManagerService")
public class AdvertisesManagerServiceImpl implements IAdvertiseManagerService {
	@Resource(name="advertiseDAO")
	private NsAdvertiseDAO advertiseDAO;

	public PageBean queryAdvList(PageBean pageBean) throws Exception {

		//SQL方式
		StringBuffer sql = new StringBuffer(" select * from ns_advertise ");
		sql.append(" where 1=1 ");
		List params = new ArrayList();
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			/*if(StringUtils.isNotBlank(pageBean.getQueryParams().get("gname"))) {
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
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("storenumbone"))) {
				sql.append(" and t1.storenumb >= ? ");
				params.add(pageBean.getQueryParams().get("storenumbone"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("storenumbtwo"))) {
				sql.append(" and t1.storenumb <= ? ");
				params.add(pageBean.getQueryParams().get("storenumbtwo"));
			}*/
		}
		
		//查询总计路数
		int total = advertiseDAO.findAdvCount(sql.toString(), params);
		//查询数据
		List<NsAdvertise> list = advertiseDAO.findAdvPage(sql.toString(), params, pageBean);
		
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}
}
