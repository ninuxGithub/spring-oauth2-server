package com.hundsun.oauth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hundsun.oauth.domain.User;
import com.hundsun.oauth.security.OauthClientDetails;
import com.hundsun.oauth.service.OauthService;
import com.hundsun.oauth.service.UserService;
import com.hundsun.oauth.utils.HttpClientUtil;
import com.hundsun.oauth.utils.PasswordHandler;

import net.sf.json.JSONObject;

/**
 * @function:
 * @spring-oauth-server :项目名称
 * @com.monkeyk.sos.web.controller.LoginController.java 类全路径
 * @2017年1月6日 上午11:28:49
 * @LoginController
 *
 */
@Controller
public class LoginController {

	@Value("#{properties['access-token-uri']}")
	private String accessTokenUri;
	
	@Value("#{properties['user-authorization-uri']}")
	private String authorizeUrl;
	
	@Autowired
	private UserService userService;

	@Autowired
	private OauthService oauthService;

	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return "normal_login";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() {
		return "success";
	}

	/**
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password,	HttpServletRequest request) {
		// 判断用户名称和密码不为空
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			// 根据用户名称获取对于的UserDetails ，该对象包含了用户的信息
			UserDetails userDetail = userService.loadUserByUsername(username);
			User user = userService.findUserByNameAndPassword(username, PasswordHandler.md5(password));
			

			if (null != userDetail && null != user) {
				String user_detail_password = userDetail.getPassword();
				String md5_password = PasswordHandler.md5(password);
				if (null != user_detail_password && null != md5_password) {
					// 和数据库的密码进行对比
					if (md5_password.equals(user_detail_password)) {
						request.getSession().setAttribute("login_user", userDetail);
						// 加载client 信息：用户需要和client 进行关联
						OauthClientDetails oauthClientDetails=null;
						if(StringUtils.isNotBlank(user.getClientId())){
							oauthClientDetails = oauthService.loadOauthClientDetails(user.getClientId());
						}
						if(null == oauthClientDetails){
							return "redirect:/toLogin";
						}
						// 采用HttpClien 获取token
						String access_token = getAccessToken(oauthClientDetails, userDetail, password);
						if (StringUtils.isNotBlank(access_token)) {
							request.getSession().setAttribute("login_access_token", access_token);
							return "redirect:/success";
						} else {
							return "redirect:/toLogin";
						}
					}
				}

			}
		}
		return "redirect:/toLogin";
	}
	
	@RequestMapping("/doLogout")
	public String logout(HttpSession session){
		//一.清理SecurityContextHolder里面的authentication 对象
		SecurityContextHolder.clearContext();
		//一.清理session 里面的 login_user, login_access_token对象
		session.invalidate();
		return "redirect:/toLogin";
	}

	/**
	 * 由于不同的client 用于不同的获取token的方式--加入支持 password , authorization_code 方式获取token
	 * 
	 * @param oauthClientDetails
	 * @param userDetail
	 * @param password
	 *            不需要解密数据库中的密码 ： 直接采用用户传递进来的密码 思考了好久
	 * @param request
	 * @return
	 */
	private String getAccessToken(OauthClientDetails oauthClientDetails, UserDetails userDetail, String password) {

		String token = null;
		String grantTypes = oauthClientDetails.getAuthorizedGrantTypes();
		
		if(StringUtils.isNotBlank(grantTypes)){
			//get scope 
			String scope = oauthClientDetails.getScope();
			if(scope.contains(",")){
				scope = scope.replaceAll(",", " ");
			}
			if(scope.contains("，")){
				scope = scope.replaceAll("，", " ");
			}
			
			//password 方式授权
			Map<String, String> params = new HashMap<>();
			if(grantTypes.toLowerCase().contains("password")){
				params.put("client_id", oauthClientDetails.getClientId());
				params.put("client_secret", oauthClientDetails.getClientSecret());
				params.put("grant_type", "password");
				params.put("scope", scope);
				params.put("username", userDetail.getUsername());
				params.put("password", password);
			}
			
			JSONObject json = HttpClientUtil.sendPost(accessTokenUri, params);
			if (null != json) {
				token = (String) json.get("access_token");
			}			
		}

		return token;
	}

}
