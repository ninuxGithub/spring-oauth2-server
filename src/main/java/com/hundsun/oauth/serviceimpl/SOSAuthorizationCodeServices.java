package com.hundsun.oauth.serviceimpl;

import javax.sql.DataSource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import com.hundsun.oauth.constant.CacheConstants;

public class SOSAuthorizationCodeServices extends JdbcAuthorizationCodeServices {

	public SOSAuthorizationCodeServices(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	@Cacheable(value = CacheConstants.AUTHORIZATION_CODE_CACHE, key = "#code")
	protected void store(String code, OAuth2Authentication authentication) {
		super.store(code, authentication);
	}

	@Override
	@CacheEvict(value = CacheConstants.AUTHORIZATION_CODE_CACHE, key = "#code")
	public OAuth2Authentication remove(String code) {
		return super.remove(code);
	}
}
