package com.hundsun.oauth.repositoryimpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hundsun.oauth.domain.Pagination;
import com.hundsun.oauth.repository.Repository;
import com.hundsun.oauth.rowmapper.UserRowMapper;

public class RepositoryImpl<T> implements Repository<T> {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public RepositoryImpl() {
		// 获取持久化对象的类型
		Type type = getClass().getGenericSuperclass();
		Type[] types = ((ParameterizedType) type).getActualTypeArguments();
		this.persistentClass = (Class<T>) types[0];
	}

	/**
	 * 采用springJDBC + MYSQL 分页
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<T> paginationBySql(String countSql, String pageListSql, Pagination<T> pagination) {

		int total = 0;
		List<T> pageList = null;
		total = jdbcTemplate.queryForObject(countSql, new Object[] {}, Integer.class);
		if (total == 0) {
			pageList = Collections.<T>emptyList();
		} else {
			jdbcTemplate.execute(pageListSql);
			int startIndex = pagination.getStartIndex();
			int pageSize = pagination.getPageSize();
			if (startIndex >= 0 && pageSize >= 0) {
				pageListSql += " limit " + startIndex + ", " + pageSize;
			}
			pageList = jdbcTemplate.query(pageListSql, new Object[] {}, getRowMapper(persistentClass));
		}
		pagination.setTotal(total);
		pagination.setPageList(pageList);
		return pagination;
	}

	/**
	 * 
	 * 如果有新的对象需要分页，需要在这里写出对应的mapper 映射
	 * 
	 * @param t
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private RowMapper getRowMapper(Class<T> t) {
		if (t.getName().equals("com.hundsun.oauth.domain.User")) {
			return new UserRowMapper();
		}
		return null;
	}
	
	//采用hibernate 分页
//	@SuppressWarnings("unchecked")
//	public Pagination<T> paginationByHql(String countHql, String pageListHql, Pagination<T> pagination) {
//		int total = 0;
//		List<T> pageList = null;
//			Query q = this.getSession().createQuery(countHql);
//			total = ((Long) q.uniqueResult()).intValue();
//			System.out.println("total = " + total);
//			if (total == 0) {
//				pageList = Collections.<T> emptyList();
//			} else {
//				q = this.getSession().createQuery(pageListHql);
//
//				int startIndex = pagination.getStartIndex();
//				int pageSize = pagination.getPageSize();
//				if (startIndex >= 0 && pageSize >= 0) {
//					q.setFirstResult(startIndex);
//					q.setMaxResults(pageSize);
//				}
//				pageList = q.list();
//			}
//			
//			System.out.println("pageList.size() = " + pageList.size());
//			
//			pagination.setTotal(total);
//			pagination.setPageList(pageList);
//			return pagination;
//		
//	}

}
