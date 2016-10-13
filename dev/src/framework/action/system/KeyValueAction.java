package framework.action.system;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import framework.action.FenyeBase;
import framework.db.DBUtil;
import framework.db.pojo.TXtKeyValue;

public class KeyValueAction  extends FenyeBase{
	private final static Logger log = Logger.getLogger(KeyValueAction.class);
	private final static String SQL_ALL = "select kv_key, kv_value,lr_sj from T_xt_KEY_VALUE ";
	private final static String SQL = "select kv_key, kv_value,lr_sj from T_xt_KEY_VALUE where kv_key like ? or kv_value like ?  ";
	private TXtKeyValue kv;
	
	public String list(){
		String sql = SQL_ALL;
		ArrayList params = new ArrayList(2);
		if(this.queryParams!=null && !"".equals(this.queryParams.get("key"))){
			params.add("%"+this.queryParams.get("key")+"%");
			params.add("%"+this.queryParams.get("key")+"%");
			sql = SQL;
		}
		this.query(sql+" order by lr_sj", params);
		return "success";
	}
	
	public String query(){
		if(this.queryParams!=null && !"".equals(this.queryParams.get("kv_key"))){
			ArrayList params = new ArrayList(1);
			String sql = SQL_ALL+" where kv_key=? ";
			params.add(this.queryParams.get("kv_key"));
			this.query(sql, params);
		}
		return "success";
	}
	
	public String save(){
		DBUtil.getDBUtilByRequest().saveOrUpdate(kv);
		return "success";
	}
	
	public String delete(){
		String[] ids = kv.getKvKey().split(",");
		for(String id : ids){
			TXtKeyValue tmp = new TXtKeyValue();
			tmp.setKvKey(id);
			DBUtil.getDBUtilByRequest().delete(tmp);
		}
		
		return "success";
	}

	public TXtKeyValue getKv() {
		return kv;
	}

	public void setKv(TXtKeyValue kv) {
		this.kv = kv;
	}
	
	
	
}

