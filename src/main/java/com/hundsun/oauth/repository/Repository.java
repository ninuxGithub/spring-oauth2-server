package com.hundsun.oauth.repository;

import com.hundsun.oauth.domain.Pagination;

/**
 * @function:标记型接口
 * @spring-security-oathority :项目名称
 * @com.hundsun.sso.repository.Repository.java 类全路径
 * @2016年12月30日 下午3:33:34
 * @Repository
 *
 */
public interface Repository <T>{
	
	public Pagination<T> paginationBySql(String countSql, String pageListSql, Pagination<T> pagination) ;

}
