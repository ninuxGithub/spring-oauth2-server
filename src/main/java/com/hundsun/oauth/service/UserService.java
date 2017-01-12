package com.hundsun.oauth.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hundsun.oauth.domain.User;
import com.hundsun.oauth.dto.UserFormDto;
import com.hundsun.oauth.dto.UserJsonDto;
import com.hundsun.oauth.dto.UserOverviewDto;

public interface UserService extends UserDetailsService {

	//default method 
	// public UserDetails loadUserByUsername(String username);

	/**
	 * 获取当前用户的json数据：判读当前用户是根据 <br>
	 * <code>
	 * 		<pre>Authentication authentication = SecurityContextHolder.getContext().getAuthentication();</pre><br>
	 * </code>
	 * 依赖SecurityContextHolder 上下文<br>
	 * 
	 * @return
	 */
	UserJsonDto loadCurrentUserJsonDto();

	/**
	 * 同一个用户名称对应的 多个 UserDto
	 * @param overviewDto
	 * @return
	 */
	UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto);

	/**
	 * 判读某个用户名是否存在
	 * @param username
	 * @return
	 */
	boolean isExistedUsername(String username);

	/**
	 * 从UserFormDto 中提取到User 对象 然后保存 
	 * @param formDto
	 * @return guid 
	 */
	String saveUser(UserFormDto formDto);

	/**
	 * 根据用户id加载用户
	 * @param id
	 * @return 
	 */
	UserFormDto loadUserFormDtoById(Long id);

	List<User> findUsersByName(String username);

	void updateUser(UserFormDto formDto);

	User findUserByNameAndPassword(String username, String password);

}
