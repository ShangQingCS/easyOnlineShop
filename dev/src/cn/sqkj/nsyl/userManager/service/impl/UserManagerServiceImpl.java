package cn.sqkj.nsyl.userManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.userManager.dao.UserManagerDAO;
import cn.sqkj.nsyl.userManager.pojo.NsUser;
import cn.sqkj.nsyl.userManager.pojo.NsUserPurse;
import cn.sqkj.nsyl.userManager.service.IUserManagerService;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.DateUtils;
import framework.util.MD5Util;

@SuppressWarnings({"rawtypes","unchecked"})
@Service("userManagerService")
public class UserManagerServiceImpl implements IUserManagerService{
	
	@Resource(name="userManagerDAO")
	private UserManagerDAO userManagerDAO;

	public PageBean queryUserList(PageBean pageBean) throws Exception {
		StringBuffer sql = new StringBuffer("select t.id,t.user_name,t.nick_name,t.user_sex,t.identity_card_validity,t.login_pwd,");
		sql.append(" t.true_name, t.user_phone,t.identity_card,t.create_time,t.option_remark,t.user_ky_balance,t.user_fx_balance,t.user_dj_balance,t.user_mail,");
		sql.append(" t.user_jf_balance, t.user_status,t.identity_issuing,t.identity_status,t.tixian_status,t.user_pid from ns_user t where 1=1 ");
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
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("identity_status"))) {
				sql.append(" and t.identity_status like ? ");
				params.add("%"+pageBean.getQueryParams().get("identity_status")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("tixian_status"))) {
				sql.append(" and t.tixian_status like ? ");
				params.add("%"+pageBean.getQueryParams().get("tixian_status")+"%");
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

	public int updateNsUser(NsUser nsUser) throws Exception {
		DBUtil db = DBUtil.getDBUtilByRequest();
		StringBuffer sql = new StringBuffer(" update ns_user t set  ");
		if(StringUtils.isNotBlank(nsUser.getUser_name())){
			sql.append(" t.user_name = '"+nsUser.getUser_name()+"' ,");
		}
		if(StringUtils.isNotBlank(nsUser.getNick_name())){
			sql.append(" t.nick_name = '"+nsUser.getNick_name()+"' ,");
		}
		if(StringUtils.isNotBlank(nsUser.getUser_phone())){
			sql.append(" t.user_phone = '"+nsUser.getUser_phone()+"', ");
		}
		if(StringUtils.isNotBlank(nsUser.getUser_mail())){
			sql.append(" t.user_mail = '"+nsUser.getUser_mail()+"' ,");
		}
		if(StringUtils.isNotBlank(nsUser.getUser_sex())){
			sql.append(" t.user_sex = "+nsUser.getUser_sex()+" , ");
		}
		if(StringUtils.isNotBlank(nsUser.getIdentity_card())){
			sql.append(" t.identity_card = '"+nsUser.getIdentity_card()+"', ");
		}
		if(nsUser.getIdentity_card_validity()!=null){
			sql.append(" t.identity_card_validity = '"+DateUtils.toDate(nsUser.getIdentity_card_validity())+"', ");
		}
		if(StringUtils.isNotBlank(nsUser.getIdentity_issuing())){
			sql.append(" t.identity_issuing = '"+nsUser.getIdentity_issuing()+"' ,");
		}
		if(StringUtils.isNotBlank(nsUser.getIdentity_status())){
			sql.append(" t.identity_status = "+nsUser.getIdentity_status()+" ,");
		}
		if(StringUtils.isNotBlank(nsUser.getLogin_pwd())){
			if(nsUser.getLogin_pwd().equals("12345678")){
				sql.append(" t.login_pwd = "+MD5Util.MD5(nsUser.getLogin_pwd())+" ,");
			}
		}
		if(StringUtils.isNotBlank(nsUser.getTixian_status())){
			sql.append(" t.tixian_status ='"+nsUser.getTixian_status()+"' ,");
		}
		if(nsUser.getUser_dj_balance()!=null&&!nsUser.getUser_dj_balance().isNaN()){
			sql.append(" t.user_dj_balance ='"+nsUser.getUser_dj_balance()+"' ,");
		}
		if(nsUser.getUser_fx_balance()!=null&&!nsUser.getUser_fx_balance().isNaN()){
			sql.append(" t.user_fx_balance ='"+nsUser.getUser_fx_balance()+"' ,");
		}
		if(nsUser.getUser_jf_balance()!=null&&!nsUser.getUser_jf_balance().isNaN()){
			sql.append(" t.user_jf_balance ='"+nsUser.getUser_jf_balance()+"' ,");
		}
		
		if(StringUtils.isNotBlank(nsUser.getOption_remark())){
			sql.append(" t.option_remark =' "+nsUser.getOption_remark()+"' ,");
		}
		sql.append(" t.option_time = '"+DateUtils.toDate(new Date())+"'");
		sql.append("  where t.id = "+nsUser.getId());
		int result = db.getSession().createSQLQuery(sql.toString()).executeUpdate();
		return result;
	}

	public NsUser queryUserListByCondition(String user_name,String true_name,String user_phone,String identity_card) throws Exception {
		StringBuffer sql = new StringBuffer("select t.id,t.user_name,t.true_name,t.user_ky_balance,t.user_fx_balance,t.user_jf_balance,"
				+ "t.identity_card,t.identity_card_validity,t.user_status from ns_user t where 1=1");
			if(StringUtils.isNotBlank(user_name)) {
				sql.append(" and t.user_name = '"+user_name+"'");
			}
			if(StringUtils.isNotBlank(true_name)) {
				sql.append(" and t.true_name = '"+true_name+"'");
			}
			if(StringUtils.isNotBlank(user_phone)) {
				sql.append(" and t.user_phone = '"+user_phone+"'");
			}
			if(StringUtils.isNotBlank(identity_card)) {
				sql.append(" and t.identity_card = '"+identity_card+"'");
			}
		
		DBUtil db = DBUtil.getDBUtilByRequest();
		List<NsUser> list = new ArrayList<NsUser>();
		list = db.getSession().createSQLQuery(sql.toString()).list(); // 执行查询
		NsUser nu = new NsUser();
		for(Iterator iterator = list.iterator();iterator.hasNext();){  
			 //每个集合元素都是一个数组，数组元素师person_id,person_name,person_age三列值  
            Object[] objects = (Object[]) iterator.next();  
            nu.setId(Long.valueOf(objects[0].toString()));
            nu.setUser_name(objects[1].toString());
            nu.setTrue_name(objects[2].toString());
            nu.setUser_ky_balance(Double.valueOf(objects[3].toString()));
            nu.setUser_fx_balance(Double.valueOf(objects[4].toString()));
			nu.setUser_jf_balance(Double.valueOf(objects[5].toString()));
			nu.setIdentity_card(objects[6].toString());
			nu.setIdentity_card_validity(DateUtils.parseDatetime(objects[7].toString()));
			nu.setUser_status(objects[8].toString());
		}
		return nu;
	}

	public PageBean queryNsUserTeamList(PageBean pageBean,String id) throws Exception {
		List params = new ArrayList();
		StringBuffer sql = new StringBuffer(" select distinct *  from " +
				"(select *  from ns_user t where t.id= "+id+
				" union  select n2.* from ns_user  n1,ns_user  n2 where n1.id= "+id+" and n2.user_pid=n1.id " +
				" union select n3.* from ns_user  n3,( select n2.* from ns_user  n1,ns_user  n2 " +
				" where n1.id="+id+" and n2.user_pid=n1.id ) " +
				" t2 where t2.id=n3.user_pid) a ");
		//查询总计路数
		int total = userManagerDAO.findNsUserCount(sql.toString(), params);
		//查询数据集
		List<NsUser> list = userManagerDAO.findNsUserPage(sql.toString(), params, pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}

	public PageBean queryNsUserCount(PageBean pageBean)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(" select DISTINCT t1.true_name,t1.user_name,t1.user_sex,"
				+ " t1.user_phone,t1.identity_card,t1.create_time,t1.user_status,t1.identity_status,"
				+ " (select sum(t2.trade_amount) dqye from ns_user_purse t2 where t2.purse_type = 0) dqye,"
				+ " (select sum(t2.trade_amount) jf from ns_user_purse t2 where t2.purse_type = 2) jf,"
				+ " (select sum(t2.trade_amount) fhje from ns_user_purse t2 where t2.purse_type = 1) fhje,"
				+ " (select sum(t2.trade_amount) cz from ns_user_purse t2 where t2.option_type = 0) cz,"
				+ " (select sum(t2.trade_amount) xf from ns_user_purse t2 where t2.option_type = 1) xf "
				+ " from ns_user t1,ns_user_purse t2 where t1.id = t2.user_id " );
		List params = new ArrayList();
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("identity_status"))) {
				sql.append(" and t1.identity_status = ? ");
				params.add(pageBean.getQueryParams().get("identity_status"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("user_status"))) {
				sql.append(" and t1.user_status = ? ");
				params.add(pageBean.getQueryParams().get("user_status"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("startCreateTime"))) {
				sql.append(" and t1.create_time >= str_to_date(?,'%Y-%m-%d %H:%i:%s')   ");
				params.add(pageBean.getQueryParams().get("startCreateTime"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("endCreateTime"))) {
				sql.append(" and t1.create_time <= str_to_date(?,'%Y-%m-%d %H:%i:%s')   ");
				params.add(pageBean.getQueryParams().get("endCreateTime"));
			}
		}
		
		sql.append(" GROUP BY t1.user_name ");
		//查询总计路数
		DBUtil db = DBUtil.getDBUtilByRequest();
		String mysql = sql.toString().toUpperCase();
		mysql = "SELECT COUNT(1) ROW_COUNT FROM ("+mysql+") a";
		List list1 = db.queryBySQL(mysql, params, 0, 0);
		int total = Integer.parseInt(((Map)list1.get(0)).get("rowCount").toString());
		//查询数据集
		List<NsUser> list = userManagerDAO.findNsUserPage(sql.toString(), params, pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}

	
	
	

}
