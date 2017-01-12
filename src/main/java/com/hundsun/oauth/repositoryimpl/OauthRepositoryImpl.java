package com.hundsun.oauth.repositoryimpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hundsun.oauth.constant.CacheConstants;
import com.hundsun.oauth.repository.OauthRepository;
import com.hundsun.oauth.rowmapper.OauthClientDetailsRowMapper;
import com.hundsun.oauth.security.OauthClientDetails;

@Repository("oauthRepositoryImpl")
public class OauthRepositoryImpl implements OauthRepository {
	private static OauthClientDetailsRowMapper oauthClientDetailsRowMapper = new OauthClientDetailsRowMapper();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public OauthClientDetails findOauthClientDetails(String clientId) {
		final String sql = " select * from oauth_client_details where  client_id = ? ";
		final List<OauthClientDetails> list = this.jdbcTemplate.query(sql, new Object[] { clientId },oauthClientDetailsRowMapper);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<OauthClientDetails> findAllOauthClientDetails() {
		final String sql = " select * from oauth_client_details order by create_time desc ";
		return this.jdbcTemplate.query(sql, oauthClientDetailsRowMapper);
	}

	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_CACHE, key = "#clientId")
	public void updateOauthClientDetailsArchive(String clientId, boolean archive) {
		final String sql = " update oauth_client_details set archived = ? where client_id = ? ";
		this.jdbcTemplate.update(sql, archive, clientId);
	}

	@Override
	public void saveOauthClientDetails(final OauthClientDetails clientDetails) {
		final String sql = " insert into oauth_client_details(client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,"
				+ " authorities,access_token_validity,refresh_token_validity,additional_information,trusted,autoapprove,create_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		this.jdbcTemplate.update(sql, ps -> {
			ps.setString(1, clientDetails.getClientId());
			ps.setString(2, clientDetails.getResourceIds());

			ps.setString(3, clientDetails.getClientSecret());
			ps.setString(4, clientDetails.getScope());

			ps.setString(5, clientDetails.getAuthorizedGrantTypes());
			ps.setString(6, clientDetails.getWebServerRedirectUri());

			ps.setString(7, clientDetails.getAuthorities());
			ps.setObject(8, clientDetails.getAccessTokenValidity());

			ps.setObject(9, clientDetails.getRefreshTokenValidity());
			ps.setString(10, clientDetails.getAdditionalInformation());

			ps.setBoolean(11, clientDetails.getTrusted());
			ps.setString(12, clientDetails.getAutoApprove());
			ps.setTimestamp(13, Timestamp.valueOf(clientDetails.getCreateTime()));

		});
	}

	@Override
	public void updateOauthClientDetails(OauthClientDetails clientDetails) {
		final String sql = " update oauth_client_details set resource_ids=?,client_secret=?,scope=?,authorized_grant_types=?,web_server_redirect_uri=?,"
				+ " authorities=?,access_token_validity=?,refresh_token_validity=?,additional_information=?,trusted=?,autoapprove=?,create_time=? where client_id=?" ;

		this.jdbcTemplate.update(sql, ps -> {
			ps.setString(1, clientDetails.getResourceIds());

			ps.setString(2, clientDetails.getClientSecret());
			ps.setString(3, clientDetails.getScope());

			ps.setString(4, clientDetails.getAuthorizedGrantTypes());
			ps.setString(5, clientDetails.getWebServerRedirectUri());

			ps.setString(6, clientDetails.getAuthorities());
			ps.setObject(7, clientDetails.getAccessTokenValidity());

			ps.setObject(8, clientDetails.getRefreshTokenValidity());
			ps.setString(9, clientDetails.getAdditionalInformation());

			ps.setBoolean(10, clientDetails.getTrusted());
			ps.setString(11, clientDetails.getAutoApprove());
			ps.setTimestamp(12, Timestamp.valueOf(clientDetails.getCreateTime()));
			ps.setString(13, clientDetails.getClientId());

		});
	}

}
