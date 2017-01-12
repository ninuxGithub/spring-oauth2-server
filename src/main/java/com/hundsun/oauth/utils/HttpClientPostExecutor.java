package com.hundsun.oauth.utils;

import org.apache.http.client.methods.RequestBuilder;

public class HttpClientPostExecutor extends HttpClientExecutor {

	public HttpClientPostExecutor(String url) {
		super(url);
	}

	protected RequestBuilder createRequestBuilder() {
		return RequestBuilder.post();
	}
}
