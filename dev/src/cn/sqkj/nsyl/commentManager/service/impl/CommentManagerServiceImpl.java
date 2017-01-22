package cn.sqkj.nsyl.commentManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.sqkj.nsyl.commentManager.dao.NsCommentDAO;
import cn.sqkj.nsyl.commentManager.pojo.NsComment;
import cn.sqkj.nsyl.commentManager.service.ICommentManagerService;
import framework.bean.PageBean;
import framework.db.DBUtil;

/**
 * @author yangchaowen
 * 2016年11月03日
 * 评论维护Service实现类
 */
@SuppressWarnings({"rawtypes","unchecked"})
@Service("commentManagerService")
public class CommentManagerServiceImpl implements ICommentManagerService {
	@Resource(name="commentDAO")
	private NsCommentDAO commentDAO;
	
	/**
	 * 查询评论列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PageBean queryCommentList(PageBean pageBean) throws Exception {
		StringBuffer sql = new StringBuffer(" select t.*,t1.goods_code,( select t1.user_name from ns_user t1 where t1.id=t.userid ) user_name,t1.gname,t2.cate_name as category_name,t3.cate_name as kind_name,t4.cate_name as brand_name from ns_comment t  ");
		sql.append(" left join ns_goods t1 on t.goodsid=t1.id ");
		sql.append(" left join ns_goods_category t2 on t1.category=t2.id ");
		sql.append(" left join ns_goods_category t3 on t1.kind=t3.id ");
		sql.append(" left join ns_goods_category t4 on t1.brand=t4.id ");
		sql.append(" where t.flag = 1 ");
		List params = new ArrayList();
		if(pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("goods_code"))) {
				sql.append(" and t1.goods_code = ? ");
				params.add(pageBean.getQueryParams().get("goods_code"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("gname"))) {
				sql.append(" and t1.gname like ? ");
				params.add("%"+pageBean.getQueryParams().get("gname")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("category"))) {
				sql.append(" and t1.category = ? ");
				params.add(pageBean.getQueryParams().get("category"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("kind"))) {
				sql.append(" and t1.kind = ? ");
				params.add(pageBean.getQueryParams().get("kind"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("brand"))) {
				sql.append(" and t1.brand = ? ");
				params.add(pageBean.getQueryParams().get("brand"));
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("userid"))) {
				sql.append(" and t.userid like ? ");
				params.add("%"+pageBean.getQueryParams().get("userid")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("user_name"))) {
				sql.append(" and t.userid in ( select id from ns_user where user_name like ? ) ");
				params.add("%"+pageBean.getQueryParams().get("user_name")+"%");
			}
			if(StringUtils.isNotBlank(pageBean.getQueryParams().get("comment"))) {
				sql.append(" and t.comment like ? ");
				params.add("%"+pageBean.getQueryParams().get("comment")+"%");
			}
		}
		
		//查询总计路数
		int total = commentDAO.findCommentCount(sql.toString(), params);
		//查询数据
		List<NsComment> goods = commentDAO.findCommentPage(sql.toString(), params, pageBean);
		
		pageBean.setTotal(total);
		pageBean.setPageData(goods);
		return pageBean;
	}
	
	public NsComment queryCommentById(Long id) throws Exception {
		if(id!=null) {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsComment nc = (NsComment) db.getSession().get(NsComment.class, id);
			return nc;
		}
		return null;
	}
}