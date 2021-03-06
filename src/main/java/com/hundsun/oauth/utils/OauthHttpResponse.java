package com.hundsun.oauth.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.http.client.methods.CloseableHttpResponse;

public class OauthHttpResponse implements Serializable {
	private static final long serialVersionUID = -4122042370889800441L;
	protected CloseableHttpResponse httpResponse;

	public OauthHttpResponse(CloseableHttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public CloseableHttpResponse httpResponse() {
		return httpResponse;
	}

	/*
	 * Convert response as string
	 */
	public String responseAsString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			httpResponse.getEntity().writeTo(baos);
			return new String(baos.toByteArray(), "UTF-8");
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/*
	 * Check response status is 200 or not
	 */
	public boolean isResponse200() {
		return httpResponse.getStatusLine().getStatusCode() == 200;
	}

}