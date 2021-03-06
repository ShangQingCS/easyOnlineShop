package framework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import framework.dao.SysCodeDAO;
import framework.db.pojo.SysCode;
import framework.service.ISysCodeService;

@Service("sysCodeService")
public class SysCodeServiceImpl implements ISysCodeService {

	@Resource(name="sysCodeDAO")
	private SysCodeDAO sysCodeDAO;
	
	public List<SysCode> querySysCodeByType(String type) throws Exception {
		return sysCodeDAO.querySysCodeByType(type);
	}
}