package com.hundsun.oauth.dto;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

public class AuthCallbackDto implements Serializable {

	private static final long serialVersionUID = 2340958502149976283L;
	private String code;
	private String state;
	private String access_token;

	/*
	 * Server response error, For example: Click 'Deny' button
	 */
	private String error;
	private String error_description;

	public AuthCallbackDto() {
	}

	public String getError() {
		return error;
	}

	public boolean error() {
		return StringUtils.isNotEmpty(error) || StringUtils.isNotEmpty(error_description);
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

}