package cn.sqkj.nsyl.dictionariesManager.service;

import cn.sqkj.nsyl.db.po.NsDictionaries;
import framework.bean.PageBean;

public interface IDictionariesManagerService {
	
	/**
	 * 查询数据字典列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryBaseDataList(PageBean pageBean) throws Exception;
	
	/**
	 * 根据ID查询数据字典
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NsDictionaries queryBaseDataById(Long id) throws Exception;
	
	/**
	 * 保存数据字典
	 * @param nsDictionaries
	 * @return
	 * @throws Exception
	 */
	public NsDictionaries saveNsDictionaries(NsDictionaries nsDictionaries)throws Exception;
	
	/**
	 * 删除数据字典
	 * @param nsDictionaries
	 * @return
	 * @throws Exception
	 */
	public String deleteNsDictionaries(NsDictionaries nsDictionaries) throws Exception;
	
	
}
