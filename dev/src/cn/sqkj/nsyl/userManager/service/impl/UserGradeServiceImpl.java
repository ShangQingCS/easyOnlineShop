package cn.sqkj.nsyl.userManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.userManager.dao.UserGradeDAO;
import cn.sqkj.nsyl.userManager.pojo.NsUser;
import cn.sqkj.nsyl.userManager.pojo.NsUserGrade;
import cn.sqkj.nsyl.userManager.service.IUserGradeService;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.util.DateUtils;

@SuppressWarnings({"rawtypes","unchecked"})
@Service("userGradeService")
public class UserGradeServiceImpl implements IUserGradeService{
	
	@Resource(name="userGradeDAO")
	private UserGradeDAO userGradeDAO;
	
	public PageBean queryUserGradeList(PageBean pageBean) throws Exception {
		StringBuffer sql = new StringBuffer("select t.id,t.grade_name,t.grade_fc_level,t.grade_fc_level1,t.grade_fc_level2,"
				+ "t.grade_fc_level3,t.grade_balance,t.grade_tx_balance,t.create_time,t.option_adminid,t.option_time,"
				+ "t.option_remark from ns_user_grade t ");
		List params = new ArrayList();
		//查询总计路数
		int total = userGradeDAO.findNsUserGradeCount(sql.toString(), params);
		//查询数据集
		List<NsUserGrade> list = userGradeDAO.findNsUserGradePage(sql.toString(), params, pageBean);
		pageBean.setTotal(total);
		pageBean.setPageData(list);
		return pageBean;
	}

	public NsUserGrade queryUserGradeById(Long id) throws Exception {
		if(id!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsUserGrade nsUserGrade = (NsUserGrade) db.getSession().get(NsUserGrade.class, id);
			return nsUserGrade;
		}
		return null;
	}

	public int updateUserGrade(NsUserGrade nug) throws Exception {
		DBUtil db = DBUtil.getDBUtilByRequest();
		StringBuffer sql = new StringBuffer(" update ns_user_grade t set  ");
		if(!nug.getGrade_fc_level().isNaN()){
			sql.append(" t.grade_fc_level =' "+nug.getGrade_fc_level()+"' ,");
		}
		if(!nug.getGrade_balance().isNaN()){
			sql.append(" t.grade_balance =' "+nug.getGrade_balance()+"' ,");
		}
		if(!nug.getGrade_tx_balance().isNaN()){
			sql.append(" t.grade_tx_balance =' "+nug.getGrade_tx_balance()+"' ,");
		}
		
		if(StringUtils.isNotBlank(nug.getOption_remark())){
			sql.append(" t.option_remark =' "+nug.getOption_remark()+"' ,");
		}
		
		sql.append(" t.option_time = '"+DateUtils.toDate(new Date())+"'");//修改时间
		sql.append("  where t.id = "+nug.getId());
		int result = db.getSession().createSQLQuery(sql.toString()).executeUpdate();
		return result;
	}
	
	public int updateUserGrade2(NsUserGrade nug) throws Exception {
		DBUtil db = DBUtil.getDBUtilByRequest();
		StringBuffer sql = new StringBuffer(" update ns_user_grade t set  ");
		if(!nug.getGrade_fc_level1().isNaN()){
			sql.append(" t.grade_fc_level1 =' "+nug.getGrade_fc_level1()+"' ,");
		}
		if(!nug.getGrade_fc_level2().isNaN()){
			sql.append(" t.grade_fc_level2 =' "+nug.getGrade_fc_level2()+"' ,");
		}
		if(!nug.getGrade_fc_level3().isNaN()){
			sql.append(" t.grade_fc_level3 =' "+nug.getGrade_fc_level3()+"' ,");
		}
		sql.append(" t.option_time = '"+DateUtils.toDate(new Date())+"'");//修改时间
		int result = db.getSession().createSQLQuery(sql.toString()).executeUpdate();
		return result;
	}

}
