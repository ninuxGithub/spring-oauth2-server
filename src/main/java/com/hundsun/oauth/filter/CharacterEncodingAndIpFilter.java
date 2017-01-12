package com.hundsun.oauth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.hundsun.oauth.utils.WebUtils;

/**
 * @function: 对字符集 IP的过滤器 日志的记载
 * @spring-security-oathority :项目名称
 * @com.hundsun.sso.filter.CharacterEncodingAndIpFilter.java 类全路径
 * @2016年12月30日 下午2:33:44
 * @CharacterEncodingAndIpFilter
 *
 */
public class CharacterEncodingAndIpFilter extends CharacterEncodingFilter {
	private static final Logger logger = Logger.getLogger(CharacterEncodingAndIpFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String ip = WebUtils.retriveClientIp(request);
		WebUtils.setIp(ip);
		logger.debug("user enter the system and User's Ip is :" + ip);
		
		HttpSession session = request.getSession();
		Object user_object = session.getAttribute("login_user");
		String token_object = (String) session.getAttribute("login_access_token");
		UserDetails userDetail = null;
		String access_token = null;
		if (null != user_object) {
			userDetail = (UserDetails) user_object;
		}
		if (StringUtils.isNotBlank(token_object)) {
			access_token = token_object;
		}

		if (null != userDetail && StringUtils.isNotBlank(access_token)) {
			System.err.println("set access_token into request and session scope " + access_token);
			request.setAttribute("access_token", access_token);

			session.setAttribute("access_token", access_token);

		}
		super.doFilterInternal(request, response, filterChain);
	}

}
