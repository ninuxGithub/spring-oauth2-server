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
			System.err.println(errMsg);
			logger.info(body.toString());

			if (StringUtils.isNotBlank(errMsg)) {
				if (body.toString().startsWith("error")) {
					HttpServletResponse response = webRequest.getResponse();
					HttpServletRequest request = webRequest.getRequest();
					String url = request.getRequestURL().toString();
					String factor = body.toString();
					response.setContentType("charset=utf-8");
					String pathUrl = "authorization_code";
					String action ="去登陆授权";
					if (errMsg.equals("An Authentication object was not found in the SecurityContext")) {
						localMsg = "没有经过授权 ! (您正在访问受保护的资源，需要授权访问)";
					} else if (errMsg.contains("Access token expired")) {
						pathUrl="toLogin";
						action="去重新登陆";
						localMsg = "当前的访问携带的token已经过期! (请重新登录)";
					}
					response.sendRedirect(errorPage + "?requestUrl=" + url + "&action="+URLEncoder.encode(action, "UTF-8")+"&factor=" + factor + "&pathUrl="+pathUrl+"&localMsg="+ URLEncoder.encode(localMsg, "UTF-8"));
				}
			}
		}
	}

}
