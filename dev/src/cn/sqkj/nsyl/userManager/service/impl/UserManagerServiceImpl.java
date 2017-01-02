package cn.sqkj.nsyl.userManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

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
		//StringBuffer sql = new StringBuffer("select t.id,t.user_name, t.true_name, t.user_phone,t.identity_card,t.create_time,");
		//sql.append(" t.user_ky_balance,t.user_fx_balance,t.user_jf_balance, t.user_status from ns_user t where 1=1 ");
		StringBuffer sql = new StringBuffer("select t.id,t.user_name,t.nick_name,t.user_sex,t.identity_card_validity,t.login_pwd,");
		sql.append(" t.true_name, t.user_phone,t.identity_card,t.create_time,t.option_remark,t.user_ky_balance,t.user_fx_balance,t.user_mail,");
		sql.append(" t.user_jf_balance, t.user_status,t.identity_issuing,t.identity_status from ns_user t where 1=1 ");
		List params = new ArrayList();
		//params.add(SysDict.ISUSE_YES);//数据有效
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("id"))) {
				sql.append(" and t.id = ? ");
				params.add(pageBean.getQueryParams().get("id"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("user_name"))) {
				sql.append(" and t.user_name like ? ");
				params.add("%"+pageBean.getQueryParams().get("user_name")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("true_name"))) {
				sql.append(" and t.true_name like ? ");
				params.add("%"+pageBean.getQueryParams().get("true_name")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("user_phone"))) {
				sql.append(" and t.user_phone like ? ");
				params.add("%"+pageBean.getQueryParams().get("user_phone")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("identity_card"))) {
				sql.append(" and t.identity_card like ? ");
				params.add("%"+pageBean.getQueryParams().get("identity_card")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("user_status"))) {
				sql.append(" and t.user_status like ? ");
				params.add("%"+pageBean.getQueryParams().get("user_status")+"%");
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

	public NsUser updateNsUser(NsUser nsUser) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
