package cn.sqkj.nsyl.commentManager.service;

import cn.sqkj.nsyl.commentManager.pojo.NsComment;
import framework.bean.PageBean;

public interface ICommentManagerService {
	
	/**
	 * 查询评论列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryCommentList(PageBean pageBean) throws Exception;
	
	public NsComment queryCommentById(Long id) throws Exception;
}