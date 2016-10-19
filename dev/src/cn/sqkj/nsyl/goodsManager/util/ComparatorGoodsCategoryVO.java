package cn.sqkj.nsyl.goodsManager.util;

import java.util.Comparator;
import java.util.Map;

public class ComparatorGoodsCategoryVO implements Comparator<Object> {

	public int compare(Object arg0, Object arg1) {
		Map item_0 = (Map) arg0;
		Map item_1 = (Map) arg1;
		int i1 = Integer.parseInt(((Map)item_1.get("attributes")).get("cateOrder").toString());
		int i0 = Integer.parseInt(((Map)item_0.get("attributes")).get("cateOrder").toString());
		//jdk1.6
		/*if (i1 > i0) {
			return 0;
		} else {
			return 1;
		}*/
		//jdk1。7
		return i0-i1;
	}
}