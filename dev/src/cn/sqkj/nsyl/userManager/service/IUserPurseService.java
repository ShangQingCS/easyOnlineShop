package cn.sqkj.nsyl.userManager.service;

import cn.sqkj.nsyl.userManager.pojo.NsUserPurse;
import framework.bean.PageBean;

public interface IUserPurseService {
	
	/**
	 * 查询钱包记录列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryUserPurseList(PageBean pageBean) throws Exception;
	
	/**
	 * 查询钱包记录列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryUserPurseListCount(PageBean pageBean) throws Exception;
	
	/**
	 * 查询钱包记录列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryUserPurseList(PageBean pageBean,String purse_type) throws Exception;
	
	/**
	 * 根据用户ID查询冻结记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NsUserPurse queryUserPurseById(Long userID) throws Exception;
	
	
	
	/**
	 * 修改钱包记录信息
	 * @return
	 * @throws Exception
	 */
	public int updateUserPurse(NsUserPurse nup) throws Exception;
	
	
	
}
