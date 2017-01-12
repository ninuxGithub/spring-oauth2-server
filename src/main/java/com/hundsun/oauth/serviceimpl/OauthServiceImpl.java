package com.hundsun.oauth.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hundsun.oauth.dto.AccessTokenDto;
import com.hundsun.oauth.dto.AuthAccessTokenDto;
import com.hundsun.oauth.dto.AuthCallbackDto;
import com.hundsun.oauth.dto.OauthClientDetailsDto;
import com.hundsun.oauth.repository.OauthRepository;
import com.hundsun.oauth.security.AccessTokenResponseHandler;
import com.hundsun.oauth.security.OauthClientDetails;
import com.hundsun.oauth.service.OauthService;
import com.hundsun.oauth.utils.HttpClientExecutor;
import com.hundsun.oauth.utils.HttpClientPostExecutor;

@Service("oauthService")
public class OauthServiceImpl implements OauthService {
	@Autowired
	private OauthRepository oauthRepository;

	@Value("#{properties['access-token-uri']}")
	private String accessTokenUri;

	@Value("#{properties['unityUserInfoUri']}")
	private String unityUserInfoUri;

	@Override
	public OauthClientDetails loadOauthClientDetails(String clientId) {
		return oauthRepository.findOauthClientDetails(clientId);
	}

	@Override
	public List<OauthClientDetailsDto> loadAllOauthClientDetailsDtos() {
		List<OauthClientDetails> clientDetailses = oauthRepository.findAllOauthClientDetails();
		return OauthClientDetailsDto.toDtos(clientDetailses);
	}

	@Override
	public void archiveOauthClientDetails(String clientId) {
		oauthRepository.updateOauthClientDetailsArchive(clientId, true);
	}

	@Override
	public OauthClientDetailsDto loadOauthClientDetailsDto(String clientId) {
		final OauthClientDetails oauthClientDetails = oauthRepository.findOauthClientDetails(clientId);
		return oauthClientDetails != null ? new OauthClientDetailsDto(oauthClientDetails) : null;
	}

	@Override
	public void registerClientDetails(OauthClientDetailsDto formDto) {
		OauthClientDetails clientDetails = formDto.OauthClientDetailDto2Domain();
		oauthRepository.saveOauthClientDetails(clientDetails);
	}

	@Override
	public void updateClientDetails(OauthClientDetailsDto formDto) {
		OauthClientDetails clientDetails = formDto.OauthClientDetailDto2Domain();
		oauthRepository.updateOauthClientDetails(clientDetails);
	}

	@Override
	public AuthAccessTokenDto createAuthAccessTokenDto(AuthCallbackDto callbackDto) {
		return new AuthAccessTokenDto().setAccessTokenUri(accessTokenUri).setCode(callbackDto.getCode());
	}

	@Override
	public AccessTokenDto retrieveAccessTokenDto(AuthAccessTokenDto tokenDto) {
		final String fullUri = tokenDto.getAccessTokenUri();

		return loadAccessTokenDto(fullUri, tokenDto.getAuthCodeParams());
	}

	private AccessTokenDto loadAccessTokenDto(String fullUri, Map<String, String> params) {
		HttpClientExecutor executor = new HttpClientPostExecutor(fullUri);
		for (String key : params.keySet()) {
			executor.addRequestParam(key, params.get(key));
		}

		AccessTokenResponseHandler responseHandler = new AccessTokenResponseHandler();
		executor.execute(responseHandler);

		return responseHandler.getAccessTokenDto();
	}

	@Override
	public AuthAccessTokenDto createAuthAccessTokenDto(String clientId) {
		return null;
	}
}
