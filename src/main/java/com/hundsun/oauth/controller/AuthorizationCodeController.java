package com.hundsun.oauth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hundsun.oauth.domain.User;
import com.hundsun.oauth.dto.AccessTokenDto;
import com.hundsun.oauth.dto.AuthAccessTokenDto;
import com.hundsun.oauth.dto.AuthCallbackDto;
import com.hundsun.oauth.dto.AuthorizationCodeDto;
import com.hundsun.oauth.security.OauthClientDetails;
import com.hundsun.oauth.security.UserDetail;
import com.hundsun.oauth.service.OauthService;
import com.hundsun.oauth.service.UserService;
import com.hundsun.oauth.utils.HttpClientUtil;
import com.hundsun.oauth.utils.WebUtils;

import net.sf.json.JSONObject;

/**
 * Handle 'authorization_code' type actions
 *
 */
@Controller
public class AuthorizationCodeController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthorizationCodeController.class);

	@Value("#{properties['user-authorization-uri']}")
	private String userAuthorizationUri;

	@Value("#{properties['application-host']}")
	private String host;

	@Value("#{properties['unityUserInfoUri']}")
	private String unityUserInfoUri;

	@Value("#{properties['access-token-uri']}")
	private String accessTokenUri;

	@Autowired
	private OauthService oauthService;

	@Autowired
	private UserService userService;

	/*
	 * Entrance: step-1
	 */
	@RequestMapping(value = "authorization_code", method = RequestMethod.GET)
	public String authorizationCode(Model model) {
		List<User> users = userService.findUsersByName("mobile");
		User mobile = users.get(0);
		model.addAttribute("userAuthorizationUri", userAuthorizationUri);
		model.addAttribute("host", host);
		model.addAttribute("clientId", mobile.getClientId());
		model.addAttribute("unityUserInfoUri", unityUserInfoUri);
		model.addAttribute("state", UUID.randomUUID().toString());
		return "authorization_code";
	}

	@RequestMapping(value = "authorization_code", method = RequestMethod.POST)
	public String submitAuthorizationCode(AuthorizationCodeDto codeDto, HttpServletRequest request) throws Exception {
		// save stats firstly
		WebUtils.saveState(request, codeDto.getState());
		WebUtils.saveClientInfo(request, codeDto);

		final String fullUri = codeDto.getFullUri();
		LOG.debug("Redirect to Oauth-Server URL: {}", fullUri);
		return "redirect:" + fullUri;
	}
	
	@RequestMapping("/access_token_result_update")
	public String toAuthorizationCodeSecucess(@RequestParam("access_token") String token, Model model){
		model.addAttribute("access_token", token);
		return "access_token_result_update";
	}

	@RequestMapping(value = "authorization_code_callback")
	public String authorizationCodeCallback(AuthCallbackDto callbackDto, HttpServletRequest request, Model model)
			throws Exception {
		
		if (callbackDto.error()) {
			// Server response error
			model.addAttribute("message", callbackDto.getError_description());
			model.addAttribute("error", callbackDto.getError());
			return "redirect:oauth_error";
		} else if (correctState(callbackDto, request)) {
			AuthorizationCodeDto acd = (AuthorizationCodeDto) request.getServletContext().getAttribute("authorization_code_dto");
			if (null != acd && StringUtils.isNotBlank(acd.getClientId())) {
				String clientId = acd.getClientId();
				OauthClientDetails clientDetails = oauthService.loadOauthClientDetails(clientId);
				if(null ==clientDetails){
					model.addAttribute("message", "根据["+clientId+"]没要找到对应的ClientDetail");
					model.addAttribute("error", "400");
					return "redirect:oauth_error";
				}
				Map<String, String> params = new HashMap<>();
				params.put("grant_type", "authorization_code");
				params.put("client_secret", clientDetails.getClientSecret());
				params.put("client_id", clientId);
				params.put("code", callbackDto.getCode());
				params.put("redirect_uri", clientDetails.getWebServerRedirectUri().trim());

				String token = null;
				JSONObject json = HttpClientUtil.sendPost(accessTokenUri, params);
				if (null != json) {
					token = (String) json.get("access_token");
				}else{
					model.addAttribute("message", "获取token失败");
					model.addAttribute("error", "401");
					return "redirect:oauth_error";
				}
				return "redirect:/access_token_result_update?access_token="+token;
			}else{
				model.addAttribute("message", "ServletContext 中没有找到对应的client_id");
				model.addAttribute("error", "400");
				return "redirect:oauth_error";
			}
		} else {
			// illegal state
			model.addAttribute("message", "Illegal \"state\": " + callbackDto.getState());
			model.addAttribute("error", "Invalid state");
			return "redirect:oauth_error";
		}

	}

	/**
	 * Use HttpClient to get access_token : step-4
	 * <p/>
	 * Then, 'authorization_code' flow is finished, use 'access_token' visit
	 * resources now
	 *
	 * @param tokenDto
	 *            AuthAccessTokenDto
	 * @param model
	 *            Model
	 * @return View
	 * @throws Exception
	 */
	@RequestMapping(value = "code_access_token", method = RequestMethod.POST)
	public String codeAccessToken(AuthAccessTokenDto tokenDto, Model model, HttpSession session) throws Exception {
		final AccessTokenDto accessTokenDto = oauthService.retrieveAccessTokenDto(tokenDto);
		if (accessTokenDto.error()) {
			model.addAttribute("message", accessTokenDto.getErrorDescription());
			model.addAttribute("error", accessTokenDto.getError());
			return "oauth_error";
		} else {
			model.addAttribute("accessTokenDto", accessTokenDto);
			model.addAttribute("unityUserInfoUri", unityUserInfoUri);
			session.setAttribute("login_access_token", accessTokenDto.getAccessToken());
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			final Object principal = authentication.getPrincipal();
			if (null != principal && principal instanceof UserDetail) {
				UserDetail userDetails = (UserDetail) principal;
				System.out.println("principal is :" + principal);
				session.setAttribute("login_user", userDetails);

			}
			return "access_token_result";
		}
	}

	/*
	 * Check the state is correct or not after redirect from Oauth Server.
	 */
	private boolean correctState(AuthCallbackDto callbackDto, HttpServletRequest request) {
		final String state = callbackDto.getState();
		return WebUtils.validateState(request, state);
	}

}