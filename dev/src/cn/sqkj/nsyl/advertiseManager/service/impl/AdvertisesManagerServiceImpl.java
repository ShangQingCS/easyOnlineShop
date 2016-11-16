package cn.sqkj.nsyl.advertiseManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.advertiseManager.dao.NsAdvertiseDAO;
import cn.sqkj.nsyl.advertiseManager.pojo.NsAdvertise;
import cn.sqkj.nsyl.advertiseManager.service.IAdvertiseManagerService;
import framework.bean.PageBean;
import framework.config.SysDict;
import framework.db.DBUtil;

/**
 * @author yangchaowen
 * 2016年11月10日
 * 广告管理service实现类
 */
@SuppressWarnings({"rawtypes","unchecked"})
@Service("advertiseManagerService")
public class AdvertisesManagerServiceImpl implements IAdvertiseManagerService {
	@Resource(name="advertiseDAO")
	private NsAdvertiseDAO advertiseDAO;

	public PageBean queryAdvList(PageBean pageBean) throws Exception {

		//SQL方式
		StringBuffer sql = new StringBuffer(" select t.*,t1.name as typename from ns_advertise t  ");
		sql.append(" left join ns_dictionaries t1 on t.kind=t1.code and t1.`type`='ADV_BANNER' ");
		sql.append(" where t.flag=? ");
		List params = new ArrayList();
		params.add(SysDict.FLAG_ACT);
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("name"))) {
				sql.append(" and t.name like ? ");
				params.add("%"+pageBean.getQueryParams().get("name")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("linkkind"))) {
				sql.append(" and t.linkkind = ? ");
				params.add(pageBean.getQueryParams().get("linkkind"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("type"))) {
				sql.append(" and t.type = ? ");
				params.add(pageBean.getQueryParams().get("type"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("kind")) && !"-1".equals(pageBean.getQueryParams().get("kind"))) {
				sql.append(" and t.kind = ? ");
				params.add(pageBean.getQueryParams().get("kind"));
			}
		}
		
		//查询总计路数
		int total = advertiseDAO.findAdvCount(sql.toString(), params);
		//查询数据
		List<NsAdvertise> list = advertiseDAO.findAdvPage(sql.toString(), params, pageBean);
		
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}
	
	public NsAdvertise queryAdvById(Long id) throws Exception {
		if(id!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsAdvertise na = (NsAdvertise) db.getSession().get(NsAdvertise.class, id);
			return na;
		}
		return null;
	}
}
