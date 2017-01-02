package framework.config;

/**
 * @author YANGCW
 * 系统常量词典类
 */
public class SysDict {
	public static final String INIT_PWD = "123456";
	
	//数据库记录状态：有效，1，无效，0
	public static final String FLAG_ACT = "1";
	public static final String FLAG_HIS = "0";
	
	public static final String SEX_MALE = "0";
	public static final String SEX_FEMALE = "1";
	
	//有效，0，无效，1
	public static final String ISUSE_YES = "0";
	public static final String ISUSE_NO = "1";
	
//	//冻结，0， 非冻结，1   默认1非冻结
//	public static final String STATUS_YES = "0";
//	public static final String STATUS_NO = "1";
//	
//	//注销，0， 非注销，1   默认1非注销
//	public static final String FLAG_YES = "0";
//	public static final String FLAG_NO = "1";
	
	//0注销1正常2冻结
	public static final String USER_STATUS_LAYOUT = "0";
	public static final String USER_STATUS = "1";
	public static final String USER_STATUS_FREEZE = "2";
	
}
