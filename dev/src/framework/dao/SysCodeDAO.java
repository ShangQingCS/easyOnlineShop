package framework.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import framework.config.SysDict;
import framework.db.DBUtil;
import framework.db.pojo.SysCode;

@Repository("sysCodeDAO")
public class SysCodeDAO {
	@SuppressWarnings("unchecked")
	public List<SysCode> querySysCodeByType(String type) throws Exception {
		DBUtil db = DBUtil.getDBUtilByRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("flag", SysDict.FLAG_ACT);
		return db.queryByPojo(SysCode.class, params);
	}
}