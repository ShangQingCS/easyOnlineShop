package cn.sqkj.nsyl.userManager.service;

import cn.sqkj.nsyl.userManager.pojo.NsUserGrade;
import framework.bean.PageBean;

public interface IUserGradeService {
	/**
	 * 查询用户列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryUserGradeList(PageBean pageBean) throws Exception;
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NsUserGrade queryUserGradeById(Long id) throws Exception;
	
	
	
	/**
	 * 修改用户信息
	 * @return
	 * @throws Exception
	 */
	public int updateUserGrade(NsUserGrade nug) throws Exception;
	
	public int updateUserGrade2(NsUserGrade nug) throws Exception;
}
