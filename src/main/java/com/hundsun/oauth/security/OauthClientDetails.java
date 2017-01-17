package com.hundsun.oauth.security;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.hundsun.oauth.utils.DateUtils;

public class OauthClientDetails implements Serializable {

	private static final long serialVersionUID = 1996940478821910463L;

	/*** 创建时间 */
	private LocalDateTime createTime = DateUtils.now();

	/*** 是否物理删除 */
	private boolean archived = false;

	/*** 用户id */
	private String clientId;

	/*** 资源id */
	private String resourceIds;

	/*** 用户账号密码 */
	private String clientSecret;

	/*** 权限的作用范围 */
	private String scope;

	/*** 授权方式 */
	private String authorizedGrantTypes = "authorization_code, refresh_token";

	/** 重定向uri **/
	private String webServerRedirectUri;

	/****/
	private String authorities;

	/** 访问验证 **/
	private Integer accessTokenValidity;

	/** 刷新验证 **/
	private Integer refreshTokenValidity;

	/** 额外消息 **/
	private String additionalInformation;

	/** 是否可信 **/
	private boolean trusted = false;

	/** 自动授权 **/
	private String autoApprove;

	private List<String> privileges = new ArrayList<String>();

	public OauthClientDetails() {
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public boolean getArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public boolean getTrusted() {
		return trusted;
	}

	public void setTrusted(boolean trusted) {
		this.trusted = trusted;
	}

	public String getAutoApprove() {
		return autoApprove;
	}

	public void setAutoApprove(String autoApprove) {
		this.autoApprove = autoApprove;
	}

	

	public List<String> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "OauthClientDetails [createTime=" + createTime + ", archived=" + archived + ", clientId=" + clientId
				+ ", resourceIds=" + resourceIds + ", clientSecret=" + clientSecret + ", scope=" + scope
				+ ", authorizedGrantTypes=" + authorizedGrantTypes + ", webServerRedirectUri=" + webServerRedirectUri
				+ ", authorities=" + authorities + ", accessTokenValidity=" + accessTokenValidity
				+ ", refreshTokenValidity=" + refreshTokenValidity + ", additionalInfomation=" + additionalInformation
				+ ", trusted=" + trusted + ", autoApprove=" + autoApprove + "]";
	}

}
