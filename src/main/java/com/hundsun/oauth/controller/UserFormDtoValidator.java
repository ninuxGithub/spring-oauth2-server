package com.hundsun.oauth.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hundsun.oauth.domain.Privilege;
import com.hundsun.oauth.domain.User;
import com.hundsun.oauth.dto.UserFormDto;
import com.hundsun.oauth.security.OauthClientDetails;
import com.hundsun.oauth.service.OauthService;
import com.hundsun.oauth.service.UserService;

/**
 * @function:用户添加的时候对表单进行验证， 将错误消息封装到对应的属性中去  JavaField : BindingResult
 * @spring-security-oathority :项目名称
 * @com.hundsun.sso.controller.UserFormDtoValidator.java 类全路径
 * @2017年1月4日 上午10:22:26  
 * @UserFormDtoValidator 
 *
 */
@Component
public class UserFormDtoValidator implements Validator {
	@Autowired
	private UserService userService;
	
	@Autowired
	private OauthService oauthService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserFormDto.class.equals(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		UserFormDto formDto = (UserFormDto) target;
		validateClientId(errors,formDto);
		validateUsername(errors, formDto);
		validatePassword(errors, formDto);
		validatePrivileges(errors, formDto);
	}

	/**
	 * 验证client id 唯一， 并且在数据库中oauth_client_details 中有一条唯一记录与之匹配
	 * @param errors
	 * @param formDto
	 */
	private void validateClientId(Errors errors, UserFormDto formDto) {
		String clientId = formDto.getClientId();
		if(StringUtils.isBlank(clientId)){
			errors.rejectValue("clientId", null,"clientId is required");
		}
		OauthClientDetails ocd = oauthService.loadOauthClientDetails(clientId);
		if(null == ocd){
			errors.rejectValue("clientId",null,"请对clientId 为 【"+ clientId+ "】 进行先注册，再来添加用户");
		}
		
	}

	/**
	 * 验证权限不为空
	 * @param errors
	 * @param formDto
	 */
	private void validatePrivileges(Errors errors, UserFormDto formDto) {
		final List<Privilege> privileges = formDto.getPrivileges();
		if (privileges == null || privileges.isEmpty()) {
			errors.rejectValue("privileges", null, "Privileges is required");
		}
	}

	/**
	 * 验证密码不为空
	 * @param errors
	 * @param formDto
	 */
	private void validatePassword(Errors errors, UserFormDto formDto) {
		final String password = formDto.getPassword();
		if (StringUtils.isEmpty(password)) {
			errors.rejectValue("password", null, "Password is required");
		}
	}

	/**
	 * 验证用户名是唯一的且不为空
	 * @param errors
	 * @param formDto
	 */
	private void validateUsername(Errors errors, UserFormDto formDto) {
		final String username = formDto.getUsername();
		if (StringUtils.isEmpty(username)) {
			errors.rejectValue("username", null, "Username is required");
			return;
		}

		boolean existed = userService.isExistedUsername(username);
		if (existed) {
			errors.rejectValue("username", null, "Username already existed");
		}

	}
	
	/**
	 * 验证用户名是唯一的且不为空
	 * @param errors
	 * @param formDto
	 */
	private void validateUsername2(Errors errors, UserFormDto formDto) {
		final String username = formDto.getUsername();
		if (StringUtils.isEmpty(username)) {
			errors.rejectValue("username", null, "Username is required");
			return;
		}
		
		List<User> list = userService.findUsersByName(username);
		if(null != list && list.size()>1){
			errors.rejectValue("username", null, "Username is duplicated");
			return;
		}
		
	}

	/**
	 * 修改用户的验证
	 * @param target
	 * @param errors
	 */
	public void updateValidate(UserFormDto target, BindingResult errors) {
		UserFormDto formDto = (UserFormDto) target;
		validateClientId(errors,formDto);
		validateUsername2(errors, formDto);
		validatePassword(errors, formDto);
		validatePrivileges(errors, formDto);
	}
	
}
