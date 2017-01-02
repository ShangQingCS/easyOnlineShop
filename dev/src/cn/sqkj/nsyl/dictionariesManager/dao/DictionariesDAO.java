package cn.sqkj.nsyl.dictionariesManager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sqkj.nsyl.db.po.NsDictionaries;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("dictionariesDAO")
public class DictionariesDAO extends PageDAO {
	/**
	 * 获取数据字典总记录数
	 * @param sql
	 * @param params
	 * @return
	 */
	public int findBaseDataCount(String sql, List params){
		return DBUtil.getDBUtilByRequest().queryCountBySQL(sql,params);
	}
	
	/**
	 * 获取数据字典数据集
	 * @param sql
	 * @param params
	 * @param pageBean
	 * @return
	 */
	public List<NsDictionaries> findDictionariesPage(String sql,List params, PageBean pageBean) {
		List<NsDictionaries> pageData = this.query(sql, params, pageBean);
		return pageData;
	}
}
