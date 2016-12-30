package cn.sqkj.nsyl.userManager.service;

import cn.sqkj.nsyl.userManager.pojo.NsUser;
import framework.bean.PageBean;

public interface IUserManagerService {
	
	/**
	 * 查询用户列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryUserList(PageBean pageBean) throws Exception;
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NsUser queryUserById(Long id) throws Exception;
	
	
	
	/**
	 * 修改用户状态
	 * @param id
	 * @param tag
	 * @return
	 * @throws Exception
	 */
	public NsUser updateUserById(Long id,String tag) throws Exception;
}
