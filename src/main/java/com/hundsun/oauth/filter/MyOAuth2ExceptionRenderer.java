package com.hundsun.oauth.filter;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * 如果client调用ajax 
 * 返回401说明没有经过授权
 * 返回402：token无效 
 * 403 无效
 *  
 * 如果是后台会直接重定向到页面
 * 
 * @function:
 * @spring-oauth2-server :项目名称
 * @com.hundsun.oauth.filter.MyOAuth2ExceptionRenderer.java 类全路径
 * @2017年1月12日 下午5:00:47
 * @MyOAuth2ExceptionRenderer
 *
 */

@Component("myOAuth2ExceptionRenderer")
public class MyOAuth2ExceptionRenderer implements OAuth2ExceptionRenderer {

	private final Logger logger = Logger.getLogger(MyOAuth2ExceptionRenderer.class);

	@Value("#{properties['erorr.renderPage']}")
	private String errorPage;

	@Override
	public void handleHttpEntityResponse(HttpEntity<?> responseEntity, ServletWebRequest webRequest) throws Exception {
		if (responseEntity == null) {
			return;
		}
		Object body = responseEntity.getBody();

		if (body != null) {
			Exception e = (Exception) body;
			String errMsg = e.getMessage();
			String localMsg = e.getLocalizedMessage();
			
			logger.info(errMsg);

			if (StringUtils.isNotBlank(errMsg)) {
				if (body.toString().startsWith("error")) {
					boolean isAjax = isAjaxRequest(webRequest);
					if (isAjax) {
						if (errMsg.equals("An Authentication object was not found in the SecurityContext")) {
							webRequest.getResponse().getWriter().write("{\"error\":\"without Authentication\",\"code\":\"401\"}");
						} else if (errMsg.contains("Access token expired")) {
							webRequest.getResponse().getWriter().write("{\"error\":\"Access token expired\",\"code\":\"402\"}");
						}else if(errMsg.contains("Invalid access token")){
							webRequest.getResponse().getWriter().write("{\"error\":\"Invalid access token\",\"code\":\"403\"}");
						}else{
							webRequest.getResponse().getWriter().write("{\"error\":\"Invalid access token\",\"code\":\"403\"}");
						}
					} else {
						HttpServletResponse response = webRequest.getResponse();
						HttpServletRequest request = webRequest.getRequest();
						String url = request.getRequestURL().toString();
						String factor = body.toString();
						response.setContentType("charset=utf-8");
						String pathUrl = "authorization_code";
						String action = "去登陆授权";
						if (errMsg.equals("An Authentication object was not found in the SecurityContext")) {
							localMsg = "没有经过授权 ! (您正在访问受保护的资源，需要授权访问)";
						} else if (errMsg.contains("Access token expired")) {
							pathUrl = "toLogin";
							action = "去重新登陆";
							localMsg = "当前的访问携带的token已经过期! (请重新登录)";
						}
						response.sendRedirect(errorPage + "?requestUrl=" + url + "&action="
								+ URLEncoder.encode(action, "UTF-8") + "&factor=" + factor + "&pathUrl=" + pathUrl
								+ "&localMsg=" + URLEncoder.encode(localMsg, "UTF-8"));
					}
				}
			}
		}
	}

	/**
	 * 判断是否是ajax请求
	 * @param request
	 * @return
	 */
	public boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}

	/**
	 * 判断是否是ajax请求
	 * @param webRequest
	 * @return
	 */
	public static boolean isAjaxRequest(WebRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

}
