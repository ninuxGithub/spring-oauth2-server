package com.hundsun.oauth.service;

import java.util.List;

import com.hundsun.oauth.dto.AccessTokenDto;
import com.hundsun.oauth.dto.AuthAccessTokenDto;
import com.hundsun.oauth.dto.AuthCallbackDto;
import com.hundsun.oauth.dto.OauthClientDetailsDto;
import com.hundsun.oauth.security.OauthClientDetails;

public interface OauthService {

	OauthClientDetails loadOauthClientDetails(String clientId);

	List<OauthClientDetailsDto> loadAllOauthClientDetailsDtos();

	void archiveOauthClientDetails(String clientId);

	OauthClientDetailsDto loadOauthClientDetailsDto(String clientId);

	void registerClientDetails(OauthClientDetailsDto formDto);

	void updateClientDetails(OauthClientDetailsDto formDto);

	AuthAccessTokenDto createAuthAccessTokenDto(AuthCallbackDto callbackDto);

	AccessTokenDto retrieveAccessTokenDto(AuthAccessTokenDto tokenDto);

	AuthAccessTokenDto createAuthAccessTokenDto(String clientId);

}
