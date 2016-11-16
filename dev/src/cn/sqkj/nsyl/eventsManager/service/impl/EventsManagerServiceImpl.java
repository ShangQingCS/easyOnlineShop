package cn.sqkj.nsyl.eventsManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.eventsManager.dao.NsEventsinfoDAO;
import cn.sqkj.nsyl.eventsManager.pojo.NsEventsinfo;
import cn.sqkj.nsyl.eventsManager.service.IEventsManagerService;
import framework.bean.PageBean;
import framework.config.SysDict;
import framework.db.DBUtil;

/**
 * @author yangchaowen
 * 2016年11月10日
 * 活动管理service实现类
 */
@Service("eventsManagerService")
public class EventsManagerServiceImpl implements IEventsManagerService {
	@Resource(name="eventsDAO")
	private NsEventsinfoDAO eventsDAO;

	public PageBean queryEventsList(PageBean pageBean) throws Exception {
		//SQL方式
		StringBuffer sql = new StringBuffer(" select * from ns_eventsinfo ");
		sql.append(" where flag=? ");
		List params = new ArrayList();
		params.add(SysDict.FLAG_ACT);
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
		int total = eventsDAO.findEventsCount(sql.toString(), params);
		//查询数据
		List<NsEventsinfo> list = eventsDAO.findEventsPage(sql.toString(), params, pageBean);
		
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}
	
	public NsEventsinfo queryEventsById(Long id) throws Exception {
		if(id!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsEventsinfo ne = (NsEventsinfo) db.getSession().get(NsEventsinfo.class, id);
			return ne;
		}
		return null;
	}
}
