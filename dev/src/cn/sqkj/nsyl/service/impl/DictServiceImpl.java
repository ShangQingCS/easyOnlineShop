package cn.sqkj.nsyl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.dao.DictDAO;
import cn.sqkj.nsyl.db.po.NsDictionaries;
import cn.sqkj.nsyl.service.IDictService;

@Service("dictService")
public class DictServiceImpl implements IDictService {

	@Resource(name="dictDAO")
	private DictDAO dictDAO;

	public List<NsDictionaries> queryDictByType(String type) throws Exception {
		return dictDAO.queryByType(type);
	}
}