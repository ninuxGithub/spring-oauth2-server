package com.hundsun.oauth.repository;

import java.util.List;

import com.hundsun.oauth.security.OauthClientDetails;

public interface OauthRepository extends Repository {

	OauthClientDetails findOauthClientDetails(String clientId);

	List<OauthClientDetails> findAllOauthClientDetails();

	void updateOauthClientDetailsArchive(String clientId, boolean archive);

	void saveOauthClientDetails(OauthClientDetails clientDetails);

	void updateOauthClientDetails(OauthClientDetails clientDetails);

}
