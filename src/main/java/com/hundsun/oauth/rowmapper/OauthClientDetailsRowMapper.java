package com.hundsun.oauth.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hundsun.oauth.security.OauthClientDetails;

public class OauthClientDetailsRowMapper implements RowMapper<OauthClientDetails> {

	@Override
	public OauthClientDetails mapRow(ResultSet re, int i) throws SQLException {
		OauthClientDetails ocd  = new OauthClientDetails();
		ocd.setClientId(re.getString("client_id"));
		ocd.setResourceIds(re.getString("resource_ids"));
		ocd.setClientSecret(re.getString("client_secret"));
		
		ocd.setScope(re.getString("scope"));
		ocd.setAuthorizedGrantTypes(re.getString("authorized_grant_types"));
		ocd.setWebServerRedirectUri(re.getString("web_server_redirect_uri"));
		
		ocd.setAuthorities(re.getString("authorities"));
		ocd.setAccessTokenValidity(getIntegerType(re,"access_token_validity"));
		ocd.setRefreshTokenValidity(getIntegerType(re, "refresh_token_validity"));
		
		ocd.setAdditionalInformation(re.getString("additional_information"));
		ocd.setCreateTime(re.getTimestamp("create_time").toLocalDateTime());
		ocd.setArchived(re.getBoolean("archived"));
		
		ocd.setTrusted(re.getBoolean("trusted"));
		ocd.setAutoApprove(re.getString("autoapprove"));
		
		return ocd;
	}

	private Integer getIntegerType(ResultSet re, String columnName) {
		Object obj = null;
		try {
			obj = re.getObject(columnName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(null != obj){
			return (Integer)obj;
		}
		return null;
	}

}
