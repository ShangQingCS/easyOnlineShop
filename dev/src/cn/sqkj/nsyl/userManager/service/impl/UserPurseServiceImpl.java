package cn.sqkj.nsyl.userManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.userManager.dao.UserPurseDAO;
import cn.sqkj.nsyl.userManager.pojo.NsUserPurse;
import cn.sqkj.nsyl.userManager.service.IUserPurseService;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.helper.RequestHelper;
import framework.util.DateUtils;

@SuppressWarnings({"rawtypes","unchecked"})
@Service("userPurseService")
public class UserPurseServiceImpl implements IUserPurseService{
	
	@Resource(name="userPurseDAO")
	private UserPurseDAO userPurseDAO;
	
	public PageBean queryUserPurseList(PageBean pageBean) throws Exception {
		StringBuffer sql = new StringBuffer("select t.id,t.trade_type,t.trade_sn,t.trade_state,t.option_type,t.purse_type,"
				+ "t.trade_amount,t.option_time from ns_user_purse t where 1=1 ");
		List params = new ArrayList();
//		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
//			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("purse_type"))) {
//				sql.append(" and t.purse_type = ? ");
//				params.add(pageBean.getQueryParams().get("purse_type"));
//			}
//		}
		//查询总计路数
		int total = userPurseDAO.findNsUserPurseCount(sql.toString(), params);
		//查询数据集
		List<NsUserPurse> list = userPurseDAO.findNsUserPursePage(sql.toString(), params, pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}
	
	
	public NsUserPurse queryUserPurseById(Long userID) throws Exception {
		StringBuffer sql = new StringBuffer("select t.id,t.trade_type,t.trade_amount,t.trade_sn,t.trade_state,t.option_type,t.user_id,t.user_amount,"
				+ "t.purse_type,t.pay_account,t.pay_open_bank,t.purse_status,t.create_time,t.option_time,t.option_adminid,t.option_adminname,"
				+ "t.option_remark from ns_user_purse t ");
		sql.append(" where t.user_id = "+userID+" and t.trade_state = 1 ");
		DBUtil db = DBUtil.getDBUtilByRequest();
		List<NsUserPurse> list = new ArrayList<NsUserPurse>();
		list = db.getSession().createSQLQuery(sql.toString()).list(); // 执行查询
		NsUserPurse nup = new NsUserPurse();
		for(Iterator iterator = list.iterator();iterator.hasNext();){  
            //每个集合元素都是一个数组，数组元素师person_id,person_name,person_age三列值  
            Object[] objects = (Object[]) iterator.next();  
            nup.setId(Long.valueOf(objects[0].toString()));
			nup.setTrade_type(Integer.valueOf(objects[1].toString()));
			nup.setTrade_amount(Double.valueOf(objects[2].toString()));
			nup.setTrade_sn(objects[3].toString());
			nup.setTrade_state(Integer.valueOf(objects[4].toString()));
			nup.setOption_type(Integer.valueOf(objects[5].toString()));
			nup.setUser_id(Long.valueOf(objects[6].toString()));
			nup.setUser_amount(Double.valueOf(objects[7].toString()));
			nup.setPurse_type(Integer.valueOf(objects[8].toString()));
			nup.setPay_account(objects[9].toString());
			nup.setPay_open_bank(objects[10].toString());
			nup.setPurse_status(Integer.valueOf(objects[11].toString()));
			nup.setCreate_time(DateUtils.parseDate(objects[12].toString()));
			nup.setOption_time(DateUtils.getDate());
			nup.setOption_adminid(Long.valueOf(objects[14].toString()));
			nup.setOption_adminname(objects[15].toString());
			nup.setOption_remark(objects[16].toString());
        }  
		return nup;
	}

	public int updateUserPurse(NsUserPurse nup) throws Exception {
		DBUtil db = DBUtil.getDBUtilByRequest();
		StringBuffer sql = new StringBuffer(" update ns_user_purse t set  ");
		if(StringUtils.isNotBlank(nup.getOption_remark())){
			sql.append(" t.option_remark =' "+nup.getOption_remark()+"' ,");
		}
		if(!nup.getTrade_state().equals("")){
			sql.append(" t.trade_state ='"+nup.getTrade_state()+"' ,");
		}
		sql.append(" t.option_time = '"+DateUtils.toDate(new Date())+"'");
		
		sql.append("  where t.id = "+nup.getId());
		int result = db.getSession().createSQLQuery(sql.toString()).executeUpdate();
		return result;
	}


	public PageBean queryUserPurseList(PageBean pageBean, String purse_type)
			throws Exception {
		StringBuffer sql = new StringBuffer("select t.id,t.trade_type,t.trade_sn,t.trade_state,t.option_type,t.purse_type,"
				+ "t.trade_amount,t.option_time from ns_user_purse t where 1=1 ");
		List params = new ArrayList();
		sql.append(" and t.purse_type = ? ");
		params.add(purse_type);
		//查询总计路数
		int total = userPurseDAO.findNsUserPurseCount(sql.toString(), params);
		//查询数据集
		List<NsUserPurse> list = userPurseDAO.findNsUserPursePage(sql.toString(), params, pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}


	public PageBean queryUserPurseListCount(PageBean pageBean) throws Exception {
		StringBuffer sql = new StringBuffer(" select t1.user_name,t1.true_name,t2.purse_type,t2.trade_type,t2.option_type,t2.trade_amount,"
				+ "t2.option_time,t1.user_status from ns_user t1,ns_user_purse t2 where t1.id = t2.user_id  ");
		List params = new ArrayList();
		
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("trade_type"))) {
				sql.append(" and t2.trade_type = ? ");
				params.add(pageBean.getQueryParams().get("trade_type"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("purse_type"))) {
				sql.append(" and t2.purse_type = ? ");
				params.add(pageBean.getQueryParams().get("purse_type"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("option_type"))) {
				sql.append(" and t2.option_type = ? ");
				params.add(pageBean.getQueryParams().get("option_type"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("startCreateTime"))) {
				sql.append(" and t2.option_time >= str_to_date(?,'%Y-%m-%d %H:%i:%s')   ");
				params.add(pageBean.getQueryParams().get("startCreateTime"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("endCreateTime"))) {
				sql.append(" and t2.option_time <= str_to_date(?,'%Y-%m-%d %H:%i:%s')   ");
				params.add(pageBean.getQueryParams().get("endCreateTime"));
			}
		}
		//查询总计路数
		int total = userPurseDAO.findNsUserPurseCount(sql.toString(), params);
		//查询数据集
		List<NsUserPurse> list = userPurseDAO.findNsUserPursePage(sql.toString(), params, pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}
	
	
	
	
	
}
