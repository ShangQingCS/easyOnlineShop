package cn.sqkj.nsyl.userManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.db.po.NsDictionaries;
import cn.sqkj.nsyl.userManager.dao.UserManagerDAO;
import cn.sqkj.nsyl.userManager.pojo.NsUser;
import cn.sqkj.nsyl.userManager.service.IUserManagerService;
import framework.bean.PageBean;
import framework.db.DBUtil;

@SuppressWarnings({"rawtypes","unchecked"})
@Service("userManagerService")
public class UserManagerServiceImpl implements IUserManagerService{
	
	@Resource(name="userManagerDAO")
	private UserManagerDAO userManagerDAO;

	public PageBean queryUserList(PageBean pageBean) throws Exception {
		StringBuffer sql = new StringBuffer("select t.id,t.userid,t.nickname,t.username,t.`name`,t.idcard,t.flag,t.`status`,t.phone from ns_user t");
		sql.append(" where 1=1 ");
		List params = new ArrayList();
		//params.add(SysDict.ISUSE_YES);//数据有效
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("userid"))) {
				sql.append(" and t.userid like ? ");
				params.add("%"+pageBean.getQueryParams().get("userid")+"%");
			}
			
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("nickname"))) {
				sql.append(" and t.nickname like ? ");
				params.add("%"+pageBean.getQueryParams().get("nickname")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("phone"))) {
				sql.append(" and t.phone like ? ");
				params.add("%"+pageBean.getQueryParams().get("phone")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("name"))) {
				sql.append(" and t.name like ? ");
				params.add("%"+pageBean.getQueryParams().get("name")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("flag"))) {
				sql.append(" and t.flag like ? ");
				params.add("%"+pageBean.getQueryParams().get("flag")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("status"))) {
				sql.append(" and t.status like ? ");
				params.add("%"+pageBean.getQueryParams().get("status")+"%");
			}
		}
		//查询总计路数
		int total = userManagerDAO.findNsUserCount(sql.toString(), params);
		//查询数据集
		List<NsUser> list = userManagerDAO.findNsUserPage(sql.toString(), params, pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}

	public NsUser queryUserById(Long id) throws Exception {
		if(id!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsUser nsuser = (NsUser) db.getSession().get(NsUser.class, id);
			return nsuser;
		}
		return null;
	}

	public NsUser updateUserById(Long id, String tag) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
