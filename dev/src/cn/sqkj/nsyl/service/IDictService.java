package cn.sqkj.nsyl.service;

import java.util.List;

import cn.sqkj.nsyl.db.po.NsDictionaries;

public interface IDictService {
	public List<NsDictionaries> queryDictByType(String type) throws Exception;
}