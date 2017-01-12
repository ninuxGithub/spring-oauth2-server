package com.hundsun.oauth.serviceimpl;

import javax.sql.DataSource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import com.hundsun.oauth.constant.CacheConstants;

public class CustomJdbcClientDetailsService extends JdbcClientDetailsService {
	private static final String SELECT_CLIENT_DETAILS_SQL = "select client_id, client_secret, resource_ids, scope, authorized_grant_types, "
			+ "web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove "
			+ "from oauth_client_details where client_id = ? and archived = 0 ";

	public CustomJdbcClientDetailsService(DataSource dataSource) {
		super(dataSource);
		setSelectClientDetailsSql(SELECT_CLIENT_DETAILS_SQL);
	}

	/**
	 * ClientDetailsService 会调用这个方法
	 */

	@Override
	@Cacheable(value = CacheConstants.CLIENT_DETAILS_CACHE, key = "#clientId")
	public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
		return super.loadClientByClientId(clientId);
	}

	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_CACHE, key = "#clientDetails.getClientId()")
	public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
		super.updateClientDetails(clientDetails);
	}

	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_CACHE, key = "#clientId")
	public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
		super.updateClientSecret(clientId, secret);
	}

	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_CACHE, key = "#clientId")
	public void removeClientDetails(String clientId) throws NoSuchClientException {
		super.removeClientDetails(clientId);
	}
}
