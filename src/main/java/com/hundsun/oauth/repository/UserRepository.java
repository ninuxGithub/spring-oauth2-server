package com.hundsun.oauth.repository;

import java.util.List;

import com.hundsun.oauth.domain.User;

/**
 * @description: user 对象中的guid是唯一的 可以根据guid进行用户的唯一区分
 * @function:用户接口
 * @spring-security-oathority :项目名称
 * @com.hundsun.sso.repository.UserRepository.java 类全路径
 * @2016年12月30日 下午3:34:03
 * @UserRepository
 *
 */
public interface UserRepository extends Repository {

	/**
	 * 更加guid 查找对应的用户
	 * 
	 * @param guid
	 * @return
	 */
	User findByGuid(String guid);

	/**
	 * 保存用户
	 * @param user
	 */
	void saveUser(User user);

	void updateUser(User user);

	User findByUsername(String username);

	List<User> findUsersByUsername(String username);

	
	User findGreaterIdUser();

	User findUserById(Long id);

	User findUserByNameAndPassword(String username, String password);

}
