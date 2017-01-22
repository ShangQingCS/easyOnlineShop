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
	 * 查询用户团队列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryNsUserTeamList(PageBean pageBean,String id) throws Exception;
	
	/**
	 * 查询用户团队列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryNsUserCount(PageBean pageBean) throws Exception;
	
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NsUser queryUserById(Long id) throws Exception;
	
	
	/**
	 * 查询用户列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public NsUser queryUserListByCondition(String user_name,String true_name,String user_phone,String identity_card) throws Exception;
	
	
	/**
	 * 修改用户信息
	 * @return
	 * @throws Exception
	 */
	public int updateNsUser(NsUser nsUser) throws Exception;
	
	
	/**
	 * 查询用户体现列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryUserTiXianList(PageBean pageBean) throws Exception;
}
