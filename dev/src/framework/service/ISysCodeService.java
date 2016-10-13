package framework.service;

import java.util.List;

import framework.db.pojo.SysCode;

public interface ISysCodeService {
	public List<SysCode> querySysCodeByType(String type) throws Exception;
}