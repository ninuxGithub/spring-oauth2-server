package com.hundsun.oauth.security;

import com.hundsun.oauth.dto.AccessTokenDto;
import com.hundsun.oauth.utils.OauthHttpResponse;

public class AccessTokenResponseHandler extends AbstractResponseHandler<AccessTokenDto> {

	private AccessTokenDto accessTokenDto;

	public AccessTokenResponseHandler() {
	}

	@Override
	public void handleResponse(OauthHttpResponse response) {
		if (response.isResponse200()) {
			this.accessTokenDto = responseToDto(response, new AccessTokenDto());
		} else {
			this.accessTokenDto = responseToErrorDto(response, new AccessTokenDto());
		}
	}

	public AccessTokenDto getAccessTokenDto() {
		return accessTokenDto;
	}
}
