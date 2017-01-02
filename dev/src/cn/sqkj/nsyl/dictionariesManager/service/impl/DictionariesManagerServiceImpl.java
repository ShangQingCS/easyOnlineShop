package cn.sqkj.nsyl.dictionariesManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.db.po.NsDictionaries;
import cn.sqkj.nsyl.dictionariesManager.dao.DictionariesDAO;
import cn.sqkj.nsyl.dictionariesManager.service.IDictionariesManagerService;
import cn.sqkj.nsyl.goodsManager.pojo.NsGoodsCategory;
import framework.bean.PageBean;
import framework.config.SysDict;
import framework.db.DBUtil;

/**
 * 
 * @author hxz
 * 2016年12月27日
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
@Service("baseDataManagerService")
public class DictionariesManagerServiceImpl implements IDictionariesManagerService{
	
	@Resource(name="dictionariesDAO")
	private DictionariesDAO dictionariesDAO;
	
	public PageBean queryBaseDataList(PageBean pageBean) throws Exception {
		StringBuffer sql = new StringBuffer(" select t.id,t.type,t.code,t.name,t.parentcode,t.remark,t.isuse,t.isedit,t.sort from ns_dictionaries t  ");
		sql.append(" where 1=1 ");
		List params = new ArrayList();
		//params.add(SysDict.ISUSE_YES);//数据有效
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("type"))) {
				sql.append(" and t.type like ? ");
				params.add("%"+pageBean.getQueryParams().get("type")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("name"))) {
				sql.append(" and t.name like ? ");
				params.add("%"+pageBean.getQueryParams().get("name")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("isuse"))) {
				sql.append(" and t.isuse like ? ");
				params.add("%"+pageBean.getQueryParams().get("isuse")+"%");
			}
		}
		//查询总计路数
		int total = dictionariesDAO.findBaseDataCount(sql.toString(), params);
		//查询数据集
		List<NsDictionaries> list = dictionariesDAO.findDictionariesPage(sql.toString(), params, pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}
	
	
	public NsDictionaries queryBaseDataById(Long id) throws Exception {
		// TODO Auto-generated method stub
		if(id!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsDictionaries nd = (NsDictionaries) db.getSession().get(NsDictionaries.class, id);
			return nd;
		}
		return null;
	}
	
	
	public NsDictionaries saveNsDictionaries(NsDictionaries nsDictionaries)
			throws Exception {
		// TODO Auto-generated method stub
		DBUtil db = DBUtil.getDBUtilByRequest();
		//新增
		if(nsDictionaries.getId()==null) {
			Long id = (Long) db.insert(nsDictionaries);
			nsDictionaries.setId(id);
		}else{
			//修改
			NsDictionaries nsDictionariesVo = (NsDictionaries) db.get(NsDictionaries.class, nsDictionaries.getId());
			nsDictionariesVo.setCode(nsDictionaries.getCode());
			nsDictionariesVo.setIsedit(nsDictionaries.getIsedit());
			nsDictionariesVo.setIsuse(nsDictionaries.getIsuse());
			nsDictionariesVo.setName(nsDictionaries.getName());
			nsDictionariesVo.setSort(nsDictionariesVo.getSort());
			nsDictionariesVo.setParentcode(nsDictionaries.getParentcode());
			nsDictionariesVo.setRemark(nsDictionaries.getRemark());
			nsDictionariesVo.setType(nsDictionaries.getType());
			db.update(nsDictionariesVo);
		}
		return nsDictionaries;
	}
	
	/**
	 * 删除字典
	 */
	public String deleteNsDictionaries(NsDictionaries nsDictionaries)
			throws Exception {
		// TODO Auto-generated method stub
		DBUtil db = DBUtil.getDBUtilByRequest();
        Query query = db.getSession().createQuery("update NsDictionaries t set t.isuse = 1 where id = "+nsDictionaries.getId()+"");  
        query.executeUpdate();  
		return "删除成功！";
	}

	

}
