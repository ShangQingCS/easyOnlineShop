package cn.sqkj.nsyl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.sqkj.nsyl.db.po.NsDictionaries;
import framework.db.DBUtil;

@SuppressWarnings("unchecked")
@Repository("dictionariesDAO")
public class NsDictionariesDAO {
	public List<NsDictionaries> queryByType(String type) throws Exception {
		DBUtil db = DBUtil.getDBUtilByRequest();
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("type", type);
		params.put("isuse", "0");
		return db.queryByPojo(NsDictionaries.class, params);
	}
}